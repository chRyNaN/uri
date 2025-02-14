package com.chrynan.uri.core

internal data object DefaultUriParser : UriParser {

    override fun parse(value: UriString): Uri {
        try {
            // Let's be lenient and trim any leading whitespace
            val schemeStartIndex = value.value.indexOfFirst { char ->
                !char.isWhitespace()
            }.takeIf { index ->
                index != -1
            } ?: 0
            val scheme = value.getScheme(startIndex = schemeStartIndex)

            val authorityStartIndex = if (scheme != null) {
                scheme.index + scheme.value.length
            } else {
                schemeStartIndex
            }
            val authority = value.getAuthority(startIndex = authorityStartIndex)

            val userInfo = authority?.let { getUserInformation(authority = it.value) }

            val host = authority?.let {
                val hostStartIndex = if (userInfo != null) {
                    userInfo.index + userInfo.value.length
                } else {
                    0
                }

                if (hostStartIndex >= authority.value.length) {
                    null
                } else {
                    getHost(
                        authority = it.value,
                        startIndex = hostStartIndex
                    )
                }
            }

            val port = authority?.let {
                val portStartIndex = if (host != null) {
                    host.index + host.value.length
                } else {
                    0
                }

                if (portStartIndex >= authority.value.length) {
                    null
                } else {
                    getPort(
                        authority = authority.value,
                        startIndex = host?.let { host ->
                            host.index + host.value.length
                        } ?: 0
                    )
                }
            }

            val pathStartIndex = if (authority != null) {
                authority.index + authority.value.length
            } else if (scheme != null) {
                scheme.index + scheme.value.length + 1
            } else {
                schemeStartIndex
            }
            val path = if (pathStartIndex >= value.value.length) {
                null
            } else {
                value.getPath(
                    containsAuthority = authority != null,
                    startIndex = pathStartIndex
                )
            }

            val queryStartIndex = if (path != null) {
                path.index + path.value.length
            } else {
                pathStartIndex
            }
            val query = if (queryStartIndex >= value.value.length || value.value[queryStartIndex] != '?') {
                null
            } else {
                value.getQuery(startIndex = queryStartIndex)
            }

            val fragmentStartIndex = if (query != null) {
                query.index + query.value.length
            } else {
                queryStartIndex
            }
            val fragment =
                if (fragmentStartIndex >= value.value.length || value.value[fragmentStartIndex] != '#') {
                    null
                } else {
                    value.getFragment(startIndex = fragmentStartIndex)
                }

            return DefaultUri(
                scheme = scheme?.value,
                authority = authority?.value,
                userInfo = userInfo?.value,
                host = host?.value,
                port = port?.value,
                path = path?.value ?: "",
                query = query?.value,
                fragment = fragment?.value,
                uriString = value
            )
        } catch (e: UriParseException) {
            throw e
        } catch (e: Exception) {
            throw UriParseException(cause = e)
        }
    }

    @Suppress("MemberVisibilityCanBePrivate") // Visible for testing
    internal fun UriString.getScheme(startIndex: Int = 0): IndexedValue<String>? {
        require(startIndex in 0..<this.value.length) { "Provided `startIndex` value must be in the range of the UriString." }

        var schemeEndIndex = -1
        var pathStartIndex = -1

        var i = startIndex

        while (i < this.value.length) {
            val char = this.value[i]

            when {
                char == ':' -> {
                    schemeEndIndex = i
                    break
                }

                char == '/' -> {
                    pathStartIndex = i
                    break
                }

                char.isWhitespace() -> throw UriParseException(message = "Invalid URI value: URI String must not contain non-encoded whitespace character.")
            }

            i++
        }

        val scheme = when {
            // If a path segment occurs before a colon character, then it is a relative URI reference and there is no scheme.
            schemeEndIndex != -1 && pathStartIndex != -1 && pathStartIndex < schemeEndIndex -> null

            // If there is no colon character, then it is a relative URI reference (or invalid URI) and there is no scheme.
            schemeEndIndex == -1 -> null

            // The string up to (excluding) the first instance of the colon character is the scheme.
            else -> this.value.substring(startIndex = 0, endIndex = schemeEndIndex)
        }

        // Schemes are case-insensitive, but by convention are lowercase. Format the value to be lowercase so that we
        // can compare schemes without having to worry about formatting later.
        val formattedScheme = scheme?.lowercase()

        return formattedScheme?.let {
            IndexedValue(
                index = 0,
                value = it
            )
        }
    }

    @Suppress("MemberVisibilityCanBePrivate") // Visible for testing
    internal fun UriString.getAuthority(
        startIndex: Int // The provided start index must come after the scheme component if there is one.
    ): IndexedValue<String>? {
        require(startIndex in 0..<this.value.length) { "Provided `startIndex` value must be in the range of the UriString." }

        // The authority component of a URI comes after the scheme component and is preceded by two forward-slashes.
        // Sometimes the scheme component is not present, in which case we check if the first component is actually an
        // authority component (starts with two forward-slashes). Otherwise, there is no authority component.
        val startChar = this.value[startIndex]

        val authorityStartIndex = when {
            startChar == ':' && startIndex + 2 < this.value.length && this.value[startIndex + 1] == '/' && this.value[startIndex + 2] == '/' -> startIndex + 3

            startChar == '/' && startIndex + 1 < this.value.length && this.value[startIndex + 1] == '/' -> startIndex + 2

            else -> return null
        }

        if (authorityStartIndex == -1 || authorityStartIndex > this.value.lastIndex) {
            return null
        }

        // The authority component is preceded by a double slash ("//") and is terminated by the next slash ("/"),
        // question mark ("?"), or number sign ("#") character, or by the end of the URI.
        var authorityEndIndex = this.value.length

        var i = authorityStartIndex
        while (i < this.value.length) {
            val char = this.value[i]

            when {
                char == '/' -> {
                    authorityEndIndex = i
                    break
                }

                char == '?' -> {
                    authorityEndIndex = i
                    break
                }

                char == '#' -> {
                    authorityEndIndex = i
                    break
                }

                char.isWhitespace() -> throw UriParseException(message = "Invalid URI value: URI String must not contain non-encoded whitespace character.")
            }

            i++
        }

        val authority =
            this.value.substring(startIndex = authorityStartIndex, endIndex = authorityEndIndex)

        return IndexedValue(
            index = authorityStartIndex,
            value = authority
        )
    }

    @Suppress("MemberVisibilityCanBePrivate") // Visible for testing
    internal fun getUserInformation(
        authority: String
    ): IndexedValue<String>? {
        val userInfoEndIndex = authority.indexOf('@')

        if (userInfoEndIndex == -1) {
            return null
        }

        return IndexedValue(
            index = 0,
            value = authority.substring(startIndex = 0, endIndex = userInfoEndIndex)
        )
    }

    @Suppress("MemberVisibilityCanBePrivate") // Visible for testing
    internal fun getHost(
        authority: String,
        startIndex: Int // Must start after the user info
    ): IndexedValue<String>? {
        require(startIndex in authority.indices) { "Provided `startIndex` value must be in the range of the UriString." }

        val hostStartIndex = if (authority[startIndex] == '@') {
            startIndex + 1
        } else {
            startIndex
        }
        var hostEndIndex = authority.length

        if (hostStartIndex >= authority.length) {
            return null
        }

        var inBrackets = false

        var i = hostStartIndex
        while (i < authority.length) {
            val char = authority[i]

            when {
                char == '[' -> {
                    inBrackets = true
                }

                char == ']' -> {
                    inBrackets = false
                }

                !inBrackets && char == ':' -> {
                    hostEndIndex = i
                    break
                }

                !inBrackets && char == '/' -> {
                    hostEndIndex = i
                    break
                }

                char.isWhitespace() -> throw UriParseException(message = "Invalid URI value: URI String must not contain non-encoded whitespace character.")
            }

            i++
        }

        val host = authority.substring(startIndex = hostStartIndex, endIndex = hostEndIndex)

        if (host.isEmpty()) {
            return null
        }

        // The host component is case-insensitive, so make sure it is all lowercase so that we don't have to convert it later when we compare values.
        // Note that a host can be an IPv4, IPv6, or IP Future Address, in which case the lowercase function should ignore the non-character values.
        val formattedHost = host.lowercase()

        return IndexedValue(
            index = hostStartIndex,
            value = formattedHost
        )
    }

    @Suppress("MemberVisibilityCanBePrivate") // Visible for testing
    internal fun getPort(
        authority: String,
        startIndex: Int // Must start after the host
    ): IndexedValue<Int>? {
        require(startIndex in authority.indices) { "Provided `startIndex` value must be in the range of the UriString." }
        require(authority[startIndex] == ':') { "Provided `startIndex` value for `getPort` function must start with the ':' character." }

        val portStartIndex = startIndex + 1
        var portEndIndex = authority.length

        if (portStartIndex >= authority.length) {
            return null
        }

        var i = portStartIndex
        while (i < authority.length) {
            val char = authority[i]

            when {
                char == '/' -> {
                    portEndIndex = i
                    break
                }

                char.isWhitespace() -> throw UriParseException(message = "Invalid URI value: URI String must not contain non-encoded whitespace character.")
            }

            i++
        }

        val port = authority.substring(startIndex = portStartIndex, endIndex = portEndIndex)

        if (port.isEmpty()) {
            return null
        }

        val formattedPort = port.toIntOrNull()
            ?: throw UriParseException(message = "Invalid URI port component. URI port component must be an integer value: $port")

        return IndexedValue(
            index = portStartIndex,
            value = formattedPort
        )
    }

    @Suppress("MemberVisibilityCanBePrivate") // Visible for testing
    internal fun UriString.getPath(
        containsAuthority: Boolean,
        startIndex: Int // Must come after scheme and authority
    ): IndexedValue<String> {
        require(startIndex in 0..<this.value.length) { "Provided `startIndex` value must be in the range of the UriString." }

        var pathEndIndex = this.value.length

        var i = startIndex
        while (i < this.value.length) {
            val char = this.value[i]

            when {
                char == '?' -> {
                    pathEndIndex = i
                    break
                }

                char == '#' -> {
                    pathEndIndex = i
                    break
                }

                char.isWhitespace() -> throw UriParseException(message = "Invalid URI value: URI String must not contain non-encoded whitespace character.")
            }

            i++
        }

        val path = this.value.substring(startIndex = startIndex, endIndex = pathEndIndex)

        if (containsAuthority) {
            if (path.isNotEmpty() && path.first() != '/') {
                throw UriParseException(message = "A URI value with an authority must either have an empty path or the path must begin with a forward slash character.")
            }
        } else {
            if (path.getOrNull(0) == '/' && path.getOrNull(1) == '/') {
                throw UriParseException(message = "A URI value without an authority must not begin with two forward slash characters.")
            }

            // We don't really need to check for the condition if there is no scheme, then the path shouldn't contain a
            // colon character for the first path part, because that would already be parsed as a scheme or fail before
            // this point.
        }

        return IndexedValue(
            index = startIndex,
            value = path
        )
    }

    @Suppress("MemberVisibilityCanBePrivate") // Visible for testing
    internal fun UriString.getQuery(
        startIndex: Int // Must be after the path component
    ): IndexedValue<String>? {
        require(startIndex in 0..<this.value.length) { "Provided `startIndex` value must be in the range of the UriString." }
        require(this.value[startIndex] == '?') { "Provided `startIndex` value for `getQuery` function must start with the '?' character. startIndex = $startIndex; character = ${this.value[startIndex]}" }

        if (startIndex + 1 >= this.value.length) {
            return null
        }

        val queryStartIndex = startIndex + 1
        var queryEndIndex = this.value.length

        var i = queryStartIndex
        while (i < this.value.length) {
            val char = this.value[i]

            when {
                char == '#' -> {
                    queryEndIndex = i
                    break
                }

                char.isWhitespace() -> throw UriParseException(message = "Invalid URI value: URI String must not contain non-encoded whitespace character.")
            }

            i++
        }

        val query = this.value.substring(startIndex = queryStartIndex, endIndex = queryEndIndex)

        if (query.isEmpty()) {
            return null
        }

        return IndexedValue(
            index = queryStartIndex,
            value = query
        )
    }

    @Suppress("MemberVisibilityCanBePrivate") // Visible for testing
    internal fun UriString.getFragment(
        startIndex: Int // Must start after query
    ): IndexedValue<String>? {
        require(startIndex in 0..<this.value.length) { "Provided `startIndex` value must be in the range of the UriString." }
        require(this.value[startIndex] == '#') { "Provided `startIndex` value for `getFragment` function must start with the '#' character." }

        if (startIndex + 1 >= this.value.length) {
            return null
        }

        val query = this.value.substring(startIndex = startIndex + 1, endIndex = this.value.length)

        if (query.isEmpty()) {
            return null
        }

        return IndexedValue(
            index = startIndex + 1,
            value = query
        )
    }
}
