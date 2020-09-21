package com.chrynan.uri.core

fun uri(
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

    return SimpleUri(
        scheme = scheme,
        userInfo = userInfo,
        host = host,
        port = port,
        path = path,
        query = query,
        fragment = fragment
    )
}

fun uriOrNull(
    scheme: String,
    userInfo: String? = null,
    host: String? = null,
    port: Int? = null,
    path: String,
    query: String? = null,
    fragment: String? = null
): Uri? =
    try {
        uri(
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

val Uri.slug: String?
    get() {
        val i = path.lastIndexOf(char = '/')

        if ((i == -1) and (path.isNotBlank())) return path
        if (i == -1) return null

        val s = path.substring(startIndex = i)

        if (s.isBlank()) return null

        return s
    }

val Uri.containsFragment: Boolean
    get() = fragment == null

val Uri.isAbsolute: Boolean
    get() = scheme.isNotBlank()

val Uri.isRelative: Boolean
    get() = !isAbsolute

val Uri.isHierarchical: Boolean
    get() {
        if (isRelative) return true

        if (schemeSpecificPart.startsWith('/')) return true

        return false
    }

val Uri.isOpaque: Boolean
    get() = !isHierarchical

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

fun Uri.toDecodedString(): UriString = buildString {
    append(scheme)
    append(':')
    append(schemeSpecificPart)

    if (!fragment.isNullOrBlank()) {
        append('#')
        append(fragment)
    }
}
