package com.chrynan.uri.core

import kotlin.test.Test
import kotlin.test.assertEquals

class UriFromStringTest {

    @Test
    fun httpUriParsesCorrectly() {
        val uriString = "https://john.doe@www.example.com:123/forum/questions/?tag=networking&order=newest#top"
        val uri = Uri.fromString(uriString)

        assertEquals(actual = uri.scheme, expected = "https")
        assertEquals(actual = uri.userInfo, expected = "john.doe")
        assertEquals(actual = uri.host, expected = "www.example.com")
        assertEquals(actual = uri.port, expected = 123)
        assertEquals(actual = uri.authority, expected = "john.doe@www.example.com:123")
        assertEquals(actual = uri.path, expected = "/forum/questions/")
        assertEquals(actual = uri.query, expected = "tag=networking&order=newest")
        assertEquals(actual = uri.fragment, expected = "top")
        assertEquals(
            actual = uri.schemeSpecificPart,
            expected = "//john.doe@www.example.com:123/forum/questions/?tag=networking&order=newest#top"
        )
        assertEquals(actual = uri.uriString, expected = uriString)
    }

    @Test
    fun ldapUriParsesCorrectly() {
        val uriString = "ldap://[2001:db8::7]/c=GB?objectClass?one"
        val uri = Uri.fromString(uriString)

        assertEquals(actual = uri.scheme, expected = "ldap")
        assertEquals(actual = uri.userInfo, expected = null)
        assertEquals(actual = uri.host, expected = "[2001:db8::7]")
        assertEquals(actual = uri.port, expected = null)
        assertEquals(actual = uri.authority, expected = "[2001:db8::7]")
        assertEquals(actual = uri.path, expected = "/c=GB")
        assertEquals(actual = uri.query, expected = "objectClass?one")
        assertEquals(actual = uri.fragment, expected = null)
        assertEquals(
            actual = uri.schemeSpecificPart,
            expected = "//[2001:db8::7]/c=GB?objectClass?one"
        )
        assertEquals(actual = uri.uriString, expected = uriString)
    }

    @Test
    fun mailtoUriParsesCorrectly() {
        val uriString = "mailto:John.Doe@example.com"
        val uri = Uri.fromString(uriString)

        assertEquals(actual = uri.scheme, expected = "mailto")
        assertEquals(actual = uri.userInfo, expected = null)
        assertEquals(actual = uri.host, expected = null)
        assertEquals(actual = uri.port, expected = null)
        assertEquals(actual = uri.authority, expected = null)
        assertEquals(actual = uri.path, expected = "John.Doe@example.com")
        assertEquals(actual = uri.query, expected = null)
        assertEquals(actual = uri.fragment, expected = null)
        assertEquals(
            actual = uri.schemeSpecificPart,
            expected = "John.Doe@example.com"
        )
        assertEquals(actual = uri.uriString, expected = uriString)
    }

    @Test
    fun newsUriParsesCorrectly() {
        val uriString = "news:comp.infosystems.www.servers.unix"
        val uri = Uri.fromString(uriString)

        assertEquals(actual = uri.scheme, expected = "news")
        assertEquals(actual = uri.userInfo, expected = null)
        assertEquals(actual = uri.host, expected = null)
        assertEquals(actual = uri.port, expected = null)
        assertEquals(actual = uri.authority, expected = null)
        assertEquals(actual = uri.path, expected = "comp.infosystems.www.servers.unix")
        assertEquals(actual = uri.query, expected = null)
        assertEquals(actual = uri.fragment, expected = null)
        assertEquals(
            actual = uri.schemeSpecificPart,
            expected = "comp.infosystems.www.servers.unix"
        )
        assertEquals(actual = uri.uriString, expected = uriString)
    }

    @Test
    fun telUriParsesCorrectly() {
        val uriString = "tel:+1-816-555-1212"
        val uri = Uri.fromString(uriString)

        assertEquals(actual = uri.scheme, expected = "tel")
        assertEquals(actual = uri.userInfo, expected = null)
        assertEquals(actual = uri.host, expected = null)
        assertEquals(actual = uri.port, expected = null)
        assertEquals(actual = uri.authority, expected = null)
        assertEquals(actual = uri.path, expected = "+1-816-555-1212")
        assertEquals(actual = uri.query, expected = null)
        assertEquals(actual = uri.fragment, expected = null)
        assertEquals(
            actual = uri.schemeSpecificPart,
            expected = "+1-816-555-1212"
        )
        assertEquals(actual = uri.uriString, expected = uriString)
    }

    @Test
    fun telnetUriParsesCorrectly() {
        val uriString = "telnet://192.0.2.16:80/"
        val uri = Uri.fromString(uriString)

        assertEquals(actual = uri.scheme, expected = "telnet")
        assertEquals(actual = uri.userInfo, expected = null)
        assertEquals(actual = uri.host, expected = "192.0.2.16:80")
        assertEquals(actual = uri.port, expected = null)
        assertEquals(actual = uri.authority, expected = "192.0.2.16:80")
        assertEquals(actual = uri.path, expected = "/")
        assertEquals(actual = uri.query, expected = null)
        assertEquals(actual = uri.fragment, expected = null)
        assertEquals(
            actual = uri.schemeSpecificPart,
            expected = "192.0.2.16:80/"
        )
        assertEquals(actual = uri.uriString, expected = uriString)
    }

    @Test
    fun urnUriParsesCorrectly() {
        val uriString = "urn:oasis:names:specification:docbook:dtd:xml:4.1.2"
        val uri = Uri.fromString(uriString)

        assertEquals(actual = uri.scheme, expected = "urn")
        assertEquals(actual = uri.userInfo, expected = null)
        assertEquals(actual = uri.host, expected = null)
        assertEquals(actual = uri.port, expected = null)
        assertEquals(actual = uri.authority, expected = null)
        assertEquals(actual = uri.path, expected = "oasis:names:specification:docbook:dtd:xml:4.1.2")
        assertEquals(actual = uri.query, expected = null)
        assertEquals(actual = uri.fragment, expected = null)
        assertEquals(
            actual = uri.schemeSpecificPart,
            expected = "oasis:names:specification:docbook:dtd:xml:4.1.2"
        )
        assertEquals(actual = uri.uriString, expected = uriString)
    }
}
