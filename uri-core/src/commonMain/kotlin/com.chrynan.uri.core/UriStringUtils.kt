@file:Suppress("unused")

package com.chrynan.uri.core

import com.chrynan.validator.UriValidator
import com.chrynan.validator.ValidationResult

private val validator = UriValidator()

/**
 * Retrieves a [Uri] from the provided [uriString]. If the provided [uriString] is valid and properly formatted, then a
 * [Uri] instance will be returned. If the provided [uriString] is invalid or is not properly formatted, then the
 * [InvalidUriException] will be thrown.
 *
 * @see [Uri]
 * @see [UriString]
 */
fun Uri.Companion.fromString(uriString: UriString): Uri =
    when (val result = validator.validate(uriString)) {
        is ValidationResult.Valid -> SimpleUriWithString(uriString)
        is ValidationResult.Invalid -> throw InvalidUriException(message = "Invalid Uri from String = $uriString. Errors = ${result.errors}")
    }

/**
 * Retrieves a [Uri] from the provided [uriString]. If the provided [uriString] is valid and properly formatted, then a
 * [Uri] instance will be returned. If the provided [uriString] is invalid or is not properly formatted, then the null
 * will be returned.
 *
 * @see [Uri]
 * @see [UriString]
 */
fun Uri.Companion.fromStringOrNull(uriString: UriString): Uri? =
    try {
        fromString(uriString = uriString)
    } catch (e: InvalidUriException) {
        null
    }
