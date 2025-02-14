package com.chrynan.uri.core.validation

import kotlin.reflect.KClass

/**
 * A [RuntimeException] that indicates there was an error asserting that a [ValidationResult] was valid or invalid.
 *
 * This exception is thrown from the [assertValid] and [assertInvalid] functions.
 */
internal open class ValidationException internal constructor(
    message: String? = null,
    cause: Throwable? = null,
    val errors: List<ValidationError> = emptyList()
) : RuntimeException(message, cause),
    ValidationError {

    override val details: String? = message
}

/**
 * A creator function for the [ValidationException] class.
 */
@Suppress("FunctionName")
internal fun <T, E : ValidationResult<*>> ValidationException(
    message: String? = null,
    result: ValidationResult<T>,
    expected: KClass<E>
): ValidationException {
    val fullMessage = buildString {
        append("ValidationException: Unexpected ValidationResult: expected = $expected; actual = ${result::class}\n")
        append("result = $result\n")

        if (message != null) {
            append("message = $message\n")
        }
    }

    return ValidationException(message = fullMessage)
}
