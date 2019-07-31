package com.chrynan.uri

fun uri(
    scheme: String,
    authority: String? = null,
    userInfo: String? = null,
    host: String? = null,
    port: Int? = null,
    path: String,
    query: String? = null,
    fragment: String? = null
): Uri = SimpleUri(
    scheme = scheme,
    authority = authority,
    userInfo = userInfo,
    host = host,
    port = port,
    path = path,
    query = query,
    fragment = fragment
)

fun Uri.toDecodedString(): UriString = buildString {
    append(scheme).append(':')
    append(schemeSpecificPart)

    if (!fragment.isNullOrBlank()) {
        append('#').append(fragment)
    }
}