//[uri-core](../../index.md)/[com.chrynan.uri.core](index.md)/[unsafeFromParts](unsafe-from-parts.md)



# unsafeFromParts  
[common]  
Content  
fun [Uri.Companion](-uri/-companion/index.md).[unsafeFromParts](unsafe-from-parts.md)(scheme: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), userInfo: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, host: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, port: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null, path: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), query: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, fragment: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null): [Uri](-uri/index.md)  
More info  


Retrieves a [Uri](-uri/index.md) from the provided parts without performing any validation or formatting of the input. Note that this can result in undefined behavior if the provided input isn't valid or formatted appropriately according to the the [Uri](-uri/index.md) interface documentation. Only use this function if you are certain that the provided input parts are properly valid and formatted and you wish to bypass the validation and formatting for performance reasons.



## See also  
  
common  
  
| | |
|---|---|
| <a name="com.chrynan.uri.core//unsafeFromParts/com.chrynan.uri.core.Uri.Companion#kotlin.String#kotlin.String?#kotlin.String?#kotlin.Int?#kotlin.String#kotlin.String?#kotlin.String?/PointingToDeclaration/"></a>[com.chrynan.uri.core.Uri](-uri/index.md)| <a name="com.chrynan.uri.core//unsafeFromParts/com.chrynan.uri.core.Uri.Companion#kotlin.String#kotlin.String?#kotlin.String?#kotlin.Int?#kotlin.String#kotlin.String?#kotlin.String?/PointingToDeclaration/"></a>|
| <a name="com.chrynan.uri.core//unsafeFromParts/com.chrynan.uri.core.Uri.Companion#kotlin.String#kotlin.String?#kotlin.String?#kotlin.Int?#kotlin.String#kotlin.String?#kotlin.String?/PointingToDeclaration/"></a>[com.chrynan.uri.core.UriString](index.md#%5Bcom.chrynan.uri.core%2FUriString%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F-1849276396)| <a name="com.chrynan.uri.core//unsafeFromParts/com.chrynan.uri.core.Uri.Companion#kotlin.String#kotlin.String?#kotlin.String?#kotlin.Int?#kotlin.String#kotlin.String?#kotlin.String?/PointingToDeclaration/"></a>|
  
  



