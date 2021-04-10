package com.aldredo.look.presentation.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aldredo.look.domain.state.StateServer
import com.aldredo.look.domain.usecase.StateServerUseCase
import com.aldredo.look.util.Timer
import com.aldredo.look.util.TimerSubscriber
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.random.Random

class LookViewModel @Inject constructor(private val stateServerUseCase: StateServerUseCase) :
    ViewModel(), TimerSubscriber {
    private val timer = Timer(1)
    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private val errorMessage = MutableLiveData<String>()
    private val code = MutableLiveData<String>()

    fun getCodeValue(): LiveData<String> = code
    fun getMessageError(): LiveData<String> = errorMessage


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
        code.postValue(generationCode().toString())
    }

    private fun generationCode() = Random.nextInt(999999)

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