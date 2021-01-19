package com.zizoh.ulesson.remote.mapper

import com.zizoh.ulesson.data.models.LessonEntity
import com.zizoh.ulesson.remote.mapper.base.RemoteModelMapper
import com.zizoh.ulesson.remote.model.LessonRemoteModel
import javax.inject.Inject

/**
 * Created by zizoh on 15/January/2021.
 */

class LessonRemoteModelMapper @Inject constructor() :
    RemoteModelMapper<LessonRemoteModel, LessonEntity> {

    override fun mapFromModel(model: LessonRemoteModel): LessonEntity {
        return with(model) {
            LessonEntity(id, name, icon, media_url, subject_id, chapter_id)
        }
    }
}