@file:Suppress("unused")

package com.chrynan.uri.core

import com.chrynan.validator.UriValidator
import com.chrynan.validator.UrlValidator
import com.chrynan.validator.ValidationResult

private const val PORT_START_DELIMITER = ':'
private const val USER_INFO_END_DELIMITER = '@'
private const val SCHEME_END_DELIMITER = ':'
private const val HOST_IPV6_END_DELIMITER = ']'

private const val SCHEME_HTTP = "http"
private const val SCHEME_HTTPS = "https"
private val URL_SCHEMES = listOf(SCHEME_HTTP, SCHEME_HTTPS)

private val uriValidator = UriValidator()
private val urlValidator = UrlValidator()

private val uriPartsRegex = Regex("^(([^:/?#]+):)?(//([^/?#]*))?([^?#]*)(\\?([^#]*))?(#(.*))?")

/**
 * Retrieves a [Uri] from the provided [uriString]. If the provided [uriString] is valid and properly formatted, then a
 * [Uri] instance will be returned. If the provided [uriString] is invalid or is not properly formatted, then the
 * [InvalidUriException] will be thrown.
 *
 * @see [Uri]
 * @see [UriString]
 */
fun Uri.Companion.fromString(uriString: UriString): Uri {
    val scheme = uriString.substringBefore(SCHEME_END_DELIMITER)

    val isHttpUrl = URL_SCHEMES.any { it.toLowerCase() == scheme.toLowerCase() }

    when (val validationResult =
        if (isHttpUrl) urlValidator.validate(uriString) else uriValidator.validate(uriString)) {
        is ValidationResult.Invalid -> throw InvalidUriException(message = "Invalid Uri from String = $uriString. Errors = ${validationResult.errors}")
        is ValidationResult.Valid -> {
            val matchResult = uriPartsRegex.matchEntire(validationResult.value)

            if (matchResult == null) throw InvalidUriException(message = "Invalid Uri from String = $uriString; Match Result = $matchResult")

            val groupValues = matchResult.groupValues

            val validScheme = groupValues.getOrNull(2)
                ?: throw InvalidUriException(message = "Invalid Uri from String = $uriString. Scheme is missing.")
            val authority = groupValues.getOrNull(4)
            val path = groupValues.getOrNull(5) ?: ""
            val query = groupValues.getOrNull(7)
            val fragment = groupValues.getOrNull(9)

            val userInfoEndIndex = authority?.indexOf(USER_INFO_END_DELIMITER) ?: -1

            val userInfo =
                if (userInfoEndIndex == -1) null else authority?.substring(startIndex = 0, endIndex = userInfoEndIndex)

            val host = authority?.let {
                val hostStartIndex = if (userInfoEndIndex == -1) 0 else userInfoEndIndex + 1
                var hostIpv6EndIndex = it.indexOf(HOST_IPV6_END_DELIMITER)
                var portStartIndex = it.indexOf(PORT_START_DELIMITER)

                if (hostIpv6EndIndex != -1) {
                    hostIpv6EndIndex += 1
                }

                if (hostIpv6EndIndex > portStartIndex) {
                    portStartIndex = -1
                }

                val hostEndIndex = listOf(hostIpv6EndIndex, portStartIndex, it.length)
                    .filter { index -> index != -1 }
                    .minOf { index -> index }

                it.substring(startIndex = hostStartIndex, endIndex = hostEndIndex)
            }

            val port = authority?.let {
                val hostIpv6EndIndex = it.indexOf(HOST_IPV6_END_DELIMITER)
                val portStartIndex = it.indexOf(PORT_START_DELIMITER)

                if (portStartIndex == -1 || hostIpv6EndIndex > portStartIndex) {
                    null
                } else {
                    it.substring(startIndex = portStartIndex + 1)
                }
            }?.toIntOrNull()

            return SimpleUri(
                uriString = validationResult.value,
                scheme = validScheme,
                userInfo = if (userInfo.isNullOrBlank()) null else userInfo,
                host = if (host.isNullOrBlank()) null else host,
                port = port,
                path = if (path.isBlank()) "" else path,
                query = if (query.isNullOrBlank()) null else query,
                fragment = if (fragment.isNullOrBlank()) null else fragment
            )
        }
    }
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
