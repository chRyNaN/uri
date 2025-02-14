//[uri-core](../../../index.md)/[com.chrynan.uri.core](../index.md)/[Uri](index.md)/[userInfo](user-info.md)

# userInfo

[common]\
abstract val [userInfo](user-info.md): [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)?

A [Uri](index.md) UserInfo subcomponent is a portion of the [authority](authority.md) component. It is an optional subcomponent that may consist of a user name and an optional password preceded by a colon (':') and followed by a '@' character.

Note that if the [authority](authority.md) is not present, then this will always return null. This is because this is a subcomponent of the [authority](authority.md) component. Even though some [path](path.md)s may have [userInfo](user-info.md) like components, it will not be considered a [userInfo](user-info.md) component and this will return null. For example, for the following [Uri](index.md), the [userInfo](user-info.md) property should return null: mailto:John.Doe@example.com

This property will return the UserInfo subcomponent without the following '@' character, and in the following form: username:password.
