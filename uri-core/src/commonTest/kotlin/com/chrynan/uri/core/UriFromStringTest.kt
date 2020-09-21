package com.chrynan.uri.core

import kotlin.test.Test
import kotlin.test.assertEquals

class UriFromStringTest {

    @Test
    fun uriWithAllPartsParsesCorrectly() {
        val uri =
            Uri.fromString("https://john.doe@www.example.com:123/forum/questions/?tag=networking&order=newest#top")

        assertEquals(uri.scheme, "https")
        assertEquals(uri.userInfo, "john.doe")
        assertEquals(uri.host, "www.example.com")
        assertEquals(uri.port, 123)
        assertEquals(uri.authority, "john.doe@www.example.com:123")
        assertEquals(uri.path, "/forum/questions/")
        assertEquals(uri.query, "tag=networking&order=newest")
        assertEquals(uri.fragment, "top")
        assertEquals(
            uri.schemeSpecificPart,
            "//john.doe@www.example.com:123/forum/questions/?tag=networking&order=newest#top"
        )
    }
}
