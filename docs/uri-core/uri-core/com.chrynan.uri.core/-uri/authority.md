//[uri-core](../../../index.md)/[com.chrynan.uri.core](../index.md)/[Uri](index.md)/[authority](authority.md)



# authority  
[common]  
Content  
open val [authority](authority.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?  
More info  


A [Uri](index.md) Authority is the portion of the URI after the [scheme](scheme.md) and the "://" sequence. It is optional for the [Uri](index.md). The Authority consists of three sub-parts of the URI: The [userInfo](user-info.md), [host](host.md), and [port](port.md).



This property will return the Authority component of this [Uri](index.md) without the preceding "://" sequence, in the following form: [userInfo](user-info.md)@[host](host.md):[port](port.md). Note that each of the sub components are optional as well but if the [userInfo](user-info.md) or [port](port.md) are included, then the [host](host.md) must be included.

  



