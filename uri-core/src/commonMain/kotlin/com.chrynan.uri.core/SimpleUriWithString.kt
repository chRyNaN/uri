package com.chrynan.uri.core

import kotlin.math.min

/**
 * An implementation of the [Uri] interface using the provided [uriString] to obtain the other parts of the [Uri]. Note
 * that the provided [uriString] is expected to be validated and formatted properly already. If the provided
 * [uriString] is not validated and formatted correctly, then the behavior of the other properties and functions is
 * undefined. The other parts of the [Uri] are obtained from the provided [uriString] using [Regex] or some other
 * means.
 */
data class SimpleUriWithString(override val uriString: UriString) : Uri {

    companion object {

        private const val authorityStartDelimiter = "://"
        private const val pathStartDelimiter = '/'
        private const val queryStartDelimiter = '?'
        private const val fragmentStartDelimiter = '#'
        private const val portStartDelimiter = ':'
        private const val userInfoEndDelimiter = '@'
        private const val schemeEndDelimiter = ':'
    }

    /**
     * Returns the starting index of the [authority] component. The resulting index would be the index after the
     * preceding [authority] delimiter ("://"). If there is no [authority] component (there is no delimiter), then -1
     * will be returned.
     */
    private val authorityStartIndex: Int by lazy {
        val startOfAuthorityIndex = uriString.indexOf(authorityStartDelimiter)

        if (startOfAuthorityIndex == -1) {
            startOfAuthorityIndex
        } else {
            startOfAuthorityIndex + authorityStartDelimiter.length
        }
    }

    /**
     * Returns the ending index (exclusive) of the [authority] component. If there is no [authority] component, then -1
     * will be returned.
     */
    private val authorityEndIndex: Int by lazy {
        if (authorityStartIndex == -1) {
            -1
        } else {
            val substring = uriString.substringAfter(authorityStartDelimiter)

            val pathIndex = substring.indexOf(pathStartDelimiter)

            if (pathIndex != -1) {
                pathIndex
            } else {
                val queryIndex = substring.indexOf(queryStartDelimiter)
                val fragmentIndex = substring.indexOf(fragmentStartDelimiter)

                min(queryIndex, fragmentIndex)
            }
        }
    }

    private val internalAuthority: String? by lazy {
        if (authorityStartIndex == -1 || authorityEndIndex == -1) {
            null
        } else {
            uriString.substring(startIndex = authorityStartIndex, endIndex = authorityEndIndex)
        }
    }

    override val scheme: String by lazy { uriString.substringBefore(schemeEndDelimiter) }

    override val userInfo: String? by lazy {
        val endIndex = internalAuthority?.indexOf(userInfoEndDelimiter) ?: -1

        if (authorityStartIndex == -1 || endIndex == -1) {
            null
        } else {
            internalAuthority?.substring(startIndex = authorityStartIndex, endIndex = endIndex)
        }
    }

    override val host: String? by lazy {
        internalAuthority?.let { auth ->
            var startIndex = auth.indexOf(userInfoEndDelimiter)

            if (startIndex == -1) {
                startIndex = 0
            } else {
                startIndex += 1
            }

            var endIndex = auth.lastIndexOf(portStartDelimiter)

            if (endIndex == -1) {
                endIndex = auth.length
            } else {
                // If there are two colons (::), then it is not a port delimiter
                if (endIndex - 1 != -1 && auth[endIndex - 1] == portStartDelimiter) {
                    endIndex = auth.length
                }
            }

            auth.substring(startIndex = startIndex, endIndex = endIndex)
        }
    }

    override val port: Int?
        get() = TODO("Not yet implemented")
    override val path: String
        get() = TODO("Not yet implemented")
    override val query: String?
        get() = TODO("Not yet implemented")
    override val fragment: String?
        get() = TODO("Not yet implemented")
}
