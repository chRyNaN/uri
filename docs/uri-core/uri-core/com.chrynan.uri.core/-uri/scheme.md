//[uri-core](../../../index.md)/[com.chrynan.uri.core](../index.md)/[Uri](index.md)/[scheme](scheme.md)



# scheme  
[common]  
Content  
abstract val [scheme](scheme.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  
More info  


A [Uri](index.md) Scheme is the first portion of the [Uri](index.md) preceding the color (':') character. It is required for the [Uri](index.md) to be considered valid. Common URI Schemes include "http", "https", "ftp", "file", etc. The Scheme component may consist of any combination of letters, digits, plus ('+'), period ('.'), or hyphen ('-'), but must begin with a letter to be considered valid.



This property will return the Scheme component of this [Uri](index.md) without the following color (':').

  



