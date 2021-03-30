//[uri-core](../../../index.md)/[com.chrynan.uri.core](../index.md)/[Uri](index.md)/[port](port.md)



# port  
[common]  
Content  
abstract val [port](port.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)?  
More info  


A [Uri](index.md) Port subcomponent is a portion of the [authority](authority.md) component. It is an optional subcomponent that comes after the [host](host.md) and a colon (':') character. It typically represents the [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) port of the server to use.



This property will return the Port subcomponent without the preceding colon (':') character or the following '/' character, in an [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) form.

  



