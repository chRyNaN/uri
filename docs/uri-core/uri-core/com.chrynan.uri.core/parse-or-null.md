//[uri-core](../../index.md)/[com.chrynan.uri.core](index.md)/[parseOrNull](parse-or-null.md)

# parseOrNull

[common]\
fun [UriParser](-uri-parser/index.md).[parseOrNull](parse-or-null.md)(value: [UriString](-uri-string/index.md)): [Uri](-uri/index.md)?

Parses the provided [UriString](-uri-string/index.md) value into a [Uri](-uri/index.md) or returns `null` if the provided [UriString](-uri-string/index.md) is not in a valid URI format.

#### Return

The resulting [Uri](-uri/index.md) value.

#### Throws

| | |
|---|---|
| [UriParseException](-uri-parse-exception/index.md) | if the provided [UriString](parse-or-null.md) was not a properly formatted URI value or another exception has occurred during parsing. |

[common]\
inline fun [UriParser](-uri-parser/index.md).[parseOrNull](parse-or-null.md)(value: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)): [Uri](-uri/index.md)?

Parses the provided URI [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html) value into a [Uri](-uri/index.md) or returns `null` if the provided URI [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html) is not in a valid URI format.

This is a convenience function for passing a [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html) value into the [UriParser.parseOrNull](parse-or-null.md) function without having to first convert it to a [UriString](-uri-string/index.md).

#### Return

The resulting [Uri](-uri/index.md) value.

#### Throws

| | |
|---|---|
| [UriParseException](-uri-parse-exception/index.md) | if the provided [UriString](parse-or-null.md) was not a properly formatted URI value or another exception has occurred during parsing. |
