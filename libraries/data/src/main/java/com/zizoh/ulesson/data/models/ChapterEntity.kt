package com.zizoh.ulesson.data.models

/**
 * Created by zizoh on 15/January/2021.
 */

data class ChapterEntity(
    val id: Int,
    val name: String,
    val lessons: List<LessonEntity>,
    var subjectId: Int
)