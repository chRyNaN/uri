//[uri-core](../../../index.md)/[com.chrynan.uri.core](../index.md)/[Uri](index.md)

# Uri

interface [Uri](index.md)

A [Uri](index.md) is a sequence of characters the uniquely identifies a particular resource, for example, a URL is a subset of a [Uri](index.md).

From [Section 3 of RFC 3986](https://datatracker.ietf.org/doc/html/rfc3986#section-3):

The generic URI syntax consists of a hierarchical sequence of components referred to as the scheme, authority, path, query, and fragment.

```kotlin
    URI         = scheme ":" hier-part [ "?" query ] [ "#" fragment ]

    hier-part   = "//" authority path-abempty
                / path-absolute
                / path-rootless
                / path-empty
```

The scheme and path components are required, though the path may be empty (no characters).  When authority is present, the path must either be empty or begin with a slash (&quot;/&quot;) character.  When authority is not present, the path cannot begin with two slash characters (&quot;//&quot;).  These restrictions result in five different ABNF rules for a path (Section 3.3), only one of which will match any given URI reference. The following are two example URIs and their component parts:

```kotlin
      foo://example.com:8042/over/there?name=ferret#nose
      \_/   \______________/\_________/ \_________/ \__/
       |           |            |            |        |
    scheme     authority       path        query   fragment
       |   _____________________|__
      / \ /                        \
      urn:example:animal:ferret:nose
```

#### See also

| | |
|---|---|
|  | [Reference Library](https://github.com/chRyNaN/uri) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [common]<br>object [Companion](-companion/index.md) : [UriParser](../-uri-parser/index.md)<br>The companion object for the [Uri](index.md) interface. This is useful for creating extension functions related to the creation of a [Uri](index.md). |

## Properties

| Name | Summary |
|---|---|
| [authority](authority.md) | [common]<br>abstract val [authority](authority.md): [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)?<br>A [Uri](index.md) Authority is the portion of the URI after the [scheme](scheme.md) and the &quot;://&quot; sequence. It is optional for the [Uri](index.md). The Authority consists of three sub-parts of the URI: The [userInfo](user-info.md), [host](host.md), and [port](port.md). |
| [fragment](fragment.md) | [common]<br>abstract val [fragment](fragment.md): [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)?<br>A [Uri](index.md) Fragment component is the portion of the URI after the [query](query.md) component. It is an optional component that is preceded by a hash ('#') character. A Fragment contains a fragment identifier which links to a secondary resource, such as a section header in an article. When the primary source is an HTML document, typically the Fragment is an HTML identifier (id) value of a specific HTML element. |
| [host](host.md) | [common]<br>abstract val [host](host.md): [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)?<br>A [Uri](index.md) Host subcomponent is a portion of the [authority](authority.md) component. It is required if the [authority](authority.md) is present. It consists of either a registered name, such as a hostname, or an IP Address. IPv4 addresses must be in a dot-decimal notation, and IPv6 addresses must be enclosed in brackets. |
| [path](path.md) | [common]<br>abstract val [path](path.md): [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)<br>The following is taken from the Wikipedia definition of a [Uri](index.md) Path component (their definition is well done so quoting instead of attempting to replicate): |
| [pathSegments](../path-segments.md) | [common]<br>val [Uri](index.md).[pathSegments](../path-segments.md): [List](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)&gt;<br>Retrieves a [List](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/-list/index.html) of [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)s each representing the individual Path Segments of this [Uri](index.md). A Path Segment is a portion of the [Uri.path](path.md) component which is separated by slash characters ('/'). If there are no Path Segments, then this will return an empty [List](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/-list/index.html). |
| [port](port.md) | [common]<br>abstract val [port](port.md): [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)?<br>A [Uri](index.md) Port subcomponent is a portion of the [authority](authority.md) component. It is an optional subcomponent that comes after the [host](host.md) and a colon (':') character. It typically represents the [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html) port of the server to use. |
| [query](query.md) | [common]<br>abstract val [query](query.md): [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)?<br>A [Uri](index.md) Query component is the portion of the [Uri](index.md) after the [path](path.md) component. It is an optional component and is preceded by a question mark ('?'). It is a [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html) of non-hierarchical data. Though its syntax is not well defined, by convention it is often a sequence of attribute-value pairs separated by a delimiter, typically the '&' character. |
| [scheme](scheme.md) | [common]<br>abstract val [scheme](scheme.md): [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)?<br>A [Uri](index.md) Scheme is the first portion of the [Uri](index.md) preceding the color (':') character. It is required for the [Uri](index.md) to be considered valid. Common URI Schemes include &quot;http&quot;, &quot;https&quot;, &quot;ftp&quot;, &quot;file&quot;, etc. The Scheme component may consist of any combination of letters, digits, plus ('+'), period ('.'), or hyphen ('-'), but must begin with a letter to be considered valid. |
| [schemeSpecificPart](scheme-specific-part.md) | [common]<br>open val [schemeSpecificPart](scheme-specific-part.md): [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)<br>A [Uri](index.md) Scheme Specific Part is the portion of the [Uri](index.md) after the [scheme](scheme.md) and the colon (':') character that follows the [scheme](scheme.md). It is the remainder of the [Uri](index.md) after &quot;[scheme](scheme.md):&quot;. It consists of the [authority](authority.md), [path](path.md), [query](query.md), and [fragment](fragment.md) components. |
| [slug](../slug.md) | [common]<br>val [Uri](index.md).[slug](../slug.md): [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)?<br>Retrieves the final path segment of the [Uri.path](path.md) without the preceding slash character ('/'). If the final path segment is blank then null will be returned. |
| [userInfo](user-info.md) | [common]<br>abstract val [userInfo](user-info.md): [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)?<br>A [Uri](index.md) UserInfo subcomponent is a portion of the [authority](authority.md) component. It is an optional subcomponent that may consist of a user name and an optional password preceded by a colon (':') and followed by a '@' character. |

## Functions

| Name | Summary |
|---|---|
| [queryParameters](../query-parameters.md) | [common]<br>fun [Uri](index.md).[queryParameters](../query-parameters.md)(vararg delimiters: [Char](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-char/index.html) = charArrayOf('&amp;', ';'), ignoreCase: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html) = true): [Map](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)?&gt;<br>Retrieves a [Map](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/-map/index.html) of [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html) keys and values representing the individual Query Parameters in this [Uri](index.md). A Query Parameter is a portion of the [Uri.query](query.md) component that is separated by other Query Parameters by the provided [delimiters](../query-parameters.md). A Query Parameter may be in the key-value form with an equals character ('=') separating the key and value. The resulting [Map](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/-map/index.html) will have a key matching the Query Parameters keys and corresponding values. If a Query Parameter doesn't have a value (the equals sign isn't present), then the value will be considered null. If there are no Query Parameters, then this will return an empty [Map](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/-map/index.html). |
| [toUriString](to-uri-string.md) | [common]<br>abstract fun [toUriString](to-uri-string.md)(): [UriString](../-uri-string/index.md)<br>Represents this [Uri](index.md) as a [UriString](../-uri-string/index.md) value. This should be a [UriString](../-uri-string/index.md) value consisting of all the parts of the [Uri](index.md). |
