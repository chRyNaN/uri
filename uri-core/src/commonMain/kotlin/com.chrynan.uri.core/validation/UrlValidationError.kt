package com.chrynan.uri.core.validation

/**
 * A [ValidationError] that can occur when validating a URL with the [UrlValidator].
 */
internal sealed class UrlValidationError(override val details: String? = null) : ValidationError {

    /**
     * The provided input value to the [UrlValidator] was null. A null value is not a valid URL.
     */
    object InputIsNull : UrlValidationError(details = "Input is not a valid URL because it is null.")

    /**
     * The provided input value to the [UrlValidator] was not in a valid URL format.
     */
    object InvalidFormat : UrlValidationError(details = "Input is not in a valid URL Format.")
}
