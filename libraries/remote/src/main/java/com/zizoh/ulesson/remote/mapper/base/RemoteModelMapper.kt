package com.zizoh.ulesson.remote.mapper.base

interface RemoteModelMapper<in M, out E> {

    fun mapFromModel(model: M): E

    fun mapFromModelList(models: List<M>): List<E> {
        val list = mutableListOf<E>()
        models.forEach {
            list.add(mapFromModel(it))
        }
        return list
    }
}