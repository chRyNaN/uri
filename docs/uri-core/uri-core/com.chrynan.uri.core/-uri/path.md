//[uri-core](../../../index.md)/[com.chrynan.uri.core](../index.md)/[Uri](index.md)/[path](path.md)



# path  
[common]  
Content  
abstract val [path](path.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  
More info  


The following is taken from the Wikipedia definition of a [Uri](index.md) Path component (their definition is well done so quoting instead of attempting to replicate):



A path component, consisting of a sequence of path segments separated by a slash (/). A path is always defined for a URI, though the defined path may be empty (zero length). A segment may also be empty, resulting in two consecutive slashes (//) in the path component. A path component may resemble or map exactly to a file system path, but does not always imply a relation to one. If an authority component is present, then the path component must either be empty or begin with a slash (/). If an authority component is absent, then the path cannot begin with an empty segment, that is with two slashes (//), as the following characters would be interpreted as an authority component.12 The final segment of the path may be referred to as a 'slug'. <a href="https://en.wikipedia.org/wiki/Uniform_Resource_Identifier">Wikipedia URI Source</a>



This property will return the Path component of the [Uri](index.md) including any slash ('/') characters. If there is an [authority](authority.md) then this [path](path.md) should begin with a slash ('/') character.

  



