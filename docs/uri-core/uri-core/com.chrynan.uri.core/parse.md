//[uri-core](../../index.md)/[com.chrynan.uri.core](index.md)/[parse](parse.md)

# parse

[common]\
inline fun [UriParser](-uri-parser/index.md).[parse](parse.md)(value: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)): [Uri](-uri/index.md)

Parses the provided URI [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html) value into a [Uri](-uri/index.md) or throws a [UriParseException](-uri-parse-exception/index.md) if the provided [UriString](-uri-string/index.md) is not in a valid URI format.

This is a convenience function for passing a [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html) value into the [UriParser.parse](-uri-parser/parse.md) function without having to first convert it to a [UriString](-uri-string/index.md).

#### Return

The resulting [Uri](-uri/index.md) value.

#### Throws

| | |
|---|---|
| [UriParseException](-uri-parse-exception/index.md) | if the provided [UriString](parse.md) was not a properly formatted URI value or another exception has occurred during parsing. |
