package com.zizoh.ulesson.dashboard.ui.dashboard.adapter.resourceprovider.exception

import com.zizoh.ulesson.domain.exception.noParamMessage

/**
 * Created by zizoh on 16/January/2021.
 */

class NoResourceException(errorMessage: String = noParamMessage) :
    IllegalArgumentException(errorMessage)