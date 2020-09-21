package com.chrynan.uri.core

/**
 * An implementation of the [Uri] interface using the provided parts. Note that the provided parts are already expected
 * to be validated. If the provided parts are not validated and formatted correctly, then the behavior of properties
 * and functions is undefined.
 */
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
