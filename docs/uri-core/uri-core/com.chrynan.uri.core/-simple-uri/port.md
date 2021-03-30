//[uri-core](../../../index.md)/[com.chrynan.uri.core](../index.md)/[SimpleUri](index.md)/[port](port.md)



# port  
[common]  
Content  
open override val [port](port.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null  
More info  


A [Uri](../-uri/index.md) Port subcomponent is a portion of the [authority](index.md#%5Bcom.chrynan.uri.core%2FSimpleUri%2Fauthority%2F%23%2FPointingToDeclaration%2F%5D%2FProperties%2F-1849276396) component. It is an optional subcomponent that comes after the [host](host.md) and a colon (':') character. It typically represents the [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) port of the server to use.



This property will return the Port subcomponent without the preceding colon (':') character or the following '/' character, in an [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) form.

  



