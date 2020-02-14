package com.example.threadingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.lang.Math.PI

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().unregister(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onAsyncTaskEvent(event: AsyncTaskEvent) {
        tvAsyncResult.text = event.resultMessage
    }

    fun runThreadRunnable()
    {
        val pi = PI.toString()
        var piToTwelve : String = ""
        val thread = Thread(Runnable {
            for(i in 0 until 13)
            {
                piToTwelve += pi[i]
            }

            runOnUiThread { tvRunnablesResult.text = piToTwelve }
        })

        thread.start()
    }

}