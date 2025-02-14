package com.chrynan.uri.core.validation

/**
 * A [ValidationError] that can occur when validating an IPv6 Address with the [Ipv6AddressValidator].
 */
internal sealed class Ipv6AddressValidationError(override val details: String? = null) : ValidationError {

    /**
     * The provided input value to the [Ipv6AddressValidator] was null. A null value is not a valid IPv6 Address.
     */
    object InputIsNull : Ipv6AddressValidationError(details = "Input is not a valid IPv6 Address because it is null.")

    /**
     * The provided input value to the [Ipv6AddressValidator] was not in a valid IPv6 address format.
     */
    object InvalidFormat : Ipv6AddressValidationError(details = "Input is not in a valid IPv6 Address Format.")
}
