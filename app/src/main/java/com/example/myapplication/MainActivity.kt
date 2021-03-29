/*
   Copyright University of South Florida 2021

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import edu.usf.cutr.otp.plan.api.PlannerApi
import edu.usf.cutr.otp.plan.model.Planner
import edu.usf.cutr.otp.plan.model.RequestParameters
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
        api.getPlan(
            success = { launch (Main) { logData(it) } },
            failure = ::handleError
        )
    }

    private fun <T: Any> logData(t : T) {
        Log.d(TAG, "logData: $t")
    }

    private fun handleError(ex: Throwable?) {
        ex?.message?.let { Log.d(TAG, it) }
        ex?.printStackTrace()
    }

    private fun latLong(lat: Double, long: Double): String {
        return "$lat,$long"
    }
}