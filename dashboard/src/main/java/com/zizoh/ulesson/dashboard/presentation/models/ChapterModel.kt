package com.zizoh.ulesson.dashboard.presentation.models

data class ChapterModel(
    val id: Int,
    val name: String,
    val lessons: List<LessonModel>
)