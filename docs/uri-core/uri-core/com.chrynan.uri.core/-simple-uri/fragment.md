//[uri-core](../../../index.md)/[com.chrynan.uri.core](../index.md)/[SimpleUri](index.md)/[fragment](fragment.md)



# fragment  
[common]  
Content  
open override val [fragment](fragment.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null  
More info  


A [Uri](../-uri/index.md) Fragment component is the portion of the URI after the [query](query.md) component. It is an optional component that is preceded by a hash ('#') character. A Fragment contains a fragment identifier which links to a secondary resource, such as a section header in an article. When the primary source is an HTML document, typically the Fragment is an HTML identifier (id) value of a specific HTML element.



This property will return this Fragment component of the [Uri](../-uri/index.md) without the preceding hash ('#') character.

  



