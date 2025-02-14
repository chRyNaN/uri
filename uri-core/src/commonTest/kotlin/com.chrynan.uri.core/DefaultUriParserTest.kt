package com.chrynan.uri.core

import kotlin.test.Test
import kotlin.test.assertEquals

class DefaultUriParserTest {

    @Test
    fun uri_with_all_components_parses_correctly() {
        assertUri(
            uri = "https://john.doe@www.example.com:123/forum/questions/?tag=networking&order=newest#top",
            scheme = "https",
            authority = "john.doe@www.example.com:123",
            userInfo = "john.doe",
            host = "www.example.com",
            port = 123,
            path = "/forum/questions/",
            query = "tag=networking&order=newest",
            fragment = "top"
        )
    }

    @Test
    fun uri_without_scheme_and_with_authority_parses_correctly() {
        assertUri(
            uri = "//john.doe@www.example.com:123/forum/questions/?tag=networking&order=newest#top",
            scheme = null,
            authority = "john.doe@www.example.com:123",
            userInfo = "john.doe",
            host = "www.example.com",
            port = 123,
            path = "/forum/questions/",
            query = "tag=networking&order=newest",
            fragment = "top"
        )
    }

    @Test
    fun uri_without_scheme_and_authority_parses_correctly() {
        assertUri(
            uri = "/john.doe@www.example.com:123/forum/questions/?tag=networking&order=newest#top",
            scheme = null,
            authority = null,
            userInfo = null,
            host = null,
            port = null,
            path = "/john.doe@www.example.com:123/forum/questions/",
            query = "tag=networking&order=newest",
            fragment = "top"
        )
    }

    @Test
    fun uri_without_scheme_and_authority_and_query_parses_correctly() {
        assertUri(
            uri = "/john.doe@www.example.com:123/forum/questions/#top",
            scheme = null,
            authority = null,
            userInfo = null,
            host = null,
            port = null,
            path = "/john.doe@www.example.com:123/forum/questions/",
            query = null,
            fragment = "top"
        )
    }

    @Test
    fun uri_only_with_path_parses_correctly() {
        assertUri(
            uri = "/john.doe@www.example.com:123/forum/questions/",
            scheme = null,
            authority = null,
            userInfo = null,
            host = null,
            port = null,
            path = "/john.doe@www.example.com:123/forum/questions/",
            query = null,
            fragment = null
        )
    }

    @Test
    fun https_uri_parses_correctly() {
        assertUri(
            uri = "https://john.doe@www.example.com:123/forum/questions/?tag=networking&order=newest#top",
            scheme = "https",
            authority = "john.doe@www.example.com:123",
            userInfo = "john.doe",
            host = "www.example.com",
            port = 123,
            path = "/forum/questions/",
            query = "tag=networking&order=newest",
            fragment = "top"
        )
    }

    @Test
    fun ldap_uri_parses_correctly() {
        assertUri(
            uri = "ldap://[2001:db8::7]/c=GB?objectClass?one",
            scheme = "ldap",
            authority = "[2001:db8::7]",
            userInfo = null,
            host = "[2001:db8::7]",
            port = null,
            path = "/c=GB",
            query = "objectClass?one",
            fragment = null
        )
    }

    @Test
    fun mailto_uri_parses_correctly() {
        assertUri(
            uri = "mailto:John.Doe@example.com",
            scheme = "mailto",
            authority = null,
            userInfo = null,
            host = null,
            port = null,
            path = "John.Doe@example.com",
            query = null,
            fragment = null
        )
    }

    @Test
    fun news_uri_parses_correctly() {
        assertUri(
            uri = "news:comp.infosystems.www.servers.unix",
            scheme = "news",
            authority = null,
            userInfo = null,
            host = null,
            port = null,
            path = "comp.infosystems.www.servers.unix",
            query = null,
            fragment = null
        )
    }

    @Test
    fun tel_uri_parses_correctly() {
        assertUri(
            uri = "tel:+1-816-555-1212",
            scheme = "tel",
            authority = null,
            userInfo = null,
            host = null,
            port = null,
            path = "+1-816-555-1212",
            query = null,
            fragment = null
        )
    }

    @Test
    fun telnet_uri_parses_correctly() {
        assertUri(
            uri = "telnet://192.0.2.16:80/",
            scheme = "telnet",
            authority = "192.0.2.16:80",
            userInfo = null,
            host = "192.0.2.16",
            port = 80,
            path = "/",
            query = null,
            fragment = null
        )
    }

    @Test
    fun urn_uri_parses_correctly() {
        assertUri(
            uri = "urn:oasis:names:specification:docbook:dtd:xml:4.1.2",
            scheme = "urn",
            authority = null,
            userInfo = null,
            host = null,
            port = null,
            path = "oasis:names:specification:docbook:dtd:xml:4.1.2",
            query = null,
            fragment = null
        )
    }

    private fun assertUri(
        uri: String,
        scheme: String?,
        authority: String?,
        userInfo: String?,
        host: String?,
        port: Int?,
        path: String,
        query: String?,
        fragment: String?
    ) {
        val result = DefaultUriParser.parse(value = UriString(value = uri))

        assertEquals(expected = scheme, actual = result.scheme)
        assertEquals(expected = authority, actual = result.authority)
        assertEquals(expected = userInfo, actual = result.userInfo)
        assertEquals(expected = host, actual = result.host)
        assertEquals(expected = port, actual = result.port)
        assertEquals(expected = path, actual = result.path)
        assertEquals(expected = query, actual = result.query)
        assertEquals(expected = fragment, actual = result.fragment)
    }
}
