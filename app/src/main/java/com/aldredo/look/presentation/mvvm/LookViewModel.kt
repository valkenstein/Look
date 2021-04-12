package com.aldredo.look.presentation.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aldredo.look.domain.state.StateCode
import com.aldredo.look.domain.state.StateProfile
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
    private val timer = Timer(10)
    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private val errorMessage = MutableLiveData<String>()
    private val showTitle = MutableLiveData<String>()
    private var generationCode: String

    fun getCodeValue(): LiveData<String> = showTitle
    fun getMessageError(): LiveData<String> = errorMessage

    init {
        timer.addSubscriber(this)
        generationCode = generationCode().toString()
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
        when (checkScreenToBd()) {
            is StateScreenBd.Result -> {
                getProfile()
            }
            is StateScreenBd.Empty -> {
                showTitle.postValue(generationCode)
                timer.startTimer()
            }
        }
    }

    private suspend fun checkScreenToBd() = withContext(Dispatchers.IO) {
        profileUseCase.getProfileBd()
    }

    override fun tick() {
        scope.launch {
            sendCodeToServerAsync(generationCode)
        }
    }

    private suspend fun sendCodeToServerAsync(code: String) =
        withContext(Dispatchers.IO) {
            when (val stateCode = codeUseCase.putCode(code)) {
                is StateCode.Result -> {
                    val cookie = stateCode.result.cookie
                    timerCancel()
                    profileUseCase.saveCookieToBd(cookie)
                    getProfile()
                }
                is StateCode.Error -> {
                    generationCode = generationCode().toString()
                    showTitle.postValue(generationCode)
                    errorMessage.postValue(stateCode.message + " - генерация нового кода")
                }
                is StateCode.Success -> {
                    Log.d("LookViewModel", "ждем подтверждения кода")
                }
            }
        }

    private suspend fun timerCancel() = withContext(Dispatchers.Main) {
        timer.cancelTimer()
    }

    private fun getProfile() = scope.launch {
        when (val stateProfile = getProfileAsync()) {
            is StateProfile.Result -> {
                showTitle.postValue(stateProfile.result.cookie)
            }
            is StateProfile.Error -> {
                errorMessage.postValue(stateProfile.message)
            }
        }
    }

    private suspend fun getProfileAsync() = withContext(Dispatchers.IO) {
        profileUseCase.getProfileServer()
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
        timer.cancelTimer()
        timer.removeSubscriber()
    }
}