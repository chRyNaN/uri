//[uri-core](../../../index.md)/[com.chrynan.uri.core](../index.md)/[SimpleUri](index.md)/[host](host.md)



# host  
[common]  
Content  
open override val [host](host.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null  
More info  


A [Uri](../-uri/index.md) Host subcomponent is a portion of the [authority](index.md#%5Bcom.chrynan.uri.core%2FSimpleUri%2Fauthority%2F%23%2FPointingToDeclaration%2F%5D%2FProperties%2F-1849276396) component. It is required if the [authority](index.md#%5Bcom.chrynan.uri.core%2FSimpleUri%2Fauthority%2F%23%2FPointingToDeclaration%2F%5D%2FProperties%2F-1849276396) is present. It consists of either a registered name, such as a hostname, or an IP Address. IPv4 addresses must be in a dot-decimal notation, and IPv6 addresses must be enclosed in brackets.



This property will return the Host subcomponent without the following colon (':') character.

  



