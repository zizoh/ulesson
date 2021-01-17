package com.zizoh.ulesson.data.mappers

import com.zizoh.ulesson.data.mappers.base.EntityMapper
import com.zizoh.ulesson.data.models.ChapterEntity
import com.zizoh.ulesson.domain.models.Chapter
import javax.inject.Inject

/**
 * Created by zizoh on 15/January/2021.
 */

class ChapterEntityMapper @Inject constructor(
    private val lessonEntityMapper: LessonEntityMapper
) : EntityMapper<ChapterEntity, Chapter> {

    override fun mapFromEntity(entity: ChapterEntity): Chapter {
        return with(entity) {
            Chapter(id, name, lessonEntityMapper.mapFromEntityList(lessons))
        }
    }

    override fun mapToEntity(domain: Chapter): ChapterEntity {
        return with(domain) {
            ChapterEntity(id, name, lessonEntityMapper.mapFromDomainList(lessons))
        }
    }
}