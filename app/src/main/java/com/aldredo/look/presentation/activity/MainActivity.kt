package com.aldredo.look.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.aldredo.look.R
import com.aldredo.look.util.Timer
import com.aldredo.look.util.TimerSubscriber

class MainActivity : AppCompatActivity(), TimerSubscriber {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val t = Timer(3)
        t.addSubscriber(this)
        findViewById<Button>(R.id.start).setOnClickListener {
            t.startTimer()
        }
        findViewById<Button>(R.id.stop).setOnClickListener {
            t.cancelTimer()
        }
    }

    override fun tick() {
        print("")
    }
}