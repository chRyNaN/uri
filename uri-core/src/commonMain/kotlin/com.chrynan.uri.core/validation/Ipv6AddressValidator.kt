package com.chrynan.uri.core.validation

/**
 * Determines whether a [String] is in a valid IPv6 Address format.
 */
internal class Ipv6AddressValidator : Validator<String?, String> {

    companion object {

        private val IPV6_ADDRESS_REGEX = Regex(WebRegexConstants.IPV6_ADDRESS)
    }

    override fun validate(input: String?): ValidationResult<String> {
        if (input == null) return Invalid(Ipv6AddressValidationError.InputIsNull)

        if (!input.matches(IPV6_ADDRESS_REGEX)) return Invalid(Ipv6AddressValidationError.InvalidFormat)

        return Valid(input)
    }
}
