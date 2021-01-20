package com.zizoh.ulesson.data.models

/**
 * Created by zizoh on 15/January/2021.
 */

data class LessonEntity(
    val id: Int,
    val name: String,
    val icon: String,
    val mediaUrl: String,
    val subjectId: Int,
    val chapterId: Int,
    var chapterName: String
)