package com.chrynan.uri.core

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * A convenience function for accessing the [UriAsStringSerializer].
 */
public inline fun Uri.Companion.stringSerializer(): KSerializer<Uri> =
    UriAsStringSerializer

/**
 * A validating [KSerializer] that serializes [Uri] values as [String]s.
 *
 * This can conveniently be obtained via the [Uri.Companion.stringSerializer] function.
 */
public object UriAsStringSerializer : KSerializer<Uri> {

    override val descriptor: SerialDescriptor = String.serializer().descriptor

    override fun serialize(encoder: Encoder, value: Uri) {
        encoder.encodeString(value.toUriString().value)
    }

    override fun deserialize(decoder: Decoder): Uri {
        val value = decoder.decodeString()

        return Uri.parse(value = value)
    }
}
