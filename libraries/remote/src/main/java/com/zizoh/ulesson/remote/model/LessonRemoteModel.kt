package com.zizoh.ulesson.remote.model

/**
 * Created by zizoh on 15/January/2021.
 */

data class LessonRemoteModel(
    val id: Int,
    val name: String,
    val icon: String,
    val media_url: String,
    val subject_id: Int,
    val chapter_id: Int
) {
    var subjectName: String = ""
}
