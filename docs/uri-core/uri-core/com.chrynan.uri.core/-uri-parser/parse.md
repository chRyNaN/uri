//[uri-core](../../../index.md)/[com.chrynan.uri.core](../index.md)/[UriParser](index.md)/[parse](parse.md)

# parse

[common]\
abstract fun [parse](parse.md)(value: [UriString](../-uri-string/index.md)): [Uri](../-uri/index.md)

Parses the provided [UriString](../-uri-string/index.md) value into a [Uri](../-uri/index.md) or throws a [UriParseException](../-uri-parse-exception/index.md) if the provided [UriString](../-uri-string/index.md) is not in a valid URI format.

#### Return

The resulting [Uri](../-uri/index.md) value.

#### Throws

| | |
|---|---|
| [UriParseException](../-uri-parse-exception/index.md) | if the provided [UriString](parse.md) was not a properly formatted URI value or another exception has occurred during parsing. |
