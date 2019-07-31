package com.chrynan.uri

internal data class SimpleUri(
    override val scheme: String,
    override val authority: String? = null,
    override val userInfo: String? = null,
    override val host: String? = null,
    override val port: Int? = null,
    override val path: String,
    override val query: String? = null,
    override val fragment: String? = null
) : Uri {

    override val schemeSpecificPart: String
        get() = buildString {
            authority?.let(::append)
            append(path)
            query?.let(::append)
        }
}