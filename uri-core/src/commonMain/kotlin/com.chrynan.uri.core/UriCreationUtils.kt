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
 * Retrieves a [Uri] from the provided [uriString]. If the provided [uriString] is valid and properly formatted, then a
 * [Uri] instance will be returned. If the provided [uriString] is invalid or is not properly formatted, then the
 * [InvalidUriException] will be thrown.
 *
 * @see [Uri]
 * @see [UriString]
 */
public fun Uri.Companion.fromString(uriString: UriString): Uri {
    val schemeEndIndex = uriString.indexOf(SCHEME_END_DELIMITER)

    if (schemeEndIndex == -1) throw InvalidUriException(message = "Invalid Uri from String = $uriString. The UriString is missing a Scheme component.")

    val scheme = uriString.substring(startIndex = 0, endIndex = schemeEndIndex)

    val isHttpUrl = URL_SCHEMES.any { it.lowercase() == scheme.lowercase() }

    when (val validationResult =
        if (isHttpUrl) urlValidator.validate(uriString) else uriValidator.validate(uriString)) {
        is ValidationResult.Invalid -> throw InvalidUriException(message = "Invalid Uri from String = $uriString. Errors = ${validationResult.errors}")
        is ValidationResult.Valid -> {
            val matchResult = uriPartsRegex.matchEntire(validationResult.value)

            if (matchResult == null) throw InvalidUriException(message = "Invalid Uri from String = $uriString; Match Result = $matchResult")

            val groupValues = matchResult.groupValues

            val validScheme = groupValues.getOrNull(2)
                ?: throw InvalidUriException(message = "Invalid Uri from String = $uriString. Scheme is missing.")
            val authority = groupValues.getOrNull(4)
            val path = groupValues.getOrNull(5) ?: ""
            val query = groupValues.getOrNull(7)
            val fragment = groupValues.getOrNull(9)

            val userInfoEndIndex = authority?.indexOf(USER_INFO_END_DELIMITER) ?: -1

            val userInfo =
                if (userInfoEndIndex == -1) null else authority?.substring(startIndex = 0, endIndex = userInfoEndIndex)

            val hostStartIndex = if (userInfoEndIndex == -1) 0 else userInfoEndIndex + 1
            val host = authority?.substring(hostStartIndex)?.let {
                var hostIpv6EndIndex = it.indexOf(HOST_IPV6_END_DELIMITER)
                var portStartIndex = it.indexOf(PORT_START_DELIMITER)

                if (hostIpv6EndIndex != -1) {
                    hostIpv6EndIndex += 1
                }

                if (hostIpv6EndIndex > portStartIndex) {
                    portStartIndex = -1
                }

                val hostEndIndex = listOf(hostIpv6EndIndex, portStartIndex, it.length)
                    .filter { index -> index != -1 }
                    .minOf { index -> index }

                it.substring(startIndex = hostStartIndex, endIndex = hostEndIndex)
            }

            val port = authority?.let {
                val hostIpv6EndIndex = it.indexOf(HOST_IPV6_END_DELIMITER)
                val portStartIndex = it.indexOf(PORT_START_DELIMITER)

                if (portStartIndex == -1 || hostIpv6EndIndex > portStartIndex) {
                    null
                } else {
                    it.substring(startIndex = portStartIndex + 1)
                }
            }?.toIntOrNull()

            return createUriFromValidatedParts(
                uriString = validationResult.value,
                scheme = validScheme,
                userInfo = userInfo,
                host = host,
                port = port,
                path = path,
                query = query,
                fragment = fragment
            )
        }
    }
}

/**
 * Retrieves a [Uri] from the provided [uriString]. If the provided [uriString] is valid and properly formatted, then a
 * [Uri] instance will be returned. If the provided [uriString] is invalid or is not properly formatted, then the null
 * will be returned.
 *
 * @see [Uri]
 * @see [UriString]
 */
public fun Uri.Companion.fromStringOrNull(uriString: UriString): Uri? =
    try {
        fromString(uriString = uriString)
    } catch (e: InvalidUriException) {
        null
    }

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
        urlValidator.validate(uriString)
    } else {
        uriValidator.validate(uriString)
    }

    when (validationResult) {
        is ValidationResult.Invalid -> throw InvalidUriException(message = "Invalid Uri from String = $uriString. Errors = ${validationResult.errors}")
        is ValidationResult.Valid -> {
            return createUriFromValidatedParts(
                uriString = validationResult.value,
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
): Uri = SimpleUri(
    uriString = uriString,
    scheme = scheme.lowercase(),
    userInfo = if (userInfo.isNullOrBlank()) null else userInfo,
    host = if (host.isNullOrBlank()) null else host,
    port = port,
    path = path.ifBlank { "" },
    query = if (query.isNullOrBlank()) null else query,
    fragment = if (fragment.isNullOrBlank()) null else fragment
)

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
): UriString = buildString {
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
