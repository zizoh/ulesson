package com.zizoh.ulesson.dashboard.presentation.mappers

import com.zizoh.ulesson.dashboard.presentation.models.ChapterModel
import com.zizoh.ulesson.domain.models.Chapter
import com.zizoh.ulesson.presentation.mapper.ModelMapper
import javax.inject.Inject

/**
 * Created by zizoh on 15/January/2021.
 */

class ChapterModelMapper @Inject constructor(
    private val lessonModelMapper: LessonModelMapper
) : ModelMapper<ChapterModel, Chapter> {

    override fun mapToModel(domain: Chapter): ChapterModel {
        return with(domain) {
            ChapterModel(id, name, lessonModelMapper.mapToModelList(lessons))
        }
    }

    override fun mapToDomain(model: ChapterModel): Chapter {
        return with(model) {
            Chapter(id, name, lessonModelMapper.mapToDomainList(lessons))
        }
    }
}