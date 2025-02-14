package com.chrynan.uri.core

/**
 * A [UriString] is a [String] representation of a complete [Uri].
 *
 * Note that no verification is performed to assert that a [UriString] is valid and properly formatted. To verify that
 * a [UriString] is valid, convert it to a [Uri] using the [Uri.Companion.fromString] or
 * [Uri.Companion.fromStringOrNull] functions.
 *
 * Note that [UriString] is just a typealias to [String].
 */
public typealias UriString = String
