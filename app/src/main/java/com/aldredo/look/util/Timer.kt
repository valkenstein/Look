package com.aldredo.look.util

import kotlinx.coroutines.*

class Timer(private val second: Long) {
    private var pause = false
    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.Default + job)
    private var subscriber: TimerSubscriber? = null

    fun addSubscriber(subscriber: TimerSubscriber) {
        this.subscriber = subscriber
    }

    fun removeSubscriber() {
        subscriber = null
    }

    private fun startCoroutineTimer() = scope.launch {
        while (true) {
            scope.launch(Dispatchers.Main) {
                subscriber?.tick()
            }
            delay(second * 1000)
        }
    }

    fun startTimer() {
        startCoroutineTimer()
    }

    fun cancelTimer() {
        scope.coroutineContext.cancelChildren()
    }

    fun pause() {
        pause = true
        scope.coroutineContext.cancelChildren()
    }

    fun isPause() = pause
}