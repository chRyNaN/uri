package com.chrynan.uri

interface Uri {

    val scheme: String
    val authority: String?
    val userInfo: String?
    val host: String?
    val port: Int?
    val path: String
    val query: String?
    val fragment: String?
    val schemeSpecificPart: String
}