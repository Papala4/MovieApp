package com.space.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

interface UIEvent

interface UIEffect

abstract class BaseViewModel<State, Event : UIEvent, Effect : UIEffect>(initialState: State) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<State> = _state.asStateFlow()

    private val _effect = Channel<Effect>(Channel.BUFFERED)
    val effect: Flow<Effect> = _effect.receiveAsFlow()

    protected fun setState(block: State.() -> State) {
        _state.update { it.block() }
    }

    protected fun sendEffect(effect: Effect) {
        viewModelScope.launch(Dispatchers.Main.immediate) {
            _effect.send(effect)
        }
    }

    abstract fun onEvent(event: Event)

}