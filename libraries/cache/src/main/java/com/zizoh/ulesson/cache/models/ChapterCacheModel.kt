package com.zizoh.ulesson.cache.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by zizoh on 18/January/2021.
 */

@Entity(tableName = "chapters")
data class ChapterCacheModel(
    @PrimaryKey
    val chapterId: Int,
    val name: String,
    val subjectId: Int,
    val subjectName: String
)