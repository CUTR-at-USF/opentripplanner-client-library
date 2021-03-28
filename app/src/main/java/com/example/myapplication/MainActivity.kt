package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import edu.usf.cutr.otp.planner.api.PlannerApi
import edu.usf.cutr.otp.planner.model.Planner
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    private lateinit var api: PlannerApi
    private lateinit var job: Job

    private val TAG = "MainActivity"

    override val coroutineContext: CoroutineContext
        get() = job + Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        job = Job()
        api = PlannerApi()
        api.getPlannerResource(
            success = { launch (Main) { logData(it) } },
            failure = ::handleError
        )
    }

    private fun logData(planner: Planner) {
        Log.d(TAG, "logData: $planner")
    }

    private fun handleError(ex: Throwable?) {
        ex?.message?.let { Log.d(TAG, it) }
        ex?.printStackTrace()
    }
}