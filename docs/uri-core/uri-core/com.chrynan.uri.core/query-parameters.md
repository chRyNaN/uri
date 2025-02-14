//[uri-core](../../index.md)/[com.chrynan.uri.core](index.md)/[queryParameters](query-parameters.md)

# queryParameters

[common]\
fun [Uri](-uri/index.md).[queryParameters](query-parameters.md)(vararg delimiters: [Char](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-char/index.html) = charArrayOf('&amp;', ';'), ignoreCase: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html) = true): [Map](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)?&gt;

Retrieves a [Map](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/-map/index.html) of [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html) keys and values representing the individual Query Parameters in this [Uri](-uri/index.md). A Query Parameter is a portion of the [Uri.query](-uri/query.md) component that is separated by other Query Parameters by the provided [delimiters](query-parameters.md). A Query Parameter may be in the key-value form with an equals character ('=') separating the key and value. The resulting [Map](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/-map/index.html) will have a key matching the Query Parameters keys and corresponding values. If a Query Parameter doesn't have a value (the equals sign isn't present), then the value will be considered null. If there are no Query Parameters, then this will return an empty [Map](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/-map/index.html).

#### Parameters

common

| | |
|---|---|
| delimiters | The delimiters that separate the Query Parameters in the [Uri.query](-uri/query.md) component. |
| ignoreCase | Whether to ignore the case (upper/lower) of the delimiters or not. |
