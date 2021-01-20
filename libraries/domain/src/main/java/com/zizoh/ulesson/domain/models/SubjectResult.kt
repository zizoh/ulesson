package com.zizoh.ulesson.domain.models

/**
 * Created by zizoh on 20/January/2021.
 */

data class SubjectResult(val subjects: List<Subject>, val error: Throwable? = null)
