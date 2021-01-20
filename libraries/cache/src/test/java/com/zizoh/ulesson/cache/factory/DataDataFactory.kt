package com.zizoh.ulesson.cache.factory

import com.zizoh.ulesson.cache.models.LessonCacheModel
import com.zizoh.ulesson.cache.models.SubjectCacheModel
import java.util.*
import kotlin.random.Random

/**
 * Created by zizoh on 20/January/2021.
 */

object DataDataFactory {
    fun makeSubjectCacheModel(): SubjectCacheModel {
        return SubjectCacheModel(makeRandomInt(), makeRandomString(), makeRandomString())
    }

    fun makeSubjectCacheModels(count: Int): List<SubjectCacheModel> {
        val list: MutableList<SubjectCacheModel> = mutableListOf()
        for (position in 0 until count) {
            list.add(makeSubjectCacheModel())
        }
        return list
    }

    fun makeLessonCacheModel(): LessonCacheModel {
        return LessonCacheModel(
            makeRandomInt(),
            makeRandomString(),
            makeRandomString(),
            makeRandomString(),
            makeRandomInt(),
            makeRandomString(),
            makeRandomInt(),
            makeRandomString()
        )
    }

    fun makeLessonCacheModels(count: Int): List<LessonCacheModel> {
        val list: MutableList<LessonCacheModel> = mutableListOf()
        for (position in 0 until count) {
            list.add(makeLessonCacheModel())
        }
        return list
    }

    private fun makeRandomString(): String = UUID.randomUUID().toString()

    private fun makeRandomInt(): Int = Random.nextInt()
}