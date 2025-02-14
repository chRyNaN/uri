# uri

A Kotlin Multi-platform Utility Library
for [Uniform Resource Identifiers (URIs)](https://en.wikipedia.org/wiki/Uniform_Resource_Identifier). <br/>
<img alt="GitHub tag (latest by date)" src="https://img.shields.io/github/v/tag/chRyNaN/uri">

```kotlin
Uri.parse("https://john.doe@www.example.com:123/forum/questions/?tag=networking&order=newest#top")
```

## Using the library

Consider the following Uri: **https://john.doe@www.example.com:123/forum/questions/?tag=networking&order=newest#top**

### Creating a Uri from parts:

```kotlin
Uri.fromParts(
    scheme = "https",
    fragment = "top",
    path = "/forum/questions/",
    query = "tag=networking&order=newest",
    userInfo = "john.doe",
    host = "www.example.com",
    port = 123
)
```

### Creating an optional Uri from parts:

If an error is encountered with the `Uri.fromParts` function, then an exception is thrown. Instead of throwing an
exception, null can be returned, using the `Uri.fromPartsOrNull` function.

```kotlin
Uri.fromPartsOrNull(
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
Uri.parse(uriString = "https://john.doe@www.example.com:123/forum/questions/?tag=networking&order=newest#top")
```

### Creating an optional Uri from a String:

If an error is encountered with the `Uri.fromString` function, then an exception is thrown. Instead of throwing an
exception, null can be returned, using the `Uri.fromStringOrNull` function.

```kotlin
Uri.parseOrNull(uriString = "https://john.doe@www.example.com:123/forum/questions/?tag=networking&order=newest#top")
```

## Building

The library is provided through [Repsy.io](https://repsy.io). Refer to
the [releases page](https://github.com/chRyNaN/uri/releases) for the latest version. <br/>
<img alt="GitHub tag (latest by date)" src="https://img.shields.io/github/v/tag/chRyNaN/uri">

### Repository

```kotlin
repositories {
    maven { url = "https://repo.repsy.io/mvn/chrynan/public" }
}
```

### Dependencies

```kotlin
implementation("com.chrynan.uri:uri-core:$VERSION")
```

```kotlin
implementation("com.chrynan.uri:uri-ktor-client:$VERSION")
```

## Documentation

More detailed documentation is available in the [docs](docs) folder. The entry point to the documentation can be
found [here](docs/index.md).

## License

```
Copyright 2021 chRyNaN

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
