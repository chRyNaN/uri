//[uri-core](../../../index.md)/[com.chrynan.uri.core](../index.md)/[SimpleUri](index.md)/[userInfo](user-info.md)



# userInfo  
[common]  
Content  
open override val [userInfo](user-info.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null  
More info  


A [Uri](../-uri/index.md) UserInfo subcomponent is a portion of the [authority](index.md#%5Bcom.chrynan.uri.core%2FSimpleUri%2Fauthority%2F%23%2FPointingToDeclaration%2F%5D%2FProperties%2F-1849276396) component. It is an optional subcomponent that may consist of a user name and an optional password preceded by a colon (':') and followed by a '@' character.



Note that if the [authority](index.md#%5Bcom.chrynan.uri.core%2FSimpleUri%2Fauthority%2F%23%2FPointingToDeclaration%2F%5D%2FProperties%2F-1849276396) is not present, then this will always return null. This is because this is a subcomponent of the [authority](index.md#%5Bcom.chrynan.uri.core%2FSimpleUri%2Fauthority%2F%23%2FPointingToDeclaration%2F%5D%2FProperties%2F-1849276396) component. Even though some [path](path.md)s may have [userInfo](user-info.md) like components, it will not be considered a [userInfo](user-info.md) component and this will return null. For example, for the following [Uri](../-uri/index.md), the [userInfo](user-info.md) property should return null: mailto:John.Doe@example.com



This property will return the UserInfo subcomponent without the following '@' character, and in the following form: username:password.

  



