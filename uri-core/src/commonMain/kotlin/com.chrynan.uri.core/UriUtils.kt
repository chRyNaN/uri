@file:Suppress("unused")

package com.chrynan.uri.core

import com.chrynan.validator.UrlValidator
import com.chrynan.validator.ValidationResult

private val validator = UrlValidator()

/**
 * Retrieves a [Uri] from the provided parts. If the provided parts are valid and properly formatted, then a [Uri] is
 * returned. If the provided parts are invalid or not properly formatted, then an [InvalidUriException] is thrown.
 *
 * @see [Uri]
 * @see [UriString]
 */
fun Uri.Companion.fromParts(
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

    val result = validator.validate(uriString)

    if (result is ValidationResult.Invalid) throw InvalidUriException(message = "Invalid Uri from String = $uriString. Errors = ${result.errors}")

    return SimpleUriWithParts(
        scheme = formattedScheme,
        userInfo = formattedUserInfo,
        host = formattedHost,
        port = port,
        path = formattedPath,
        query = formattedQuery,
        fragment = formattedFragment
    )
}

/**
 * Retrieves a [Uri] from the provided parts. If the provided parts are valid and properly formatted, then a [Uri] is
 * returned. If the provided parts are invalid or not properly formatted, then null is returned.
 *
 * @see [Uri]
 * @see [UriString]
 */
fun Uri.Companion.fromPartsOrNull(
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
 * Retrieves the final path segment of the [Uri.path] without the preceding slash character ('/'). If the final path
 * segment is blank then null will be returned.
 */
val Uri.slug: String?
    get() {
        val i = path.lastIndexOf(char = '/')

        if ((i == -1) and (path.isNotBlank())) return path
        if (i == -1) return null
        if (i + 1 > path.length) return null

        val s = path.substring(startIndex = i + 1)

        if (s.isBlank()) return null

        return s
    }

/**
 * Retrieves a [List] of [String]s each representing the individual Path Segments of this [Uri]. A Path Segment is a
 * portion of the [Uri.path] component which is separated by slash characters ('/'). If there are no Path Segments,
 * then this will return an empty [List].
 */
val Uri.pathSegments: List<String>
    get() = path.split('/')

/**
 * Retrieves a [Map] of [String] keys and values representing the individual Query Parameters in this [Uri]. A Query
 * Parameter is a portion of the [Uri.query] component that is separated by other Query Parameters by the provided
 * [delimiters]. A Query Parameter may be in the key-value form with an equals character ('=') separating the key and
 * value. The resulting [Map] will have a key matching the Query Parameters keys and corresponding values. If a Query
 * Parameter doesn't have a value (the equals sign isn't present), then the value will be considered null. If there are
 * no Query Parameters, then this will return an empty [Map].
 *
 * @param [delimiters] The delimiters that separate the Query Parameters in the [Uri.query] component.
 * @param [ignoreCase] Whether to ignore the case (upper/lower) of the delimiters or not.
 */
fun Uri.queryParameters(
    vararg delimiters: Char = charArrayOf('&', ';'),
    ignoreCase: Boolean = true
): Map<String, String?> {
    val q = query ?: return emptyMap()

    return q.split(*delimiters, ignoreCase = ignoreCase)
        .associate {
            val equalsIndex = it.indexOf(char = '=')
            val key = if (equalsIndex == -1) it else it.substring(startIndex = 0, endIndex = equalsIndex)
            val value = if (equalsIndex == -1) null else it.substring(startIndex = equalsIndex)

            key to value
        }
}

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
