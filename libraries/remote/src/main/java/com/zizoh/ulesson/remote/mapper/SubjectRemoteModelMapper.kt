package com.zizoh.ulesson.remote.mapper

import com.zizoh.ulesson.data.models.SubjectEntity
import com.zizoh.ulesson.remote.mapper.base.RemoteModelMapper
import com.zizoh.ulesson.remote.model.SubjectRemoteModel
import javax.inject.Inject

/**
 * Created by zizoh on 15/January/2021.
 */

class SubjectRemoteModelMapper @Inject constructor(
    private val chapterRemoteModelMapper: ChapterRemoteModelMapper
) : RemoteModelMapper<SubjectRemoteModel, SubjectEntity> {

    override fun mapFromModel(model: SubjectRemoteModel): SubjectEntity {
        return with(model) {
            SubjectEntity(
                id,
                name,
                icon,
                chapterRemoteModelMapper.mapFromModelList(chapters)
            )
        }
    }
}