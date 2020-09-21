package com.chrynan.uri.core

fun uriFromString(uriString: UriString): Uri {
    try {
        val schemeEndIndex = uriString.indexOf(char = ':')
        val fragmentStartIndex = uriString.indexOf(char = '#', startIndex = schemeEndIndex) + 1

        val scheme = uriString.substring(startIndex = 0, endIndex = schemeEndIndex)
        val fragment = uriString.substring(fragmentStartIndex)
        val path = getPath(uriString = uriString, ssi = schemeEndIndex)
        val query = getQuery(uriString = uriString, ssi = schemeEndIndex, fsi = fragmentStartIndex)
        val authority = getAuthority(uriString = uriString, ssi = schemeEndIndex)
        val userInfo = authority?.let { getUserInfo(authority = it) }
        val host = authority?.let { getHost(authority = it) }
        val port = authority?.let { getPort(authority = it) }

        return fromParts(
            scheme = scheme,
            fragment = fragment,
            path = path,
            query = query,
            userInfo = userInfo,
            host = host,
            port = port
        )
    } catch (e: Exception) {
        throw InvalidUriException(message = "Invalid Uri from String = $uriString")
    }
}

fun uriFromStringOrNull(uriString: UriString): Uri? =
    try {
        uriFromString(uriString = uriString)
    } catch (e: InvalidUriException) {
        null
    }

private fun getPath(uriString: String, ssi: Int): String {
    val length = uriString.length

    // Find start of path
    val pathStart =
        if ((length > ssi + 2) and (uriString[ssi + 1] == '/') and (uriString[ssi + 2] == '/')) {
            // Skip over authority to path
            val authorityEnd = uriString.indexOfAny(chars = charArrayOf('/', '\\'), startIndex = ssi + 3)
            val queryOrFragmentStart = uriString.indexOfAny(chars = charArrayOf('?', '#'), startIndex = ssi + 3)

            if (authorityEnd == -1) return "" // Empty path
            if ((queryOrFragmentStart != -1) and (queryOrFragmentStart < authorityEnd)) return "" // Empty path

            authorityEnd
        } else {
            // Path starts immediately after scheme separator
            ssi + 1
        }

    val endIndex = uriString.indexOfAny(chars = charArrayOf('?', '#'), startIndex = pathStart)

    // Find end of path
    val pathEnd = if (endIndex == -1) length else endIndex

    return uriString.substring(pathStart, pathEnd)
}

private fun getQuery(uriString: String, ssi: Int, fsi: Int): String? {
    val qsi = uriString.indexOf(char = '?', startIndex = ssi)

    if (qsi == -1) return null

    if (fsi == -1) return uriString.substring(startIndex = qsi + 1)

    return if (fsi < qsi) null else uriString.substring(startIndex = qsi + 1, endIndex = fsi)
}

private fun getAuthority(uriString: String, ssi: Int): String? {
    val length = uriString.length

    // If "//" follows the scheme separator, we have an authority
    return if ((length > ssi + 2) and (uriString[ssi + 1] == '/') and (uriString[ssi + 2] == '/')) {
        // We have an authority
        val end = uriString.indexOfAny(chars = charArrayOf('/', '\\', '?', '#'), startIndex = ssi + 3)

        uriString.substring(ssi + 3, if (end == -1) length else end)
    } else {
        null
    }
}

private fun getUserInfo(authority: String): String? {
    val end = authority.lastIndexOf(char = '@')
    return if (end == -1) null else authority.substring(startIndex = 0, endIndex = end)
}

private fun getHost(authority: String): String? {
    // Parse out user info and then port.
    val userInfoSeparator = authority.lastIndexOf(char = '@')
    val portSeparator = authority.indexOf(char = ':', startIndex = userInfoSeparator)

    return if (portSeparator == -1) authority.substring(startIndex = userInfoSeparator + 1) else authority.substring(
        startIndex = userInfoSeparator + 1,
        endIndex = portSeparator
    )
}

private fun getPort(authority: String): Int {
    val userInfoSeparator = authority.lastIndexOf(char = '@')
    val portSeparator = authority.indexOf(char = ':', startIndex = userInfoSeparator)

    if (portSeparator == -1) return -1

    val portString = authority.substring(startIndex = portSeparator + 1)

    return try {
        portString.toInt()
    } catch (e: NumberFormatException) {
        -1
    }
}
