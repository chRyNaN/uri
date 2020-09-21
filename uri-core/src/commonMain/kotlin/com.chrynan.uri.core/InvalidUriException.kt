package com.chrynan.uri.core

data class InvalidUriException(override val message: String? = null) :
    RuntimeException("Invalid Uri: message = $message")
