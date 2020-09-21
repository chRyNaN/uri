package com.chrynan.uri.core

internal data class SimpleUri(
    override val scheme: String,
    override val userInfo: String? = null,
    override val host: String? = null,
    override val port: Int? = null,
    override val path: String,
    override val query: String? = null,
    override val fragment: String? = null
) : Uri {

    override val authority: String?
        get() {
            if (userInfo == null && host == null && port == null) return null

            return buildString {
                if (userInfo != null) {
                    append(userInfo)
                    append('@')
                }

                if (host != null) {
                    append(host)
                }

                if (port != null) {
                    append(':')
                    append(port)
                }
            }
        }

    override val schemeSpecificPart: String
        get() = buildString {
            if (authority != null) {
                append("//")
                append(authority)
            }

            append('/')
            append(path)

            if (query != null) {
                append('?')
                append(query)
            }
        }

    override val uriString: UriString
        get() = "$scheme$schemeSpecificPart"
}
