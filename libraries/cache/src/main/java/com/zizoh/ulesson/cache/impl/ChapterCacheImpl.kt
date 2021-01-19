package com.zizoh.ulesson.cache.impl

import com.zizoh.ulesson.cache.mappers.ChapterCacheMapper
import com.zizoh.ulesson.cache.mappers.ChapterWithLessonsMapper
import com.zizoh.ulesson.cache.models.ChapterCacheModel
import com.zizoh.ulesson.cache.models.ChapterWithLessons
import com.zizoh.ulesson.cache.room.dao.ChapterDao
import com.zizoh.ulesson.data.contract.cache.ChapterCache
import com.zizoh.ulesson.data.models.ChapterEntity
import com.zizoh.ulesson.data.models.ChapterWithLessonsEntity
import javax.inject.Inject

/**
 * Created by zizoh on 18/January/2021.
 */

class ChapterCacheImpl @Inject constructor(
    private val chapterDao: ChapterDao,
    private val chapterCacheMapper: ChapterCacheMapper,
    private val chapterWithLessonsMapper: ChapterWithLessonsMapper
) : ChapterCache {

    override suspend fun saveChapters(chapters: List<ChapterEntity>) {
        val chaptersCacheModels: List<ChapterCacheModel> =
            chapterCacheMapper.mapToModelList(chapters)
        chapterDao.saveChapters(chaptersCacheModels)
    }

    override suspend fun getChaptersWithLessonsWithSubjectId(subjectId: Int): List<ChapterWithLessonsEntity> {
        val chaptersWithLessons: List<ChapterWithLessons> =
            chapterDao.getChapterWithLessonsWithSubjectId(subjectId)
        return chapterWithLessonsMapper.mapToEntityList(chaptersWithLessons)

    }
}