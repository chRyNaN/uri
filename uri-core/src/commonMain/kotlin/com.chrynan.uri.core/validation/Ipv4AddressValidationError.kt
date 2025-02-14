package com.chrynan.uri.core.validation

/**
 * A [ValidationError] that can occur when validating an IP Address with the [Ipv4AddressValidator].
 */
internal sealed class Ipv4AddressValidationError(override val details: String? = null) : ValidationError {

    /**
     * The provided input value to the [Ipv4AddressValidator] was null. A null value is not a valid IP Address.
     */
    object InputIsNull : Ipv4AddressValidationError(details = "Input is not a valid IP Address because it is null.")

    /**
     * The provided input value to the [Ipv4AddressValidator] was not in a valid IP address format.
     */
    object InvalidFormat : Ipv4AddressValidationError(details = "Input is not in a valid IP Address Format.")
}
