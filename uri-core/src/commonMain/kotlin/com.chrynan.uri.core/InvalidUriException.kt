package com.chrynan.uri.core

/**
 * An Exception that is thrown when attempting to obtain a [Uri] with invalid or not properly formatted parts.
 */
public class InvalidUriException(
    override val message: String? = null,
    cause: Exception? = null
) : UriParseException("Invalid Uri: message = $message", cause)
