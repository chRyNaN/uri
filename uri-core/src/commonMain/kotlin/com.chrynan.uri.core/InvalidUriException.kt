package com.chrynan.uri.core

/**
 * An Exception that is thrown when attempting to obtain a [Uri] with invalid or not properly formatted parts.
 */
class InvalidUriException(override val message: String? = null) :
    RuntimeException("Invalid Uri: message = $message")
