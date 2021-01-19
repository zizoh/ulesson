package com.zizoh.ulesson.data.models

/**
 * Created by zizoh on 18/January/2021.
 */

data class ChapterWithLessonsEntity(
    val chapterEntity: ChapterEntity,
    val lessons: List<LessonEntity>
)