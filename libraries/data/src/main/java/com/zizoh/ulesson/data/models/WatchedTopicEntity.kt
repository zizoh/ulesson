package com.zizoh.ulesson.data.models

/**
 * Created by zizoh on 17/January/2021.
 */

data class WatchedTopicEntity(
    val id: Int,
    val name: String,
    val icon: String,
    val mediaUrl: String,
    val subjectId: Int,
    val subjectName: String,
    val chapterId: Int,
    var watchedDate: Long = 0L
)