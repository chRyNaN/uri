package com.chrynan.uri.core

/**
 * An implementation of the [Uri] interface using the provided values. Each of the provided values is expected to be
 * validated and formatted upon construction of this class. If the provided values are not validated and formatted, the
 * behavior is undefined.
 */
internal data class DefaultUri internal constructor(
    override val scheme: String?,
    override val authority: String?,
    override val userInfo: String? = null,
    override val host: String? = null,
    override val port: Int? = null,
    override val path: String,
    override val query: String? = null,
    override val fragment: String? = null,
    private val uriString: UriString
) : Uri {

    override fun toUriString(): UriString = uriString
}
