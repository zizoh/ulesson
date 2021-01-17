package com.zizoh.ulesson.core.di

import com.zizoh.ulesson.data.repository.SubjectRepositoryImpl
import com.zizoh.ulesson.domain.repository.SubjectRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by zizoh on 17/January/2021.
 */

@InstallIn(SingletonComponent::class)
@Module
interface DataModule {

    @get:Binds
    val SubjectRepositoryImpl.subjectRepository: SubjectRepository

}