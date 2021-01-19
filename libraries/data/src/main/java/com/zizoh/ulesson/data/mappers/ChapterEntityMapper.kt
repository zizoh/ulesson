package com.zizoh.ulesson.data.mappers

import com.zizoh.ulesson.data.mappers.base.EntityMapper
import com.zizoh.ulesson.data.models.ChapterWithLessonsEntity
import com.zizoh.ulesson.domain.models.Chapter
import javax.inject.Inject

/**
 * Created by zizoh on 19/January/2021.
 */

class ChapterEntityMapper @Inject constructor(
    private val lessonEntityMapper: LessonEntityMapper
) :
    EntityMapper<ChapterWithLessonsEntity, Chapter> {
    override fun mapFromEntity(entity: ChapterWithLessonsEntity): Chapter {
        return with(entity) {
            Chapter(
                chapterEntity.id,
                chapterEntity.name,
                lessonEntityMapper.mapFromEntityList(lessons),
                chapterEntity.subjectName
            )
        }
    }

    override fun mapToEntity(domain: Chapter): ChapterWithLessonsEntity {
        throw IllegalStateException("Not mapping to entity")
    }
}