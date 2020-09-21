@file:Suppress("unused")

package com.chrynan.uri.core

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
    val authorityIsAbsent = (userInfo == null) and (host == null) and (port == null)

    if (authorityIsAbsent and (path.startsWith("/"))) throw InvalidUriException(message = "Provided Path cannot start with an empty Path Segment if there is no Authority present: path = $path")

    return SimpleUriWithParts(
        scheme = scheme,
        userInfo = userInfo,
        host = host,
        port = port,
        path = path,
        query = query,
        fragment = fragment
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

val Uri.pathSegments: List<String>
    get() = path.split('/', ignoreCase = true, limit = path.length)

val Uri.queryParameters: Map<String, String>
    get() {
        val q = query ?: return emptyMap()

        return q.split('&', ';', ignoreCase = true, limit = q.length)
            .associate {
                val equalsIndex = it.indexOf(char = '=')
                val key = if (equalsIndex == -1) it else it.substring(startIndex = 0, endIndex = equalsIndex)
                val value = if (equalsIndex == -1) "" else it.substring(startIndex = equalsIndex)

                key to value
            }
    }
