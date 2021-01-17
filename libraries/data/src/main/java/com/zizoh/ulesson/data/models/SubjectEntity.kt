package com.zizoh.ulesson.data.models

/**
 * Created by zizoh on 15/January/2021.
 */

data class SubjectEntity(
    val id: Int,
    val name: String,
    val icon: String,
    val chapters: List<ChapterEntity>
)