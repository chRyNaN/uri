package com.chrynan.uri.core.validation

/**
 * A [ValidationError] that can occur when validating a URI with [UriValidator].
 */
internal sealed class UriValidationError(override val details: String? = null) : ValidationError {

    /**
     * The provided input value to the [UriValidator] was null. A null value is not a valid URI.
     */
    object InputIsNull : UriValidationError(details = "Input is not a valid URI because it is null.")

    /**
     * The provided input value to the [UriValidator] was blank. A blank value is not a valid URI.
     */
    object InputIsBlank : UriValidationError(details = "Input is not a valid URI because it is blank.")

    /**
     * The provided input value to the [UriValidator] contained a blank or empty Scheme which is invalid.
     */
    object SchemeIsBlank : UriValidationError(details = "Input is not a valid URI because the Scheme was blank.")

    /**
     * The provided input value to the [UriValidator] did not contain a Scheme separator (':').
     */
    object MissingSchemeSeparator :
        UriValidationError(details = "Input is not a valid URI because the Scheme was not distinguishable from the rest of the URI since there was no Scheme separator (':').")

    /**
     * The provided input value to the [UriValidator] contained a Scheme that did not begin with a letter which is
     * invalid.
     */
    object SchemeStartsWithInvalidCharacter :
        UriValidationError(details = "Input is not a valid URI because the first character in the Scheme was not a letter.")

    /**
     * The provided input value to the [UriValidator] contained an invalid Scheme, meaning the input was invalid.
     */
    data class InvalidSchemeFormat(val scheme: String) :
        UriValidationError(details = "Input is not a valid URI because the Scheme was invalid. Scheme = $scheme")

    data class InvalidCharactersUsed(val characters: String) :
        UriValidationError(details = "Input is not a valid URI because invalid characters were used. Characters = $characters")

    data class PortIsNotANumber(val port: String) :
        UriValidationError(details = "Input is not a valid URI because the port was not a valid number. port = $port")

    data class InvalidTextBetweenHostAndPort(val text: String) :
        UriValidationError(details = "Input is not a valid URI because there was invalid text between the Host and Port components. If the Port exists, it should immediately follow the Host. text = $text")

    data class InvalidIpv6Host(val host: String) :
        UriValidationError(details = "Input is not a valid URI because the IPv6 Address in the Host component is not valid.")

    object PathIsBlank :
        UriValidationError(details = "Input is not a valid URI because the Path component was blank. The Path component can be empty but cannot have a blank space.")

    data class InvalidPathStart(val characters: String) :
        UriValidationError(details = "Input is not a valid URI because the Path component had invalid starting characters. characters = $characters")

    object FirstPathSegmentContainsColon :
        UriValidationError(details = "Input is not a valid URI because the first Path Segment in the relative URI contains a colon which is invalid.")
}
