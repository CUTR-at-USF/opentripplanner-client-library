package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import edu.usf.cutr.otp.planner.api.PlannerApi
import edu.usf.cutr.otp.planner.model.Planner
import edu.usf.cutr.otp.planner.model.RequestParameters
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    private lateinit var api: PlannerApi
    private lateinit var job: Job
    private lateinit var requestParameters: RequestParameters
    private val TAG = "MainActivity"

    override val coroutineContext: CoroutineContext
        get() = job + Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        job = Job()
        requestParameters = RequestParameters(
            fromPlace = latLong(41.84712, -87.64678),
            toPlace = latLong(41.84584, -87.65214),
            arriveBy = "false")
        api = PlannerApi("10.0.2.2", 8080, requestParameters)
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

    private fun latLong(lat: Double, long: Double): String {
        return "$lat,$long"
    }
}