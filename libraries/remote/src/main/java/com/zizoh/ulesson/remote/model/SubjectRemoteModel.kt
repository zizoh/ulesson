package com.zizoh.ulesson.remote.model

/**
 * Created by zizoh on 15/January/2021.
 */

data class SubjectRemoteModel(
    val id: Int,
    val name: String,
    val icon: String,
    val chapters: List<ChapterRemoteModel>
)
