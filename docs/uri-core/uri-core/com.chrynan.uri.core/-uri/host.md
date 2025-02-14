//[uri-core](../../../index.md)/[com.chrynan.uri.core](../index.md)/[Uri](index.md)/[host](host.md)

# host

[common]\
abstract val [host](host.md): [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)?

A [Uri](index.md) Host subcomponent is a portion of the [authority](authority.md) component. It is required if the [authority](authority.md) is present. It consists of either a registered name, such as a hostname, or an IP Address. IPv4 addresses must be in a dot-decimal notation, and IPv6 addresses must be enclosed in brackets.

This property will return the Host subcomponent without the following colon (':') character.
