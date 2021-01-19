package com.zizoh.ulesson.remote.impl

import com.zizoh.ulesson.data.contract.remote.SubjectRemote
import com.zizoh.ulesson.data.models.SubjectEntity
import com.zizoh.ulesson.remote.ApiService
import com.zizoh.ulesson.remote.mapper.SubjectRemoteModelMapper
import com.zizoh.ulesson.remote.model.SubjectRemoteModel
import javax.inject.Inject

/**
 * Created by zizoh on 15/January/2021.
 */

class SubjectRemoteImpl @Inject constructor(
    private val apiService: ApiService,
    private val subjectRemoteModelMapper: SubjectRemoteModelMapper
) : SubjectRemote {

    override suspend fun getSubjects(): List<SubjectEntity> {
        val subjects: List<SubjectRemoteModel> = apiService.getData().data.subjects
        return subjectRemoteModelMapper.mapFromModelList(subjects)
    }
}