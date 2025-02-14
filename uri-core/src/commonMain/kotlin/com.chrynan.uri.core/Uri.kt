package com.chrynan.uri.core

/**
 * A [Uri] is a sequence of characters the uniquely identifies a particular resource, for example, a URL is a subset of
 * a [Uri].
 *
 * From [Section 3 of RFC 3986](https://datatracker.ietf.org/doc/html/rfc3986#section-3):
 *
 * > The generic URI syntax consists of a hierarchical sequence of
 * > components referred to as the scheme, authority, path, query, and
 * > fragment.
 * ```
 *           URI         = scheme ":" hier-part [ "?" query ] [ "#" fragment ]
 *
 *           hier-part   = "//" authority path-abempty
 *                       / path-absolute
 *                       / path-rootless
 *                       / path-empty
 * ```
 * > The scheme and path components are required, though the path may be
 * > empty (no characters).  When authority is present, the path must
 * > either be empty or begin with a slash ("/") character.  When
 * > authority is not present, the path cannot begin with two slash
 * > characters ("//").  These restrictions result in five different ABNF
 * > rules for a path (Section 3.3), only one of which will match any
 * > given URI reference.
 * > The following are two example URIs and their component parts:
 * ```
 *           foo://example.com:8042/over/there?name=ferret#nose
 *           \_/   \______________/\_________/ \_________/ \__/
 *            |           |            |            |        |
 *         scheme     authority       path        query   fragment
 *            |   _____________________|__
 *           / \ /                        \
 *           urn:example:animal:ferret:nose
 * ```
 *
 * @property [scheme] A [Uri] Scheme is the first portion of the [Uri] preceding the colon (':') character. It is
 * required for a valid URI, but can be omitted when the URI is actually a URI Reference (relative URI value). Common
 * URI Schemes include "http", "https", "ftp", "file", etc. The Scheme component may consist of any combination of
 * letters, digits, plus ('+'), period ('.'), or hyphen ('-'), but must begin with a letter to be considered valid.
 * This property will return the Scheme component of this [Uri] without the following colon (':'). An empty or blank
 * value will be represented as `null`.
 *
 * @property [authority] A [Uri] Authority is the portion of the URI after the [scheme] and the "://" sequence. It is
 * optional for the [Uri]. The Authority consists of three sub-parts of the URI: The [userInfo], [host], and [port].
 * This property will return the Authority component of this [Uri] without the preceding "://" sequence, in the
 * following form: `[userInfo]@[host]:[port]`. Note that each of the subcomponents are optional as well but if the
 * [userInfo] or [port] are included, then the [host] must be included.
 *
 * @property [userInfo] A [Uri] UserInfo subcomponent is a portion of the [authority] component. It is an optional
 * subcomponent that may consist of a username and an optional password preceded by a colon (':') and followed by a '@'
 * character. Note that if the [authority] is not present, then this will always return null. This is because this is a
 * subcomponent of the [authority] component. Even though some [path]s may have [userInfo] like components, it will not
 * be considered a [userInfo] component and this will return null. For example, for the following [Uri], the [userInfo]
 * property should return null: mailto:John.Doe@example.com. This property will return the UserInfo subcomponent
 * without the following '@' character, and in the following form: `username:password`.
 *
 * @property [host] A [Uri] Host subcomponent is a portion of the [authority] component. It is required if the
 * [authority] is present. It consists of either a registered name, such as a hostname, or an IP Address. IPv4
 * addresses must be in a dot-decimal notation, and IPv6 addresses must be enclosed in brackets. This property will
 * return the Host subcomponent without the following colon (':') character.
 *
 * @property [port] A [Uri] Port subcomponent is a portion of the [authority] component. It is an optional subcomponent
 * that comes after the [host] and a colon (':') character. It typically represents the [Int] port of the server to
 * use. This property will return the Port subcomponent without the preceding colon (':') character or the following
 * '/' character, in an [Int] form.
 *
 * @property [path] The following is taken from the Wikipedia definition of a [Uri] Path component (their definition is
 * well done so quoting instead of attempting to replicate):
 * > A path component, consisting of a sequence of path segments separated by a slash (/). A path is always defined
 * > for a URI, though the defined path may be empty (zero length). A segment may also be empty, resulting in two
 * > consecutive slashes (//) in the path component. A path component may resemble or map exactly to a file system
 * > path, but does not always imply a relation to one. If an authority component is present, then the path component
 * > must either be empty or begin with a slash (/). If an authority component is absent, then the path cannot begin
 * > with an empty segment, that is with two slashes (//), as the following characters would be interpreted as an
 * > authority component.[12] The final segment of the path may be referred to as a 'slug'.
 *
 * This property will return the Path component of the [Uri] including any slash ('/') characters. If there is an
 * [authority] then this [path] should begin with a slash ('/') character.
 *
 * @property [query] A [Uri] Query component is the portion of the [Uri] after the [path] component. It is an optional
 * component and is preceded by a question mark ('?'). It is a [String] of non-hierarchical data. Though its syntax is
 * not well-defined, by convention it is often a sequence of attribute-value pairs separated by a delimiter, typically
 * the '&' character. This property will return the Query component of the [Uri] without the preceding question mark
 * ('?') character and including any delimiters between the possible attribute-value pairs.
 *
 * @property [fragment] A [Uri] Fragment component is the portion of the URI after the [query] component. It is an
 * optional component that is preceded by a hash ('#') character. A Fragment contains a fragment identifier which links
 * to a secondary resource, such as a section header in an article. When the primary source is an HTML document,
 * typically the Fragment is an HTML identifier (id) value of a specific HTML element. This property will return this
 * Fragment component of the [Uri] without the preceding hash ('#') character.
 *
 * @property [uriString] Represents this [Uri] as a [UriString] value. This should be a [UriString] value consisting of
 * all the parts of the [Uri].
 *
 * @see [URI Specification RFC 3986](https://datatracker.ietf.org/doc/html/rfc3986)
 * @see [RFC 3305](https://datatracker.ietf.org/doc/html/rfc3305)
 * @see [URI Documentation](https://en.wikipedia.org/wiki/Uniform_Resource_Identifier)
 * @see [Reference Library](https://github.com/chRyNaN/uri)
 */
public interface Uri {

    /**
     * A [Uri] Scheme is the first portion of the [Uri] preceding the color (':') character. It is required for the
     * [Uri] to be considered valid. Common URI Schemes include "http", "https", "ftp", "file", etc. The Scheme
     * component may consist of any combination of letters, digits, plus ('+'), period ('.'), or hyphen ('-'), but must
     * begin with a letter to be considered valid.
     *
     * This property will return the Scheme component of this [Uri] without the following color (':').
     */
    public val scheme: String?

    /**
     * A [Uri] Authority is the portion of the URI after the [scheme] and the "://" sequence. It is optional for the
     * [Uri]. The Authority consists of three sub-parts of the URI: The [userInfo], [host], and [port].
     *
     * This property will return the Authority component of this [Uri] without the preceding "://" sequence, in the
     * following form: [userInfo]@[host]:[port]. Note that each of the sub components are optional as well but if the
     * [userInfo] or [port] are included, then the [host] must be included.
     */
    public val authority: String?

    /**
     * A [Uri] UserInfo subcomponent is a portion of the [authority] component. It is an optional subcomponent that may
     * consist of a user name and an optional password preceded by a colon (':') and followed by a '@' character.
     *
     * Note that if the [authority] is not present, then this will always return null. This is because this is a
     * subcomponent of the [authority] component. Even though some [path]s may have [userInfo] like components, it will
     * not be considered a [userInfo] component and this will return null. For example, for the following [Uri], the
     * [userInfo] property should return null: mailto:John.Doe@example.com
     *
     * This property will return the UserInfo subcomponent without the following '@' character, and in the following
     * form: username:password.
     */
    public val userInfo: String?

    /**
     * A [Uri] Host subcomponent is a portion of the [authority] component. It is required if the [authority] is
     * present. It consists of either a registered name, such as a hostname, or an IP Address. IPv4 addresses must be
     * in a dot-decimal notation, and IPv6 addresses must be enclosed in brackets.
     *
     * This property will return the Host subcomponent without the following colon (':') character.
     */
    public val host: String?

    /**
     * A [Uri] Port subcomponent is a portion of the [authority] component. It is an optional subcomponent that comes
     * after the [host] and a colon (':') character. It typically represents the [Int] port of the server to use.
     *
     * This property will return the Port subcomponent without the preceding colon (':') character or the following
     * '/' character, in an [Int] form.
     */
    public val port: Int?

    /**
     * The following is taken from the Wikipedia definition of a [Uri] Path component (their definition is well done so
     * quoting instead of attempting to replicate):
     *
     * > A path component, consisting of a sequence of path segments separated by a slash (/). A path is always defined
     * for a URI, though the defined path may be empty (zero length). A segment may also be empty, resulting in two
     * consecutive slashes (//) in the path component. A path component may resemble or map exactly to a file system
     * path, but does not always imply a relation to one. If an authority component is present, then the path component
     * must either be empty or begin with a slash (/). If an authority component is absent, then the path cannot begin
     * with an empty segment, that is with two slashes (//), as the following characters would be interpreted as an
     * authority component.[12] The final segment of the path may be referred to as a 'slug'.
     * <a href="https://en.wikipedia.org/wiki/Uniform_Resource_Identifier">Wikipedia URI Source</a>
     *
     * This property will return the Path component of the [Uri] including any slash ('/') characters. If there is an
     * [authority] then this [path] should begin with a slash ('/') character.
     */
    public val path: String

    /**
     * A [Uri] Query component is the portion of the [Uri] after the [path] component. It is an optional component and
     * is preceded by a question mark ('?'). It is a [String] of non-hierarchical data. Though its syntax is not well
     * defined, by convention it is often a sequence of attribute-value pairs separated by a delimiter, typically the
     * '&' character.
     *
     * This property will return the Query component of the [Uri] without the preceding question mark ('?') character
     * and including any delimiters between the possible attribute-value pairs.
     */
    public val query: String?

    /**
     * A [Uri] Fragment component is the portion of the URI after the [query] component. It is an optional component
     * that is preceded by a hash ('#') character. A Fragment contains a fragment identifier which links to a secondary
     * resource, such as a section header in an article. When the primary source is an HTML document, typically the
     * Fragment is an HTML identifier (id) value of a specific HTML element.
     *
     * This property will return this Fragment component of the [Uri] without the preceding hash ('#') character.
     */
    public val fragment: String?

    /**
     * A [Uri] Scheme Specific Part is the portion of the [Uri] after the [scheme] and the colon (':') character that
     * follows the [scheme]. It is the remainder of the [Uri] after "[scheme]:". It consists of the [authority], [path],
     * [query], and [fragment] components.
     *
     * This property will return the Scheme Specific Part of this [Uri].
     */
    public val schemeSpecificPart: String
        get() = buildString {
            if (authority != null) {
                append("//$authority")
            }

            append(path)

            if (query != null) {
                append("?$query")
            }

            if (fragment != null) {
                append("#$fragment")
            }
        }

    /**
     * Represents this [Uri] as a [UriString] value. This should be a [UriString] value consisting of all the parts of
     * the [Uri].
     */
    public fun toUriString(): UriString

    /**
     * The companion object for the [Uri] interface. This is useful for creating extension functions related to the
     * creation of a [Uri].
     */
    public companion object : UriParser by DefaultUriParser {

        /**
         * The [Set] of reserved [Char]s defined by the URI specification.
         *
         * @see [URI Specification](https://datatracker.ietf.org/doc/html/rfc3986#section-2.2)
         */
        public val reserved: Set<Char>
            get() = reservedCharacters

        /**
         * The [Set] of general delimiter [Char]s defined by the URI specification.
         *
         * @see [URI Specification](https://datatracker.ietf.org/doc/html/rfc3986#section-2.2)
         */
        public val generalDelimiters: Set<Char>
            get() = generalDelimiterCharacters

        /**
         * The [Set] of sub-delimiter [Char]s defined by the URI specification.
         *
         * @see [URI Specification](https://datatracker.ietf.org/doc/html/rfc3986#section-2.2)
         */
        public val subDelimiters: Set<Char>
            get() = subDelimiterCharacters

        /**
         * Determines whether this [Char] is one of the [reserved] characters defined by the URI specification.
         *
         * @see [URI Specification](https://datatracker.ietf.org/doc/html/rfc3986#section-2.2)
         */
        public inline val Char.isReserved: Boolean
            inline get() = reserved.contains(this)

        /**
         * Determines whether this [Char] is one of the [generalDelimiters] characters defined by the URI
         * specification.
         *
         * @see [URI Specification](https://datatracker.ietf.org/doc/html/rfc3986#section-2.2)
         */
        public inline val Char.isGeneralDelimiter: Boolean
            inline get() = generalDelimiters.contains(this)

        /**
         * Determines whether this [Char] is one of the [subDelimiters] characters defined by the URI specification.
         *
         * @see [URI Specification](https://datatracker.ietf.org/doc/html/rfc3986#section-2.2)
         */
        public inline val Char.isSubDelimiter: Boolean
            inline get() = subDelimiters.contains(this)
    }
}

private val generalDelimiterCharacters = setOf(
    ':',
    '/',
    '?',
    '#',
    '[',
    ']',
    '@'
)
private val subDelimiterCharacters = setOf(
    '!',
    '$',
    '&',
    '\'',
    '(',
    ')',
    '*',
    '+',
    ',',
    ';',
    '='
)
private val reservedCharacters = generalDelimiterCharacters + subDelimiterCharacters
