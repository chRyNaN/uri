package com.chrynan.uri.core

import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class UriExtensionsTest {

    @Test
    fun query_parameters_returns_expected_values() {
        val uri = Uri.fromString("https://www.example.com/some/path?one=valueOne&two=valueTwo&three")

        val queryParameters = uri.queryParameters()

        assertEquals(expected = 3, actual = queryParameters.size)
        assertContains(queryParameters, "one")
        assertContains(queryParameters, "two")
        assertContains(queryParameters, "three")

        assertEquals(expected = "valueOne", actual = queryParameters["one"])
        assertEquals(expected = "valueTwo", actual = queryParameters["two"])
        assertEquals(expected = null, actual = queryParameters["three"])
    }
}
