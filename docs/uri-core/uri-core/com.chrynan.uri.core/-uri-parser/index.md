//[uri-core](../../../index.md)/[com.chrynan.uri.core](../index.md)/[UriParser](index.md)

# UriParser

fun interface [UriParser](index.md)

A component that parses a [UriString](../-uri-string/index.md) value into a [Uri](../-uri/index.md).

!Warning The [UriString](../-uri-string/index.md) provided to this function is already expected to be properly percent-encoded. It is not the responsibility of a [UriParser](index.md) to handle percent-encoding. If a [UriString](../-uri-string/index.md), that contains components that are not properly percent-encoding, according to the URI Specification, is passed to a [UriParser](index.md), the [UriParser](index.md) may throw a [UriParseException](../-uri-parse-exception/index.md) or result in undefined behavior, such as returning an invalid [Uri](../-uri/index.md) model.

#### Inheritors

| |
|---|
| [Companion](../-uri/-companion/index.md) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [common]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [parse](parse.md) | [common]<br>abstract fun [parse](parse.md)(value: [UriString](../-uri-string/index.md)): [Uri](../-uri/index.md)<br>Parses the provided [UriString](../-uri-string/index.md) value into a [Uri](../-uri/index.md) or throws a [UriParseException](../-uri-parse-exception/index.md) if the provided [UriString](../-uri-string/index.md) is not in a valid URI format. |
| [parse](../parse.md) | [common]<br>inline fun [UriParser](index.md).[parse](../parse.md)(value: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)): [Uri](../-uri/index.md)<br>Parses the provided URI [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html) value into a [Uri](../-uri/index.md) or throws a [UriParseException](../-uri-parse-exception/index.md) if the provided [UriString](../-uri-string/index.md) is not in a valid URI format. |
| [parseOrNull](../parse-or-null.md) | [common]<br>fun [UriParser](index.md).[parseOrNull](../parse-or-null.md)(value: [UriString](../-uri-string/index.md)): [Uri](../-uri/index.md)?<br>Parses the provided [UriString](../-uri-string/index.md) value into a [Uri](../-uri/index.md) or returns `null` if the provided [UriString](../-uri-string/index.md) is not in a valid URI format.<br>[common]<br>inline fun [UriParser](index.md).[parseOrNull](../parse-or-null.md)(value: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)): [Uri](../-uri/index.md)?<br>Parses the provided URI [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html) value into a [Uri](../-uri/index.md) or returns `null` if the provided URI [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html) is not in a valid URI format. |
