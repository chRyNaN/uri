@file:Suppress("unused")

package com.chrynan.uri.core

/**
 * Retrieves the final path segment of the [Uri.path] without the preceding slash character ('/'). If the final path
 * segment is blank then null will be returned.
 */
public val Uri.slug: String?
    get() {
        val i = path.lastIndexOf(char = '/')

        if ((i == -1) and (path.isNotBlank())) return path
        if (i == -1) return null
        if (i + 1 > path.length) return null

        val s = path.substring(startIndex = i + 1)

        if (s.isBlank()) return null

        return s
    }

/**
 * Retrieves a [List] of [String]s each representing the individual Path Segments of this [Uri]. A Path Segment is a
 * portion of the [Uri.path] component which is separated by slash characters ('/'). If there are no Path Segments,
 * then this will return an empty [List].
 */
public val Uri.pathSegments: List<String>
    get() = path.split('/')

/**
 * Retrieves a [Map] of [String] keys and values representing the individual Query Parameters in this [Uri]. A Query
 * Parameter is a portion of the [Uri.query] component that is separated by other Query Parameters by the provided
 * [delimiters]. A Query Parameter may be in the key-value form with an equals character ('=') separating the key and
 * value. The resulting [Map] will have a key matching the Query Parameters keys and corresponding values. If a Query
 * Parameter doesn't have a value (the equals sign isn't present), then the value will be considered null. If there are
 * no Query Parameters, then this will return an empty [Map].
 *
 * @param [delimiters] The delimiters that separate the Query Parameters in the [Uri.query] component.
 * @param [ignoreCase] Whether to ignore the case (upper/lower) of the delimiters or not.
 */
public fun Uri.queryParameters(
    vararg delimiters: Char = charArrayOf('&', ';'),
    ignoreCase: Boolean = true
): Map<String, String?> {
    val q = query ?: return emptyMap()

    return q.split(*delimiters, ignoreCase = ignoreCase)
        .associate {
            val equalsIndex = it.indexOf(char = '=')
            val key = if (equalsIndex == -1) it else it.substring(startIndex = 0, endIndex = equalsIndex)
            val value =
                if (equalsIndex == -1 || equalsIndex + 1 > it.lastIndex) null else it.substring(startIndex = equalsIndex + 1)

            key to value
        }
}
