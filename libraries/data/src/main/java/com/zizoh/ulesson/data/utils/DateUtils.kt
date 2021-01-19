package com.zizoh.ulesson.data.utils

import org.joda.time.Instant

/**
 * Created by zizoh on 17/January/2021.
 */

object DateUtils {
    fun getCurrentTime(): Long {
        return Instant.now().millis
    }
}