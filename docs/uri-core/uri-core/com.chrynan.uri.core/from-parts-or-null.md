//[uri-core](../../index.md)/[com.chrynan.uri.core](index.md)/[fromPartsOrNull](from-parts-or-null.md)

# fromPartsOrNull

[common]\
fun [Uri.Companion](-uri/-companion/index.md).[fromPartsOrNull](from-parts-or-null.md)(scheme: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html), userInfo: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)? = null, host: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)? = null, port: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)? = null, path: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html), query: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)? = null, fragment: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)? = null): [Uri](-uri/index.md)?

Retrieves a [Uri](-uri/index.md) from the provided parts. If the provided parts are valid and properly formatted, then a [Uri](-uri/index.md) is returned. If the provided parts are invalid or not properly formatted, then null is returned.

#### See also

| |
|---|
| [Uri](-uri/index.md) |
| [UriString](-uri-string/index.md) |
