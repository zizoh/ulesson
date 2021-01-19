package com.zizoh.ulesson.cache.mappers

import com.zizoh.ulesson.cache.mappers.base.CacheModelMapper
import com.zizoh.ulesson.cache.models.ChapterWithLessons
import com.zizoh.ulesson.data.models.ChapterWithLessonsEntity
import javax.inject.Inject

/**
 * Created by zizoh on 18/January/2021.
 */

class ChapterWithLessonsMapper @Inject constructor(
    private val chapterCacheMapper: ChapterCacheMapper,
    private val lessonCacheModelMapper: LessonCacheModelMapper
) : CacheModelMapper<ChapterWithLessons, ChapterWithLessonsEntity> {

    override fun mapToEntity(model: ChapterWithLessons): ChapterWithLessonsEntity {
        return with(model) {
            ChapterWithLessonsEntity(
                chapterCacheMapper.mapToEntity(chapterCacheModel),
                lessonCacheModelMapper.mapToEntityList(lessons)
            )
        }
    }

    override fun mapToModel(entity: ChapterWithLessonsEntity): ChapterWithLessons {
        return with(entity) {
            ChapterWithLessons(
                chapterCacheMapper.mapToModel(chapterEntity),
                lessonCacheModelMapper.mapToModelList(lessons)
            )
        }
    }
}