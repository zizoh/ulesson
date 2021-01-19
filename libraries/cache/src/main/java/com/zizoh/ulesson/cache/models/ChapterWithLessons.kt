package com.zizoh.ulesson.cache.models

import androidx.room.Embedded
import androidx.room.Relation

/**
 * Created by zizoh on 18/January/2021.
 */

data class ChapterWithLessons(
    @Embedded
    val chapterCacheModel: ChapterCacheModel,
    @Relation(
        parentColumn = "chapterId",
        entityColumn = "chapterId"
    )
    val lessons: List<LessonCacheModel>
)