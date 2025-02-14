package com.chrynan.uri.core.validation

/**
 * A [Validator] performs validation on an input type of [T] and returns a [ValidationResult] of type [R]. This
 * validation process determines whether the provided input to the [validate] function matches an expected format
 * criteria. If the validation process passes, then a [ValidationResult.Valid] should be returned. Otherwise, a
 * [ValidationResult.Invalid] should be returned.
 */
internal interface Validator<T, R> {

    /**
     * A function that determines whether the provided [input] is valid. If the provided [input] is deemed valid, then
     * a [ValidationResult.Valid] object, wrapping the returned type, will be returned. If the provided [input] is
     * deemed invalid, then a [ValidationResult.Invalid] object, wrapping any [ValidationError]s encountered, will be
     * returned.
     */
    fun validate(input: T): ValidationResult<R>

    /**
     * A convenience function that delegates to the [validate] function. This allows executing a [Validator] like a
     * function. For example, emailValidator(input) instead of emailValidator.validate(input). It is up to the
     * developer which approach should be used.
     */
    operator fun invoke(input: T): ValidationResult<R> = validate(input)
}
