package com.chrynan.uri.core

import kotlin.test.Test
import kotlin.test.assertEquals

class UriTest {

    @Test
    fun `valid uri parses correctly`() {
        val uri =
            Uri.parse("https://john.doe@www.example.com:123/forum/questions/?tag=networking&order=newest#top")

        assertEquals(
            expected = uri.toUriString().value,
            actual = "https://john.doe@www.example.com:123/forum/questions/?tag=networking&order=newest#top"
        )
    }

    @Test
    fun `uri with user info parses correctly`() {
        val uri = Uri.parse("https://username:password@example.com")

        assertEquals(
            expected = uri.toUriString().value,
            actual = "https://username:password@example.com"
        )
    }
}
