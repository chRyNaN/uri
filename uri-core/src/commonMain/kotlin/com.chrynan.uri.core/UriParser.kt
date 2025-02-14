package com.chrynan.uri.core

/**
 * A component that parses a [UriString] value into a [Uri].
 *
 * > [!Warning]
 * > The [UriString] provided to this function is already expected to be properly percent-encoded. It is not the
 * > responsibility of a [UriParser] to handle percent-encoding. If a [UriString], that contains components that are
 * > not properly percent-encoding, according to the URI Specification, is passed to a [UriParser], the [UriParser] may
 * > throw a [UriParseException] or result in undefined behavior, such as returning an invalid [Uri] model.
 */
public fun interface UriParser {

    /**
     * Parses the provided [UriString] value into a [Uri] or throws a [UriParseException] if the provided [UriString]
     * is not in a valid URI format.
     *
     * @throws [UriParseException] if the provided [UriString] [value] was not a properly formatted URI value or
     * another exception has occurred during parsing.
     *
     * @return The resulting [Uri] value.
     */
    @Throws(UriParseException::class)
    public fun parse(value: UriString): Uri

    public companion object
}

/**
 * Parses the provided [UriString] value into a [Uri] or returns `null` if the provided [UriString] is not in a valid
 * URI format.
 *
 * @throws [UriParseException] if the provided [UriString] [value] was not a properly formatted URI value or
 * another exception has occurred during parsing.
 *
 * @return The resulting [Uri] value.
 */
public fun UriParser.parseOrNull(value: UriString): Uri? =
    try {
        this.parse(value = value)
    } catch (_: UriParseException) {
        null
    }

/**
 * Parses the provided URI [String] value into a [Uri] or throws a [UriParseException] if the provided [UriString]
 * is not in a valid URI format.
 *
 * This is a convenience function for passing a [String] value into the [UriParser.parse] function without having to
 * first convert it to a [UriString].
 *
 * @throws [UriParseException] if the provided [UriString] [value] was not a properly formatted URI value or
 * another exception has occurred during parsing.
 *
 * @return The resulting [Uri] value.
 */
@Throws(UriParseException::class)
public inline fun UriParser.parse(value: String): Uri =
    this.parse(value = UriString(value = value))

/**
 * Parses the provided URI [String] value into a [Uri] or returns `null` if the provided URI [String] is not in a valid
 * URI format.
 *
 * This is a convenience function for passing a [String] value into the [UriParser.parseOrNull] function without having
 * to first convert it to a [UriString].
 *
 * @throws [UriParseException] if the provided [UriString] [value] was not a properly formatted URI value or
 * another exception has occurred during parsing.
 *
 * @return The resulting [Uri] value.
 */
public inline fun UriParser.parseOrNull(value: String): Uri? =
    try {
        this.parse(value = value)
    } catch (_: UriParseException) {
        null
    }

/**
 * Retrieves a default [UriParser] instance.
 */
public val UriParser.Companion.Default: UriParser
    get() = DefaultUriParser
