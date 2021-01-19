package com.zizoh.ulesson.domain.models

/**
 * Created by zizoh on 15/January/2021.
 */

data class Chapter(
    val id: Int,
    val name: String,
    val lessons: List<Lesson>,
    val subjectName: String
)