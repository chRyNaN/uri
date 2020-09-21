package com.chrynan.uri.core

internal data class SimpleUriWithParts(
    override val scheme: String,
    override val userInfo: String? = null,
    override val host: String? = null,
    override val port: Int? = null,
    override val path: String,
    override val query: String? = null,
    override val fragment: String? = null
) : Uri {

    override val uriString: UriString
        get() = "$scheme:$schemeSpecificPart"
}
