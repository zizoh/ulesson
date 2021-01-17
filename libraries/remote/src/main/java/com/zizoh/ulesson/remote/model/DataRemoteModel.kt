package com.zizoh.ulesson.remote.model

/**
 * Created by zizoh on 15/January/2021.
 */

data class DataRemoteModel(
    val status: String,
    val message: String,
    val subjects: List<SubjectRemoteModel>
)