package com.zizoh.ulesson.data.contract.cache

import com.zizoh.ulesson.data.models.ChapterEntity
import com.zizoh.ulesson.data.models.ChapterWithLessonsEntity

/**
 * Created by zizoh on 18/January/2021.
 */

interface ChapterCache {

    suspend fun saveChapters(chapters: List<ChapterEntity>)

    suspend fun getChaptersWithLessonsWithSubjectId(subjectId: Int): List<ChapterWithLessonsEntity>
}