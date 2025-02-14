//[uri-core](../../index.md)/[com.chrynan.uri.core](index.md)/[unsafeFromParts](unsafe-from-parts.md)

# unsafeFromParts

[common]\
fun [Uri.Companion](-uri/-companion/index.md).[unsafeFromParts](unsafe-from-parts.md)(scheme: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html), userInfo: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)? = null, host: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)? = null, port: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)? = null, path: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html), query: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)? = null, fragment: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)? = null): [Uri](-uri/index.md)

Retrieves a [Uri](-uri/index.md) from the provided parts without performing any validation or formatting of the input. Note that this can result in undefined behavior if the provided input isn't valid or formatted appropriately according to the the [Uri](-uri/index.md) interface documentation. Only use this function if you are certain that the provided input parts are properly valid and formatted and you wish to bypass the validation and formatting for performance reasons.

#### See also

| |
|---|
| [Uri](-uri/index.md) |
| [UriString](-uri-string/index.md) |
