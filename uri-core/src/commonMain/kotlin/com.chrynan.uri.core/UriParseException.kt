package com.chrynan.uri.core

/**
 * Represents an exception that can be thrown when attempting to parse a [UriString] into a [Uri] using a [UriParser].
 */
public open class UriParseException(
    message: String? = null,
    cause: Throwable? = null
) : RuntimeException(message, cause)
