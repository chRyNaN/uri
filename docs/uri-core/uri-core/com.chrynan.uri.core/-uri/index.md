//[uri-core](../../../index.md)/[com.chrynan.uri.core](../index.md)/[Uri](index.md)



# Uri  
 [common] interface [Uri](index.md)

A [Uri](index.md) is a sequence of characters the uniquely identifies a particular resource, such as, a web address (URL). For example, a URL is a subset of a [Uri](index.md).



For more information about URIs, refer to the following resource: https://en.wikipedia.org/wiki/Uniform_Resource_Identifier

   


## See also  
  
common  
  
| | |
|---|---|
| <a name="com.chrynan.uri.core/Uri///PointingToDeclaration/"></a>| <a name="com.chrynan.uri.core/Uri///PointingToDeclaration/"></a><br><br>[URI Documentation](https://en.wikipedia.org/wiki/Uniform_Resource_Identifier)<br><br>|
  


## Types  
  
|  Name |  Summary | 
|---|---|
| <a name="com.chrynan.uri.core/Uri.Companion///PointingToDeclaration/"></a>[Companion](-companion/index.md)| <a name="com.chrynan.uri.core/Uri.Companion///PointingToDeclaration/"></a>[common]  <br>Content  <br>object [Companion](-companion/index.md)  <br>More info  <br>The companion object for the [Uri](index.md) interface.  <br><br><br>|


## Properties  
  
|  Name |  Summary | 
|---|---|
| <a name="com.chrynan.uri.core/Uri/authority/#/PointingToDeclaration/"></a>[authority](authority.md)| <a name="com.chrynan.uri.core/Uri/authority/#/PointingToDeclaration/"></a> [common] open val [authority](authority.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?A [Uri](index.md) Authority is the portion of the URI after the [scheme](scheme.md) and the "://" sequence.   <br>|
| <a name="com.chrynan.uri.core/Uri/fragment/#/PointingToDeclaration/"></a>[fragment](fragment.md)| <a name="com.chrynan.uri.core/Uri/fragment/#/PointingToDeclaration/"></a> [common] abstract val [fragment](fragment.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?A [Uri](index.md) Fragment component is the portion of the URI after the [query](query.md) component.   <br>|
| <a name="com.chrynan.uri.core/Uri/host/#/PointingToDeclaration/"></a>[host](host.md)| <a name="com.chrynan.uri.core/Uri/host/#/PointingToDeclaration/"></a> [common] abstract val [host](host.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?A [Uri](index.md) Host subcomponent is a portion of the [authority](authority.md) component.   <br>|
| <a name="com.chrynan.uri.core/Uri/path/#/PointingToDeclaration/"></a>[path](path.md)| <a name="com.chrynan.uri.core/Uri/path/#/PointingToDeclaration/"></a> [common] abstract val [path](path.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)The following is taken from the Wikipedia definition of a [Uri](index.md) Path component (their definition is well done so quoting instead of attempting to replicate):<br><br>A path component, consisting of a sequence of path segments separated by a slash (/). A path is always defined for a URI, though the defined path may be empty (zero length). A segment may also be empty, resulting in two consecutive slashes (//) in the path component. A path component may resemble or map exactly to a file system path, but does not always imply a relation to one. If an authority component is present, then the path component must either be empty or begin with a slash (/). If an authority component is absent, then the path cannot begin with an empty segment, that is with two slashes (//), as the following characters would be interpreted as an authority component.12 The final segment of the path may be referred to as a 'slug'. <a href="https://en.wikipedia.org/wiki/Uniform_Resource_Identifier">Wikipedia URI Source</a><br><br>This property will return the Path component of the [Uri](index.md) including any slash ('/') characters.   <br>|
| <a name="com.chrynan.uri.core/Uri/port/#/PointingToDeclaration/"></a>[port](port.md)| <a name="com.chrynan.uri.core/Uri/port/#/PointingToDeclaration/"></a> [common] abstract val [port](port.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)?A [Uri](index.md) Port subcomponent is a portion of the [authority](authority.md) component.   <br>|
| <a name="com.chrynan.uri.core/Uri/query/#/PointingToDeclaration/"></a>[query](query.md)| <a name="com.chrynan.uri.core/Uri/query/#/PointingToDeclaration/"></a> [common] abstract val [query](query.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?A [Uri](index.md) Query component is the portion of the [Uri](index.md) after the [path](path.md) component.   <br>|
| <a name="com.chrynan.uri.core/Uri/scheme/#/PointingToDeclaration/"></a>[scheme](scheme.md)| <a name="com.chrynan.uri.core/Uri/scheme/#/PointingToDeclaration/"></a> [common] abstract val [scheme](scheme.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)A [Uri](index.md) Scheme is the first portion of the [Uri](index.md) preceding the color (':') character.   <br>|
| <a name="com.chrynan.uri.core/Uri/schemeSpecificPart/#/PointingToDeclaration/"></a>[schemeSpecificPart](scheme-specific-part.md)| <a name="com.chrynan.uri.core/Uri/schemeSpecificPart/#/PointingToDeclaration/"></a> [common] open val [schemeSpecificPart](scheme-specific-part.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)A [Uri](index.md) Scheme Specific Part is the portion of the [Uri](index.md) after the [scheme](scheme.md) and the colon (':') character that follows the [scheme](scheme.md).   <br>|
| <a name="com.chrynan.uri.core/Uri/uriString/#/PointingToDeclaration/"></a>[uriString](uri-string.md)| <a name="com.chrynan.uri.core/Uri/uriString/#/PointingToDeclaration/"></a> [common] abstract val [uriString](uri-string.md): [UriString](../index.md#%5Bcom.chrynan.uri.core%2FUriString%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F-1849276396)Represents this [Uri](index.md) as a [UriString](../index.md#%5Bcom.chrynan.uri.core%2FUriString%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F-1849276396) value.   <br>|
| <a name="com.chrynan.uri.core/Uri/userInfo/#/PointingToDeclaration/"></a>[userInfo](user-info.md)| <a name="com.chrynan.uri.core/Uri/userInfo/#/PointingToDeclaration/"></a> [common] abstract val [userInfo](user-info.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?A [Uri](index.md) UserInfo subcomponent is a portion of the [authority](authority.md) component.   <br>|


## Inheritors  
  
|  Name | 
|---|
| <a name="com.chrynan.uri.core/SimpleUri///PointingToDeclaration/"></a>[SimpleUri](../-simple-uri/index.md)|


## Extensions  
  
|  Name |  Summary | 
|---|---|
| <a name="com.chrynan.uri.core//pathSegments/com.chrynan.uri.core.Uri#/PointingToDeclaration/"></a>[pathSegments](../path-segments.md)| <a name="com.chrynan.uri.core//pathSegments/com.chrynan.uri.core.Uri#/PointingToDeclaration/"></a>[common]  <br>Content  <br>val [Uri](index.md).[pathSegments](../path-segments.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)>  <br>More info  <br>Retrieves a [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html) of [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)s each representing the individual Path Segments of this [Uri](index.md).  <br><br><br>|
| <a name="com.chrynan.uri.core//queryParameters/com.chrynan.uri.core.Uri#kotlin.CharArray#kotlin.Boolean/PointingToDeclaration/"></a>[queryParameters](../query-parameters.md)| <a name="com.chrynan.uri.core//queryParameters/com.chrynan.uri.core.Uri#kotlin.CharArray#kotlin.Boolean/PointingToDeclaration/"></a>[common]  <br>Content  <br>fun [Uri](index.md).[queryParameters](../query-parameters.md)(vararg delimiters: [Char](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char/index.html) = charArrayOf('&', ';'), ignoreCase: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true): [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)<[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?>  <br>More info  <br>Retrieves a [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html) of [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) keys and values representing the individual Query Parameters in this [Uri](index.md).  <br><br><br>|
| <a name="com.chrynan.uri.core//slug/com.chrynan.uri.core.Uri#/PointingToDeclaration/"></a>[slug](../slug.md)| <a name="com.chrynan.uri.core//slug/com.chrynan.uri.core.Uri#/PointingToDeclaration/"></a>[common]  <br>Content  <br>val [Uri](index.md).[slug](../slug.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?  <br>More info  <br>Retrieves the final path segment of the [Uri.path](path.md) without the preceding slash character ('/').  <br><br><br>|

