package com.zizoh.ulesson.data.repository

import com.zizoh.ulesson.data.contract.remote.SubjectRemote
import com.zizoh.ulesson.data.mappers.SubjectEntityMapper
import com.zizoh.ulesson.data.models.SubjectEntity
import com.zizoh.ulesson.domain.models.Subject
import com.zizoh.ulesson.domain.repository.SubjectRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SubjectRepositoryImpl @Inject constructor(
    private val subjectRemote: SubjectRemote,
    private val subjectEntityMapper: SubjectEntityMapper
) : SubjectRepository {

    override fun getSubjects(): Flow<List<Subject>> {
        return flow {
            val subjects: List<SubjectEntity> = subjectRemote.getSubjects()
            emit(subjectEntityMapper.mapFromEntityList(subjects))
        }
    }
}