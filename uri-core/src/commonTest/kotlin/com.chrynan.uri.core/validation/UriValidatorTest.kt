package com.chrynan.uri.core.validation

import kotlin.test.Test

class UriValidatorTest {

    private val validator = UriValidator()

    @Test
    fun nullInputIsInvalid() {
        assertInvalid(validator.validate(null))
    }

    @Test
    fun emptyInputIsInvalid() {
        assertInvalid(validator.validate(""))
    }

    @Test
    fun blankInputIsInvalid() {
        assertInvalid(validator.validate(" "))
    }

    @Test
    fun inputWithoutSchemeSeparatorIsInvalid() {
        assertInvalid(validator.validate("Invalid"))
    }

    @Test
    fun httpsUriWithoutSchemeIsInvalid() {
        val result =
            validator.validate("//john.doe@www.example.com:123/forum/questions/?tag=networking&order=newest#top")

        assertInvalid(result)
    }

    @Test
    fun httpsUriWithoutSchemeAndStartingWithColonIsInvalid() {
        val result =
            validator.validate("://john.doe@www.example.com:123/forum/questions/?tag=networking&order=newest#top")

        assertInvalid(result)
    }

    @Test
    fun validHttpsUriIsValid() {
        val result =
            validator.validate("https://john.doe@www.example.com:123/forum/questions/?tag=networking&order=newest#top")

        assertValid(result)
    }

    @Test
    fun validHttpsUriWithoutQueryAndWithFragmentAfterSlashIsValid() {
        val result = validator.validate("https://john.doe@www.example.com:123/forum/questions/#top")

        assertValid(result)
    }

    @Test
    fun validHttpsUriWithoutQueryAndWithFragmentIsValid() {
        val result = validator.validate("https://john.doe@www.example.com:123/forum/questions#top")

        assertValid(result)
    }

    @Test
    fun validHttpsUriWithoutFragmentIsValid() {
        val result =
            validator.validate("https://john.doe@www.example.com:123/forum/questions/?tag=networking&order=newest")

        assertValid(result)
    }

    @Test
    fun validHttpsUriWithoutFragmentAndWithoutQueryEndingInSlashIsValid() {
        val result = validator.validate("https://john.doe@www.example.com:123/forum/questions/")

        assertValid(result)
    }

    @Test
    fun validHttpsUriWithoutFragmentAndWithoutQueryIsValid() {
        val result = validator.validate("https://john.doe@www.example.com:123/forum/questions")

        assertValid(result)
    }

    @Test
    fun validHttpsUriWithoutUserInfoIsValid() {
        val result = validator.validate("https://www.example.com:123/forum/questions")

        assertValid(result)
    }

    @Test
    fun validHttpsUriWithoutPortIsValid() {
        val result = validator.validate("https://john.doe@www.example.com/forum/questions")

        assertValid(result)
    }

    @Test
    fun validHttpsUriWithEmptyPortIsValid() {
        val result = validator.validate("https://john.doe@www.example.com:/forum/questions")

        assertValid(result)
    }

    @Test
    fun validHttpsUriWithoutUserInfoAndWithoutPortIsValid() {
        val result = validator.validate("https://www.example.com/forum/questions")

        assertValid(result)
    }

    @Test
    fun validHttpsUriWithoutUserInfoAndWithEmptyPortIsValid() {
        val result = validator.validate("https://www.example.com:/forum/questions")

        assertValid(result)
    }

    //@Test TODO fix
    //fun validLdapUriIsValid() {
    //    val result = validator.validate("ldap://[2001:db8::7]/c=GB?objectClass?one")
    //
    //    assertValid(result)
    //}

    @Test
    fun validMailtoUriIsValid() {
        val result = validator.validate("mailto:John.Doe@example.com")

        assertValid(result)
    }

    @Test
    fun validNewsUriIsValid() {
        val result = validator.validate("news:comp.infosystems.www.servers.unix")

        assertValid(result)
    }

    @Test
    fun validTelUriIsValid() {
        val result = validator.validate("tel:+1-816-555-1212")

        assertValid(result)
    }

    @Test
    fun validTelnetUriIsValid() {
        val result = validator.validate("telnet://192.0.2.16:80/")

        assertValid(result)
    }

    @Test
    fun validUrnUriIsValid() {
        val result = validator.validate("urn:oasis:names:specification:docbook:dtd:xml:4.1.2")

        assertValid(result)
    }
}
