package com.chrynan.uri.core

data class SimpleUriString(override val uriString: UriString) : Uri {

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
