package com.aldredo.look.presentation.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aldredo.look.domain.state.StateScreenBd
import com.aldredo.look.domain.state.StateServer
import com.aldredo.look.domain.usecase.CodeUseCase
import com.aldredo.look.domain.usecase.ProfileUseCase
import com.aldredo.look.domain.usecase.StateServerUseCase
import com.aldredo.look.util.Timer
import com.aldredo.look.util.TimerSubscriber
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.random.Random

class LookViewModel @Inject constructor(
    private val stateServerUseCase: StateServerUseCase,
    private val codeUseCase: CodeUseCase,
    private val profileUseCase: ProfileUseCase,
) : ViewModel(), TimerSubscriber {
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
        when (val stateServer = checkStateServerAsync()) {
            is StateServer.Success -> {
                addScreen()
            }
            is StateServer.Error -> {
                errorMessage.postValue(stateServer.message)
            }
        }
    }

    private suspend fun addScreen() {
        when (val screenBd = checkScreenToBd()) {
            is StateScreenBd.Result -> {
                code.postValue(screenBd.result.name)
            }
            is StateScreenBd.Empty -> {
                val generationCode = generationCode().toString()
                code.postValue(generationCode)
                timer.startTimer()
            }
        }
    }

    private suspend fun checkScreenToBd() = withContext(Dispatchers.IO) {
        profileUseCase.getProfileBd()
    }

    override fun tick() {
        codeUseCase.putCode()
    }

    private fun generationCode() = Random.nextInt(100000, 999999)

    private suspend fun checkStateServerAsync() = withContext(Dispatchers.IO) {
        stateServerUseCase.getStateServer()
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