package com.chrynan.uri.core.validation

/**
 * A [Validator] that validates whether the provided [String] input is a valid URI. This [UriValidator] attempts to
 * validate according to the RFC-3986 specification: https://tools.ietf.org/html/rfc3986#section-3.1
 *
 * Note that this [UriValidator] doesn't perform specific validation for every URI Scheme type. Instead it just tries
 * to assert that the provided [String] input is in a generally valid URI format.
 *
 * Note that this [UriValidator] does not validate relative URIs (URIs without the Scheme component).
 */
internal class UriValidator : Validator<String?, String> {

    companion object {

        private const val SCHEME_SEPARATOR = ':'
        private const val AUTHORITY_SEPARATOR = "://"
        private const val QUERY_SEPARATOR = '?'
        private const val FRAGMENT_SEPARATOR = '#'
        private const val PATH_SEPARATOR = '/'
        private const val USER_INFO_SEPARATOR = '@'
        private const val IPV6_START_SEPARATOR = '['
        private const val IPV6_END_SEPARATOR = ']'
        private const val PORT_SEPARATOR = ':'

        private val SCHEME_REGEX = Regex("^([a-zA-Z0-9+.-]+)")
        private val LETTER_REGEX = Regex("^[a-zA-Z]")
    }

    private val ipv4AddressValidator = Ipv4AddressValidator()
    private val ipv6AddressValidator = Ipv6AddressValidator()

    override fun validate(input: String?): ValidationResult<String> {
        if (input == null) return Invalid(UriValidationError.InputIsNull)

        if (input.isBlank()) return Invalid(UriValidationError.InputIsBlank)

        val schemeEndIndex = input.indexOf(SCHEME_SEPARATOR)

        if (schemeEndIndex == -1) return Invalid(UriValidationError.MissingSchemeSeparator)

        val schemeResult = validateScheme(input.substring(startIndex = 0, endIndex = schemeEndIndex))

        if (schemeResult is ValidationResult.Invalid) return schemeResult

        val authority = getAuthority(input)
        val authorityResult = validateAuthority(authority)

        if (authorityResult is ValidationResult.Invalid) return Invalid(authorityResult.errors.toList())

        val path = getPath(input)
        val pathResult = validatePath(path = path, uriContainsAuthority = authority != null)

        if (pathResult is ValidationResult.Invalid) return pathResult

        // The Query and Fragment components are generally unconstrained. There are more scheme-specific restrictions
        // on their content and structure but since this Validator doesn't perform scheme-specific validation, there's
        // nothing to validate for these components. So we can just consider them valid and at this point, consider the
        // URI valid.

        // The scheme is technically valid when it contains uppercase letters, but it is recommended that the Scheme
        // consist of only lowercase letters. So we normalize it for the returned result. Everything else can remain
        // the same.
        val uriWithoutScheme = input.substringAfter(SCHEME_SEPARATOR)
        val output = "${(schemeResult as ValidationResult.Valid).value}$SCHEME_SEPARATOR$uriWithoutScheme"

        return Valid(output)
    }

    private fun validateScheme(scheme: String): ValidationResult<String> {
        if (scheme.isBlank()) return Invalid(UriValidationError.SchemeIsBlank)

        if (!LETTER_REGEX.matches(scheme[0].toString())) return Invalid(UriValidationError.SchemeStartsWithInvalidCharacter)

        if (!scheme.matches(SCHEME_REGEX)) return Invalid(UriValidationError.InvalidSchemeFormat(scheme))

        return Valid(scheme.lowercase())
    }

    private fun getAuthorityStartIndex(input: String): Int {
        val index = input.indexOf(AUTHORITY_SEPARATOR)

        if (index == -1) return index

        return index + AUTHORITY_SEPARATOR.length
    }

    private fun getAuthority(input: String): String? {
        val startIndex = getAuthorityStartIndex(input)

        if (startIndex == -1) return null

        val substring = input.substring(startIndex = startIndex)

        val pathIndex = substring.indexOf(PATH_SEPARATOR)
        val queryIndex = substring.indexOf(QUERY_SEPARATOR)
        val fragmentIndex = substring.indexOf(FRAGMENT_SEPARATOR)
        val endIndex = listOf(pathIndex, queryIndex, fragmentIndex, substring.length)
            .filter { it != -1 }
            .minOf { it }

        return substring.substring(startIndex = 0, endIndex = endIndex)
    }

    private fun validateAuthority(authority: String?): ValidationResult<String?> {
        // The absence of an authority component is allowed in URIs
        if (authority == null) return Valid(authority)

        val userInfoEndIndex = authority.indexOf(USER_INFO_SEPARATOR)
        val userInfo =
            if (userInfoEndIndex == -1) null else authority.substring(startIndex = 0, endIndex = userInfoEndIndex)

        if (userInfo != null && userInfo.contains(USER_INFO_SEPARATOR)) {
            return Invalid(UriValidationError.InvalidCharactersUsed("$USER_INFO_SEPARATOR"))
        }

        val ipv6HostStartIndex = authority.indexOf(IPV6_START_SEPARATOR)
        val ipv6HostEndIndex = authority.indexOf(IPV6_END_SEPARATOR)

        val portSeparatorIndex = authority.lastIndexOf(PORT_SEPARATOR)

        // According to RFC-3986, a Host can be either:
        // * IP Literal = [ ( IPv6Address / IPvFuture ) ]
        // * IPv4 Address in decimal-dot notation = 255.255.255.1
        // * Registered Name (similar to but not exclusively a domain name)
        // * Empty Registered Name if there is a scheme specific default
        if (ipv6HostStartIndex != -1 && ipv6HostEndIndex != -1) {
            // The Host is an IPv6 Address
            if (portSeparatorIndex != -1 && portSeparatorIndex > ipv6HostEndIndex && ipv6HostEndIndex + 1 != portSeparatorIndex) {
                return Invalid(
                    UriValidationError.InvalidTextBetweenHostAndPort(
                        authority.substring(
                            startIndex = ipv6HostStartIndex + 1,
                            endIndex = portSeparatorIndex
                        )
                    )
                )
            }

            if (portSeparatorIndex > ipv6HostEndIndex) {
                val portString = when {
                    portSeparatorIndex == -1 -> null
                    portSeparatorIndex + 1 >= authority.length -> null
                    else -> authority.substring(startIndex = portSeparatorIndex + 1)
                }

                if (portString != null && portString.toIntOrNull() == null) {
                    return Invalid(UriValidationError.PortIsNotANumber(portString))
                }
            }

            val host = authority.substring(startIndex = ipv6HostStartIndex + 1, endIndex = ipv6HostEndIndex)

            // This might be too restrictive. The RFC-3986 specification allows for future IP Address formats and with
            // an optional version preface. This might not support that.
            // TODO be more permissive with IPv6 Addresses?
            if (ipv6AddressValidator.validate(host) is ValidationResult.Invalid) {
                return Invalid(UriValidationError.InvalidIpv6Host(host))
            }

            return Valid(authority)
        } else if (ipv6HostStartIndex == -1 && ipv6HostEndIndex != -1) {
            // Contains ending IPv6 bracket but not starting one which is an error
            return Invalid(UriValidationError.InvalidCharactersUsed("$IPV6_END_SEPARATOR"))
        } else if (ipv6HostStartIndex != -1 && ipv6HostEndIndex == -1) {
            // Contains starting IPv6 bracket but not ending one which is an error
            return Invalid(UriValidationError.InvalidCharactersUsed("$IPV6_START_SEPARATOR"))
        } else {
            val portString = when {
                portSeparatorIndex == -1 -> null
                portSeparatorIndex + 1 >= authority.length -> null
                else -> authority.substring(startIndex = portSeparatorIndex + 1)
            }

            if (portString != null && portString.toIntOrNull() == null) {
                return Invalid(UriValidationError.PortIsNotANumber(portString))
            }

            // The Host is either an IPv4 Address or a Registered Name
            val hostStartIndex = if (userInfoEndIndex == -1) 0 else userInfoEndIndex + 1
            val hostEndIndex = if (portSeparatorIndex == -1) authority.length else portSeparatorIndex

            val host = authority.substring(startIndex = hostStartIndex, endIndex = hostEndIndex)

            // According to RFC-3986 an empty host is considered valid for certain schemes that define a default host.
            // It is up to the scheme specific validation to determine whether an empty host is valid. But for this
            // validator, it is considered valid, as this validator doesn't perform scheme specific validation.
            if (host.isEmpty()) return Valid(authority)

            if (ipv4AddressValidator.validate(host) is ValidationResult.Valid) return Valid(authority)

            // Only a single '@' character is allowed and that is for the separator between the User Info and Host
            // components. If the host contains another, then that is an error.
            if (host.contains(USER_INFO_SEPARATOR)) return Invalid(UriValidationError.InvalidCharactersUsed("$USER_INFO_SEPARATOR"))

            // Other than the basic validation already performed, we don't do any more validation on a Registered Host
            // Name as that's more a scheme specific validation logic. Note that this means that we can be overly
            // permissive in our validation. TODO be more restrictive for the Host name if possible?
            return Valid(authority)
        }
    }

    private fun getPathStartIndex(input: String): Int {
        val authorityStartIndex = getAuthorityStartIndex(input)

        if (authorityStartIndex != -1) {
            val substring = input.substring(startIndex = authorityStartIndex)

            return authorityStartIndex + substring.indexOf(PATH_SEPARATOR)
        }

        return input.indexOf(SCHEME_SEPARATOR) + 1
    }

    private fun getPath(input: String): String {
        val pathStartIndex = getPathStartIndex(input)

        if (pathStartIndex == -1) return ""

        val substring = input.substring(startIndex = pathStartIndex)

        val queryIndex = substring.indexOf(QUERY_SEPARATOR)
        val fragmentIndex = substring.indexOf(FRAGMENT_SEPARATOR)
        val endIndex = listOf(queryIndex, fragmentIndex, substring.length)
            .filter { it != -1 }
            .minOf { it }

        return substring.substring(startIndex = 0, endIndex = endIndex)
    }

    private fun validatePath(
        path: String,
        uriContainsAuthority: Boolean,
        isUriRelative: Boolean = false
    ): ValidationResult<String> {
        if (path.isEmpty()) return Valid(path)

        // If the URI Path component is blank (contains non-encoded space characters) then it is considered invalid.
        if (path.isBlank()) return Invalid(UriValidationError.PathIsBlank)

        // If a URI has an Authority component, then the Path component must either be empty or start with a slash
        // ('/') character. Since we already checked for an empty path, we just have to check for a slash.
        if (uriContainsAuthority && !path.startsWith("/")) {
            return Invalid(UriValidationError.InvalidPathStart("${path[0]}"))
        }

        // If a URI does not contain an Authority component, then the Path component cannot begin with two slashes.
        if (!uriContainsAuthority && path.startsWith("//")) {
            return Invalid(UriValidationError.InvalidPathStart("//"))
        }

        // If the URI is relative, then the first Path Segment can't contain a colon (':').
        if (isUriRelative) {
            val pathWithoutStartingSlash = when {
                path.startsWith("//") -> path.substringAfter("//")
                path.startsWith("/") -> path.substringAfter("/")
                else -> path
            }

            val firstPathSegmentEndIndex = pathWithoutStartingSlash.indexOf(PATH_SEPARATOR)

            val firstSegment = if (firstPathSegmentEndIndex == -1) {
                pathWithoutStartingSlash
            } else {
                pathWithoutStartingSlash.substring(startIndex = 0, endIndex = firstPathSegmentEndIndex)
            }

            if (firstSegment.contains(':')) {
                return Invalid(UriValidationError.FirstPathSegmentContainsColon)
            }
        }


        // Everything else is schema specific, or dependent on the URI implementation, which this Validator doesn't
        // handle. So the path is considered valid.
        return Valid(path)
    }
}
