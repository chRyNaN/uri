@file:Suppress("unused")

package com.chrynan.uri.ktor

import com.chrynan.uri.core.Uri
import com.chrynan.uri.core.fromParts
import com.chrynan.uri.core.fromPartsOrNull
import io.ktor.http.*
import io.ktor.util.*
import com.chrynan.uri.core.InvalidUriException

/**
 * Converts this [Url] to a [Uri] or throws an [InvalidUriException] if a validation exception has been encountered.
 */
fun Url.toUri(): Uri {
    val scheme = this.protocol.name

    val userInfo = when {
        this.user != null && this.password != null -> "${this.user}:${this.password}"
        this.user != null -> this.user
        else -> null
    }

    var queryParameters: String? = this.parameters.toMap().entries.joinToString(separator = "&") { entry ->
        entry.value.joinToString(separator = "&") { "${entry.key}=$it" }
    }
    queryParameters = if (queryParameters.isNullOrBlank()) null else queryParameters

    val fragment = if (this.fragment.isBlank()) null else this.fragment

    return Uri.fromParts(
        scheme = scheme,
        userInfo = userInfo,
        host = this.host,
        port = this.port,
        path = this.encodedPath,
        query = queryParameters,
        fragment = fragment
    )
}

/**
 * Converts this [Url] to a [Uri] or null if a validation exception has been encountered.
 */
fun Url.toUriOrNull(): Uri? {
    val scheme = this.protocol.name

    val userInfo = when {
        this.user != null && this.password != null -> "${this.user}:${this.password}"
        this.user != null -> this.user
        else -> null
    }

    var queryParameters: String? = this.parameters.toMap().entries.joinToString(separator = "&") { entry ->
        entry.value.joinToString(separator = "&") { "${entry.key}=$it" }
    }
    queryParameters = if (queryParameters.isNullOrBlank()) null else queryParameters

    val fragment = if (this.fragment.isBlank()) null else this.fragment

    return Uri.fromPartsOrNull(
        scheme = scheme,
        userInfo = userInfo,
        host = this.host,
        port = this.port,
        path = this.encodedPath,
        query = queryParameters,
        fragment = fragment
    )
}

/**
 * Converts this [Uri] to a [Url] or throws an [InvalidUriException] if a validation exception has been encountered.
 */
fun Uri.toUrl(): Url = try {
    Url(urlString = uriString)
} catch (exception: Exception) {
    throw InvalidUriException(cause = exception)
}

/**
 * Converts this [Uri] to a [Url] or null if a validation exception has been encountered.
 */
fun Uri.toUrlOrNull(): Url? =
    try {
        this.toUrl()
    } catch (exception: InvalidUriException) {
        null
    }
