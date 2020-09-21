package com.chrynan.uri.core

/**
 * An implementation of the [Uri] interface using the provided [uriString] to obtain the other parts of the [Uri]. Note
 * that the provided [uriString] is expected to be validated and formatted properly already. If the provided
 * [uriString] is not validated and formatted correctly, then the behavior of the other properties and functions is
 * undefined. The other parts of the [Uri] are obtained from the provided [uriString] using [Regex] or some other
 * means.
 */
data class SimpleUriWithString(override val uriString: UriString) : Uri {

    override val scheme: String
        get() = TODO("Not yet implemented")
    override val authority: String?
        get() = TODO("Not yet implemented")
    override val userInfo: String?
        get() = TODO("Not yet implemented")
    override val host: String?
        get() = TODO("Not yet implemented")
    override val port: Int?
        get() = TODO("Not yet implemented")
    override val path: String
        get() = TODO("Not yet implemented")
    override val query: String?
        get() = TODO("Not yet implemented")
    override val fragment: String?
        get() = TODO("Not yet implemented")
    override val schemeSpecificPart: String
        get() = TODO("Not yet implemented")
}
