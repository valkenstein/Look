package com.aldredo.look.util

import kotlinx.coroutines.*

class Timer {
    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.Default + job)
    private var subscriber: TimerSubscriber? = null

    fun addSubscriber(subscriber: TimerSubscriber) {
        this.subscriber = subscriber
    }

    fun removeSubscriber() {
        subscriber = null
    }

    private fun startCoroutineTimer(
        delayMillis: Long = 0,
        repeatMillis: Long = 0,
        action: () -> Unit
    ) = scope.launch(Dispatchers.IO) {
        delay(delayMillis)
        if (repeatMillis > 0) {
            while (true) {
                action()
                delay(repeatMillis)
            }
        } else {
            action()
        }
    }

    private val timer: Job = startCoroutineTimer(delayMillis = 0, repeatMillis = 1000) {
        scope.launch(Dispatchers.Main) {
            subscriber?.tick()
        }
    }

    fun startTimer() {
        timer.start()
    }

    fun cancelTimer() {
        timer.cancel()
    }
}