package com.chrynan.uri.core.validation

internal class UrlValidator : Validator<String?, String> {

    companion object {

        private val URL_REGEX = Regex(WebRegexConstants.WEB_URL)
        private val AUTO_URL_REGEX = Regex(WebRegexConstants.AUTOLINK_WEB_URL)
    }

    override fun validate(input: String?): ValidationResult<String> {
        if (input == null) return Invalid(UrlValidationError.InputIsNull)

        if (!input.matches(URL_REGEX) && !input.matches(AUTO_URL_REGEX)) return Invalid(UrlValidationError.InvalidFormat)

        return Valid(input)
    }
}
