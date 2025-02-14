//[uri-core](../../index.md)/[com.chrynan.uri.core](index.md)/[pathSegments](path-segments.md)

# pathSegments

[common]\
val [Uri](-uri/index.md).[pathSegments](path-segments.md): [List](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)&gt;

Retrieves a [List](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/-list/index.html) of [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)s each representing the individual Path Segments of this [Uri](-uri/index.md). A Path Segment is a portion of the [Uri.path](-uri/path.md) component which is separated by slash characters ('/'). If there are no Path Segments, then this will return an empty [List](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/-list/index.html).
