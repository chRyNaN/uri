package com.chrynan.uri.core.validation

import kotlin.jvm.JvmInline

/**
 * Represents the result of a [Validator.validate] function call. This could either be a [Valid] or [Invalid] result.
 */
internal sealed interface ValidationResult<T> {

    /**
     * A [ValidationResult] that indicates that the provided input to a [Validator.validate] function was considered
     * valid, or that the validation was considered successful. The [value] property is the valid input for the
     * [Validator.validate] function.
     */
    @JvmInline
    value class Valid<T> internal constructor(
        val value: T
    ) : ValidationResult<T>

    /**
     * A [ValidationResult] that indicates that the provided input to a [Validator.validate] function was considered
     * invalid, or that the validation was considered unsuccessful. The [errors] property are the [Collection] of
     * errors that were encountered when performing the validation.
     */
    @JvmInline
    value class Invalid<T> internal constructor(
        val errors: List<ValidationError>
    ) : ValidationResult<T>
}

/**
 * A convenience function that creates an [ValidationResult.Valid] class with the provided [value]. This function just
 * delegates to the [ValidationResult.Valid] constructor.
 */
@Suppress("FunctionName")
internal fun <T> Valid(value: T): ValidationResult.Valid<T> = ValidationResult.Valid(value)

/**
 * A convenience function that creates an [ValidationResult.Invalid] class with the provided [errors]. This function
 * just delegates to the [ValidationResult.Valid] constructor.
 */
@Suppress("FunctionName")
internal fun <T> Invalid(errors: List<ValidationError>): ValidationResult.Invalid<T> = ValidationResult.Invalid(errors)

/**
 * A convenience function that creates an [ValidationResult.Invalid] class with a vararg number of [ValidationError]s.
 * This function just delegates to the [ValidationResult.Invalid] constructor with the vararg parameters as a [List].
 */
@Suppress("FunctionName")
internal fun <T> Invalid(vararg errors: ValidationError): ValidationResult.Invalid<T> =
    ValidationResult.Invalid(errors.toList())
