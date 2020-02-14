package com.example.threadingapp

import android.os.AsyncTask
import android.util.Log
import org.greenrobot.eventbus.EventBus
import  java.lang.StringBuilder
import kotlin.random.Random

class AsyncTaskSort : AsyncTask<String, String, String>() {

    var arrayOFRandInt = IntArray(1000)
    var sortArray : String = ""

    override fun onPreExecute() {
        super.onPreExecute()
    }

    //Runs on Worker thread
    //The task that needs to be ran
    override fun doInBackground(vararg params: String?): String
    {

        for(i in 0 until arrayOFRandInt.size)
        {
            arrayOFRandInt[i] = Random.nextInt(0,100)
        }

        arrayOFRandInt.sort()

        for (i in 0 until arrayOFRandInt.size)
        {
            sortArray += arrayOFRandInt[i].toString() +", "
        }
            return sortArray
    }

    //Runs on Main thread
    // Gets updates about task status
    override fun onProgressUpdate(vararg values: String?) {
        super.onProgressUpdate(*values)
    }

    //Runs on Main thread
    //Reports result of the task
    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        EventBus.getDefault().post(AsyncTaskEvent( result?: "No result"))
    }

}



