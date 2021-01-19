package com.zizoh.ulesson.dashboard.presentation.mappers

import com.zizoh.ulesson.dashboard.presentation.models.SubjectModel
import com.zizoh.ulesson.domain.models.Subject
import com.zizoh.ulesson.presentation.mapper.ModelMapper
import javax.inject.Inject

/**
 * Created by zizoh on 15/January/2021.
 */

class SubjectModelMapper @Inject constructor(
    private val chapterModelMapper: ChapterModelMapper
) : ModelMapper<SubjectModel, Subject> {

    override fun mapToModel(domain: Subject): SubjectModel {
        return with(domain) {
            SubjectModel(id, name, icon, chapterModelMapper.mapToModelList(chapters))
        }
    }

    override fun mapToDomain(model: SubjectModel): Subject {
        return with(model) {
            Subject(id, name, icon, chapterModelMapper.mapToDomainList(chapters))
        }
    }
}