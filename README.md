# uri
A Simple Kotlin Multiplatform Utility Library for Uniform Resource Identifiers (URIs).

### Creating a Uri

```kotlin
// https://john.doe@www.example.com:123/forum/questions/?tag=networking&order=newest#top
// Taken from Wikipedia: https://en.wikipedia.org/wiki/Uniform_Resource_Identifier
uri(
  scheme = "https",
  fragment = "top",
  path = "/forum/questions/",
  query = "tag=networking&order=newest",
  userInfo = "john.doe",
  host = "www.example.com",
  port = 123
)

uriOrNull(
  scheme = "https",
  fragment = "top",
  path = "/forum/questions/",
  query = "tag=networking&order=newest",
  userInfo = "john.doe",
  host = "www.example.com",
  port = 123
)
```

### Creating a Uri from a String:

```kotlin
// Throws InvalidUriException if the provided UriString is invalid
uriFromString(uriString = "https://john.doe@www.example.com:123/forum/questions/?tag=networking&order=newest#top")

uriFromStringOrNull(uriString = "https://john.doe@www.example.com:123/forum/questions/?tag=networking&order=newest#top")
```