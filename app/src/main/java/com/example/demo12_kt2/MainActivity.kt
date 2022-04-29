package com.example.demo12_kt2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var textView: TextView
    private var counter: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textView1)
        setupCounter()
        findViewById<Button>(R.id.button).setOnClickListener { v -> wait1() }
        findViewById<Button>(R.id.button2).setOnClickListener { v->wait2() }
    }

    private fun wait2() {
        Thread {
            Thread.sleep(5000)
            handler.sendEmptyMessage(SWITCH_LAYOUT)
        }.start()
    }

    private fun wait1() {
        Thread {
            Thread.sleep(5000)
            runOnUiThread { setContentView(R.layout.activity_main2) }
        }.start()
    }

    private fun setupCounter() {
        Thread {
            while (true) {
                Thread.sleep(100)
                runOnUiThread { textView.text = "counter=${counter++}" }
            }
        }.start()
    }

    companion object {
        const val SWITCH_LAYOUT = 888
    }

    private val handler = Handler { m ->
        when (m.what) {
            SWITCH_LAYOUT -> setContentView(R.layout.activity_main2)
        }
        false
    }
}