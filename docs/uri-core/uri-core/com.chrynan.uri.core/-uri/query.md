//[uri-core](../../../index.md)/[com.chrynan.uri.core](../index.md)/[Uri](index.md)/[query](query.md)

# query

[common]\
abstract val [query](query.md): [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)?

A [Uri](index.md) Query component is the portion of the [Uri](index.md) after the [path](path.md) component. It is an optional component and is preceded by a question mark ('?'). It is a [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html) of non-hierarchical data. Though its syntax is not well defined, by convention it is often a sequence of attribute-value pairs separated by a delimiter, typically the '&' character.

This property will return the Query component of the [Uri](index.md) without the preceding question mark ('?') character and including any delimiters between the possible attribute-value pairs.
