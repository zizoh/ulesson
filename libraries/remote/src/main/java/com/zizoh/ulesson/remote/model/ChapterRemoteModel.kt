package com.zizoh.ulesson.remote.model

/**
 * Created by zizoh on 15/January/2021.
 */

data class ChapterRemoteModel(
    val id: Int,
    val name: String,
    val lessons: List<LessonRemoteModel>
)
