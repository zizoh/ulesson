package com.zizoh.ulesson.domain.models

/**
 * Created by zizoh on 15/January/2021.
 */

data class Lesson(
    val id: Int,
    val name: String,
    val icon: String,
    val mediaUrl: String,
    val subjectId: Int,
    val subjectName: String,
    val chapterId: Int
)