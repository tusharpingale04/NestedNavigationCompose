package com.tushar.moviescompose.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.updateAndGet

abstract class BaseViewModel<BaseState>(
    initialState: BaseState
): ViewModel() {

    private val _state: MutableStateFlow<BaseState> = MutableStateFlow(initialState)
    val state: StateFlow<BaseState> = _state.asStateFlow()

    protected fun setState(update: (BaseState) -> BaseState) = _state.updateAndGet(update)

}

interface BaseState