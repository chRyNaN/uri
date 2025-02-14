//[uri-core](../../../index.md)/[com.chrynan.uri.core](../index.md)/[Uri](index.md)/[scheme](scheme.md)

# scheme

[common]\
abstract val [scheme](scheme.md): [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)?

A [Uri](index.md) Scheme is the first portion of the [Uri](index.md) preceding the color (':') character. It is required for the [Uri](index.md) to be considered valid. Common URI Schemes include &quot;http&quot;, &quot;https&quot;, &quot;ftp&quot;, &quot;file&quot;, etc. The Scheme component may consist of any combination of letters, digits, plus ('+'), period ('.'), or hyphen ('-'), but must begin with a letter to be considered valid.

This property will return the Scheme component of this [Uri](index.md) without the following color (':').
