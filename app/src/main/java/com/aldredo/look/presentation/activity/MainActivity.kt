package com.aldredo.look.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.aldredo.core.base.mvvm.ModelFactory
import com.aldredo.look.R
import com.aldredo.look.di.ActivityComponent
import com.aldredo.look.presentation.mvvm.LookViewModel
import com.aldredo.look.util.Timer
import com.aldredo.look.util.TimerSubscriber
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var lookViewModel: LookViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ActivityComponent.create(this).inject(this)
        //  lookViewModel TODO(21)
        //lookViewModel = ViewModelProviders.of(this, ModelFactory(lookViewModel)).get(lookViewModel::class.java)
        findViewById<Button>(R.id.left).setOnClickListener {
            lookViewModel.onPause()
        }

        findViewById<Button>(R.id.center).setOnClickListener {
            lookViewModel.onResume()
        }

        findViewById<Button>(R.id.right).setOnClickListener {
            lookViewModel.onDestroy()
        }

        val codeTextView = findViewById<TextView>(R.id.code)
        lookViewModel.getCodeValue().observe(this, {
            codeTextView.text = it
        })

        lookViewModel.getMessageError().observe(this, {
            toast(it)
        })
    }

    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        lookViewModel.onPause()
        super.onPause()
    }

    override fun onResume() {
        lookViewModel.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        lookViewModel.onDestroy()
        super.onDestroy()
    }
}