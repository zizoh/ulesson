package com.zizoh.ulesson.remote.mapper

import com.zizoh.ulesson.data.models.ChapterEntity
import com.zizoh.ulesson.remote.mapper.base.RemoteModelMapper
import com.zizoh.ulesson.remote.model.ChapterRemoteModel
import javax.inject.Inject

/**
 * Created by zizoh on 15/January/2021.
 */

class ChapterRemoteModelMapper @Inject constructor(
    private val lessonRemoteModelMapper: LessonRemoteModelMapper
) : RemoteModelMapper<ChapterRemoteModel, ChapterEntity> {

    override fun mapFromModel(model: ChapterRemoteModel): ChapterEntity {
        return with(model) {
            ChapterEntity(
                id,
                name,
                lessonRemoteModelMapper.mapFromModelList(lessons),
                0,
                ""
            )
        }
    }
}