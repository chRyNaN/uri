//[uri-core](../../../index.md)/[com.chrynan.uri.core](../index.md)/[UriAsStringSerializer](index.md)

# UriAsStringSerializer

[common]\
object [UriAsStringSerializer](index.md) : KSerializer&lt;[Uri](../-uri/index.md)&gt; 

A validating KSerializer that serializes [Uri](../-uri/index.md) values as [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)s.

This can conveniently be obtained via the [Uri.Companion.stringSerializer](../string-serializer.md) function.

## Properties

| Name | Summary |
|---|---|
| [descriptor](descriptor.md) | [common]<br>open override val [descriptor](descriptor.md): SerialDescriptor |

## Functions

| Name | Summary |
|---|---|
| [deserialize](deserialize.md) | [common]<br>open override fun [deserialize](deserialize.md)(decoder: Decoder): [Uri](../-uri/index.md) |
| [serialize](serialize.md) | [common]<br>open override fun [serialize](serialize.md)(encoder: Encoder, value: [Uri](../-uri/index.md)) |
