@file:Suppress("unused")

package com.chrynan.uri.core

import com.chrynan.validator.UrlValidator
import com.chrynan.validator.ValidationResult

private val validator = UrlValidator()

fun Uri.Companion.fromString(uriString: UriString): Uri =
    when (val result = validator.validate(uriString)) {
        is ValidationResult.Valid -> SimpleUriWithString(uriString)
        is ValidationResult.Invalid -> throw InvalidUriException(message = "Invalid Uri from String = $uriString. Errors = ${result.errors}")
    }

fun Uri.Companion.fromStringOrNull(uriString: UriString): Uri? =
    try {
        fromString(uriString = uriString)
    } catch (e: InvalidUriException) {
        null
    }
