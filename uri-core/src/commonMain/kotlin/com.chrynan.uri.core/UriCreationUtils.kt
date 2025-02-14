@file:Suppress("unused")

package com.chrynan.uri.core

import com.chrynan.uri.core.validation.UriValidator
import com.chrynan.uri.core.validation.UrlValidator
import com.chrynan.uri.core.validation.ValidationResult

private const val PORT_START_DELIMITER = ':'
private const val USER_INFO_END_DELIMITER = '@'
private const val SCHEME_END_DELIMITER = ':'
private const val HOST_IPV6_END_DELIMITER = ']'

private const val SCHEME_HTTP = "http"
private const val SCHEME_HTTPS = "https"
private val URL_SCHEMES = listOf(SCHEME_HTTP, SCHEME_HTTPS)

private val uriValidator = UriValidator()
private val urlValidator = UrlValidator()

private val uriPartsRegex = Regex("^(([^:/?#]+):)?(//([^/?#]*))?([^?#]*)(\\?([^#]*))?(#(.*))?")

/**
 * Retrieves a [Uri] from the provided parts. If the provided parts are valid and properly formatted, then a [Uri] is
 * returned. If the provided parts are invalid or not properly formatted, then an [InvalidUriException] is thrown.
 *
 * @see [Uri]
 * @see [UriString]
 */
public fun Uri.Companion.fromParts(
    scheme: String,
    userInfo: String? = null,
    host: String? = null,
    port: Int? = null,
    path: String,
    query: String? = null,
    fragment: String? = null
): Uri {
    if (host == null && (userInfo != null || port != null)) throw InvalidUriException(message = "If userInfo or port fields are provided, then the host must be provided too.")

    val formattedScheme = when {
        scheme.endsWith("://") -> scheme.substringBefore("://")
        scheme.endsWith(":") -> scheme.substringBefore(":")
        else -> scheme
    }

    val formattedUserInfo = when {
        userInfo.isNullOrBlank() -> null
        userInfo.endsWith('@') -> userInfo.substringBefore('@')
        else -> userInfo
    }

    val formattedHost = when {
        host.isNullOrBlank() -> null
        host.startsWith('@') -> host.substringAfter('@')
        host.startsWith("://") -> host.substringAfter("://")
        else -> host
    }

    val formattedPath = when {
        host.isNullOrBlank() && path.startsWith('/') -> path.substringAfter('/')
        !host.isNullOrBlank() && !path.startsWith('/') -> "/$path"
        else -> path
    }

    val formattedQuery = if (query.isNullOrBlank()) null else query

    val formattedFragment = if (fragment.isNullOrBlank()) null else fragment

    val uriString = uriStringFromParts(
        scheme = formattedScheme,
        userInfo = formattedUserInfo,
        host = formattedHost,
        port = port,
        path = formattedPath,
        query = formattedQuery,
        fragment = formattedFragment
    )

    val validationResult = if (URL_SCHEMES.any { it.lowercase() == formattedScheme.lowercase() }) {
        urlValidator.validate(uriString.value)
    } else {
        uriValidator.validate(uriString.value)
    }

    when (validationResult) {
        is ValidationResult.Invalid -> throw InvalidUriException(message = "Invalid Uri from String = $uriString. Errors = ${validationResult.errors}")
        is ValidationResult.Valid -> {
            return createUriFromValidatedParts(
                uriString = UriString(value = validationResult.value),
                scheme = formattedScheme,
                userInfo = formattedUserInfo,
                host = formattedHost,
                port = port,
                path = formattedPath,
                query = formattedQuery,
                fragment = formattedFragment
            )
        }
    }
}

/**
 * Retrieves a [Uri] from the provided parts. If the provided parts are valid and properly formatted, then a [Uri] is
 * returned. If the provided parts are invalid or not properly formatted, then null is returned.
 *
 * @see [Uri]
 * @see [UriString]
 */
public fun Uri.Companion.fromPartsOrNull(
    scheme: String,
    userInfo: String? = null,
    host: String? = null,
    port: Int? = null,
    path: String,
    query: String? = null,
    fragment: String? = null
): Uri? =
    try {
        fromParts(
            scheme = scheme,
            userInfo = userInfo,
            host = host,
            port = port,
            path = path,
            query = query,
            fragment = fragment
        )
    } catch (e: Exception) {
        null
    }

/**
 * Retrieves a [Uri] from the provided parts without performing any validation or formatting of the input. Note that
 * this can result in undefined behavior if the provided input isn't valid or formatted appropriately according to the
 * the [Uri] interface documentation. Only use this function if you are certain that the provided input parts are
 * properly valid and formatted and you wish to bypass the validation and formatting for performance reasons.
 *
 * @see [Uri]
 * @see [UriString]
 */
public fun Uri.Companion.unsafeFromParts(
    scheme: String,
    userInfo: String? = null,
    host: String? = null,
    port: Int? = null,
    path: String,
    query: String? = null,
    fragment: String? = null
): Uri {
    val uriString = uriStringFromParts(
        scheme = scheme,
        userInfo = userInfo,
        host = host,
        port = port,
        path = path,
        query = query,
        fragment = fragment
    )

    return createUriFromValidatedParts(
        uriString = uriString,
        scheme = scheme,
        userInfo = userInfo,
        host = host,
        port = port,
        path = path,
        query = query,
        fragment = fragment
    )
}

private fun createUriFromValidatedParts(
    uriString: UriString,
    scheme: String,
    userInfo: String? = null,
    host: String? = null,
    port: Int? = null,
    path: String,
    query: String? = null,
    fragment: String? = null
): Uri {
    val authority = if (host == null) {
        null
    } else {
        buildString {
            if (userInfo != null) {
                append("$userInfo@")
            }

            append(host)

            if (port != null) {
                append(":$port")
            }
        }
    }

    return DefaultUri(
        uriString = uriString,
        scheme = scheme.lowercase(),
        authority = authority,
        userInfo = if (userInfo.isNullOrBlank()) null else userInfo,
        host = if (host.isNullOrBlank()) null else host,
        port = port,
        path = path.ifBlank { "" },
        query = if (query.isNullOrBlank()) null else query,
        fragment = if (fragment.isNullOrBlank()) null else fragment
    )
}

/**
 * A private function that creates a [UriString] from the provided parts. The provided parts are expected to be already
 * formatted.
 */
private fun uriStringFromParts(
    scheme: String,
    userInfo: String? = null,
    host: String? = null,
    port: Int? = null,
    path: String,
    query: String? = null,
    fragment: String? = null
): UriString {
    val value = buildString {
        append(scheme)

        if (host != null) {
            append("://")

            if (userInfo != null) {
                append("$userInfo@")
            }

            append(host)

            if (port != null) {
                append(":$port")
            }
        } else {
            append(":")
        }

        append(path)

        if (query != null) {
            append("?$query")
        }

        if (fragment != null) {
            append("#$fragment")
        }
    }

    return UriString(value = value)
}
