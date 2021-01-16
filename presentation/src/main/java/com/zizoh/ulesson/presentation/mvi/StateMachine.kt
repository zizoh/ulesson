package com.zizoh.ulesson.presentation.mvi

import kotlinx.coroutines.flow.*

abstract class StateMachine<I : ViewIntent, S : ViewState, out R : ViewResult>(
    private val intentProcessor: IntentProcessor<I, R>,
    private val reducer: ViewStateReducer<S, R>,
    initialIntent: I,
    initialState: S
) {

    private val viewStateFlow: MutableStateFlow<S> = MutableStateFlow(initialState)

    private val intentsChannel: MutableSharedFlow<I> = MutableSharedFlow<I>(1).apply {
        tryEmit(initialIntent)
    }

    fun processIntents(intents: Flow<I>): Flow<I> = intents.onEach { viewIntents ->
        intentsChannel.emit(viewIntents)
    }

    val viewState: Flow<S>
        get() = viewStateFlow

    val processor: Flow<S> = intentsChannel
        .flatMapMerge { action ->
            intentProcessor.intentToResult(action)
        }.scan(initialState) { previous, result ->
            reducer.reduce(previous, result)
        }.onEach { state ->
            viewStateFlow.value = state
        }
}