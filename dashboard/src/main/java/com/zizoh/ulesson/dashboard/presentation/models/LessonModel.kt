package com.zizoh.ulesson.dashboard.presentation.models

data class LessonModel(
    val id: Int,
    val name: String,
    val icon: String,
    val mediaUrl: String,
    val subjectId: Int,
    val subjectName: String,
    val chapterId: Int,
    val chapterName: String
)