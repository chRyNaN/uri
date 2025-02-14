//[uri-core](../../../index.md)/[com.chrynan.uri.core](../index.md)/[UriString](index.md)

# UriString

[common]\
@[JvmInline](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.jvm/-jvm-inline/index.html)

@Serializable

value class [UriString](index.md)(val value: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html))

A [UriString](index.md) is a wrapper around a [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html) representation of a complete [Uri](../-uri/index.md).

Note that no verification is performed to assert that a [UriString](index.md) is valid and properly formatted. To verify that a [UriString](index.md) is valid, convert it to a [Uri](../-uri/index.md) using a [UriParser](../-uri-parser/index.md).

## Constructors

| | |
|---|---|
| [UriString](-uri-string.md) | [common]<br>constructor(value: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [value](value.md) | [common]<br>val [value](value.md): [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html) |
