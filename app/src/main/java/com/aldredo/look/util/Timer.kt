package com.aldredo.look.util

import kotlinx.coroutines.*

class Timer(private val second: Long) {
    private var pause = false
    private var startTimer = false
    private val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())
    private var subscriber: TimerSubscriber? = null

    fun addSubscriber(subscriber: TimerSubscriber) {
        this.subscriber = subscriber
    }

    fun removeSubscriber() {
        subscriber = null
    }

    private fun startCoroutineTimer() = scope.launch {
        while (true) {
            mainThread()
            delay(second * 1000)
        }
    }

    private fun mainThread() = scope.launch(Dispatchers.Main) {
        subscriber?.tick()
    }

    fun start() {
        startTimer = true
        startCoroutineTimer()
    }

    fun cancel() {
        pause = false
        startTimer = false
        scope.coroutineContext.cancelChildren()
    }

    fun pause() {
        if (startTimer) {
            pause = true
            scope.coroutineContext.cancelChildren()
        }
    }

    fun isPause() = startTimer and pause
}