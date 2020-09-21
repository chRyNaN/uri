# uri
A Kotlin Multi-platform Utility Library for [Uniform Resource Identifiers (URIs)](https://en.wikipedia.org/wiki/Uniform_Resource_Identifier).

## Creating a Uri
Consider the following Uri: **https://john.doe@www.example.com:123/forum/questions/?tag=networking&order=newest#top**

### Creating a Uri:
```kotlin
uri(
  scheme = "https",
  fragment = "top",
  path = "/forum/questions/",
  query = "tag=networking&order=newest",
  userInfo = "john.doe",
  host = "www.example.com",
  port = 123
)
```

### Creating an optional Uri:
If an error is encountered with the `uri` function, then an exception is thrown. Instead of throwing an exception, null can be returned, using the `uriOrNull` function.
```kotlin
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
This library has a typealias, `UriString`, which is just a `String`. A `UriString` can be parsed and turned into a `Uri`.

```kotlin
uriFromString(uriString = "https://john.doe@www.example.com:123/forum/questions/?tag=networking&order=newest#top")
```

### Creating an optional Uri from a String:
If an error is encountered with the `uriFromString` function, then an exception is thrown. Instead of throwing an exception, null can be returned, using the `uriFromStringOrNull` function.
```kotlin
uriFromStringOrNull(uriString = "https://john.doe@www.example.com:123/forum/questions/?tag=networking&order=newest#top")
```

## GraphQL
This library also contains a GraphQL type reference in the `schema` folder.
```graphql
scalar UriString

type Uri {
    scheme: String!
    authority: String
    userInfo: String
    host: String
    port: Int
    path: String!
    query: String
    fragment: String
}

input InputUri {
    scheme: String!
    authority: String
    userInfo: String
    host: String
    port: Int
    path: String!
    query: String
    fragment: String
}
```

## Building

The library is provided through [Bintray](https://bintray.com/). Refer to the [releases page](https://github.com/chRyNaN/uri/releases) for the latest version.

### Repository
```kotlin
repositories {
    maven {
        url = uri("https://dl.bintray.com/chrynan/chrynan")
    }
}
```

### Dependencies
**Kotlin Common Core Module:**
```kotlin
implementation("com.chrynan.uri:uri-core:$VERSION")
```

**Kotlin JVM Module:**
```kotlin
implementation("com.chrynan.uri:uri-core-jvm:$VERSION")
```

**Kotlin JS Module:**
```kotlin
implementation("com.chrynan.uri:uri-core-js:$VERSION")
```

## License
```
Copyright 2020 chRyNaN

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
