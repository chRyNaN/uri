package com.chrynan.uri.core

@Target(
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.CONSTRUCTOR
)
@RequiresOptIn(
    message = "API is experimental, and therefore, may contain errors and change in the future. Please use with caution.",
    level = RequiresOptIn.Level.WARNING
)
public annotation class ExperimentalUriApi public constructor()
