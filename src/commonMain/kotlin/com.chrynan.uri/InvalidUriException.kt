package com.chrynan.uri

data class InvalidUriException(override val message: String? = null) :
    RuntimeException("Invalid Uri: message = $message")