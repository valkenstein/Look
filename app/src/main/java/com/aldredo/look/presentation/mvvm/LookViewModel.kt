package com.aldredo.look.presentation.mvvm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aldredo.look.domain.state.StateServer
import com.aldredo.look.domain.usecase.StateServerUseCase
import com.aldredo.look.util.Timer
import com.aldredo.look.util.TimerSubscriber
import kotlinx.coroutines.*
import javax.inject.Inject

class LookViewModel @Inject constructor(private val stateServerUseCase: StateServerUseCase) :
    ViewModel(), TimerSubscriber {
    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private val errorMessage = MutableLiveData<String>()
    private val timer = Timer(1)

    init {
        timer.addSubscriber(this)
        checkStateServer()
    }

    private fun checkStateServer() = scope.launch {
        when (val state = checkStateAsync()) {
            is StateServer.Success -> {
                checkAddScreen()
            }
            is StateServer.Error -> {
                errorMessage.postValue(state.message)
            }
        }
    }

    private fun checkAddScreen() {

    }

    private suspend fun checkStateAsync() = withContext(Dispatchers.IO) {
        stateServerUseCase.getStateServer()
    }

    override fun tick() {
        Log.e("MainActivity", "tick")
    }

    fun onResume() {
        if (timer.isPause())
            timer.startTimer()
    }

    fun onPause() {
        timer.pause()
    }

    fun onDestroy() {
        timer.removeSubscriber()
    }
}