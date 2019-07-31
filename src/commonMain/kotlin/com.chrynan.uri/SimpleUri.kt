package com.chrynan.uri

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

            val sb = StringBuilder()

            if (userInfo != null) {
                sb.append(userInfo)
                sb.append('@')
            }

            if (host != null) {
                sb.append(host)
            }

            if (port != null) {
                sb.append(':')
                sb.append(port)
            }

            return sb.toString()
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
}