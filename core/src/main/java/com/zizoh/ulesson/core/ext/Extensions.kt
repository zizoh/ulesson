package com.zizoh.ulesson.core.ext

val Throwable.errorMessage: String
    get() = message ?: localizedMessage ?: "An error occurred"