package com.chrynan.uri.core

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * A [UriString] is a wrapper around a [String] representation of a complete [Uri].
 *
 * Note that no verification is performed to assert that a [UriString] is valid and properly formatted. To verify that
 * a [UriString] is valid, convert it to a [Uri] using a [UriParser].
 */
@JvmInline
@Serializable
public value class UriString public constructor(
    public val value: String
)
