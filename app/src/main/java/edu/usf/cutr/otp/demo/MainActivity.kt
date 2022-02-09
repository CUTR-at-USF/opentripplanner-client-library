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
package edu.usf.cutr.otp.demo

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import edu.usf.cutr.otp.bike_rental.api.BikeRentalApi
import edu.usf.cutr.otp.plan.api.PlanApi
import edu.usf.cutr.otp.plan.model.RequestParameters
import edu.usf.cutr.otp.plan.model.core.TraverseModes
import edu.usf.cutr.otp.serverinfo.api.ServerInfoApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    private lateinit var planApi: PlanApi
    private lateinit var bikeRentalApi: BikeRentalApi
    private lateinit var serverInfoApi: ServerInfoApi
    private lateinit var job: Job
    private lateinit var requestParameters: RequestParameters
    private val TAG = "MainActivity"

    //ui
    private lateinit var planButton: Button
    private lateinit var bikeRentalButton: Button
    private lateinit var serverInfoButton: Button
    private lateinit var responseText: TextView

    override val coroutineContext: CoroutineContext
        get() = job + Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        job = Job()

        // ui init
        planButton = findViewById(R.id.plan_button)
        bikeRentalButton = findViewById(R.id.bike_rental_button)
        serverInfoButton = findViewById(R.id.server_info_button)
        responseText = findViewById(R.id.response_view)

        val startDateTime = getDateFromTimeStamp("2020-10-01T00:00:00.000", "yyyy-MM-dd'T'HH:mm:ss.SSS") // Processed time format from CalPoly

        val date = startDateTime.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"))
        val time = startDateTime.format(DateTimeFormatter.ofPattern("h:mma"))

        planButton.setOnClickListener {
            requestParameters = RequestParameters(
                fromPlace = latLong(41.77887686, -87.59492544),
                toPlace = latLong(41.70658788, -87.62336651),
                date = date.toString(), time = time.toString(),
                traverseModes = TraverseModes.TRANSIT
            )
            planApi = PlanApi("http://10.0.2.2:8080/otp/routers/default/plan",  requestParameters)
            planApi.debug(true)
            planApi.getPlan(
                success = { launch (Main) { logData(it) } },
                failure = ::handleError
            )
        }

        bikeRentalButton.setOnClickListener {
            bikeRentalApi = BikeRentalApi("http://10.0.2.2:8080/otp/routers/default/bike_rental",
                lowerLeft = latLong(41.81712, -87.62678),
                upperRight = latLong(41.84584, -87.65214))
            bikeRentalApi.debug(true)
            bikeRentalApi.getBikeRental(
                success = {launch (Main) { logData(it) }},
                failure = ::handleError
            )
        }

        serverInfoButton.setOnClickListener {
            serverInfoApi = ServerInfoApi("http://10.0.2.2:8080/otp")
            serverInfoApi.debug(true)
            serverInfoApi.getServerInfo(
                success = {launch (Main) { logData(it) }},
                failure = ::handleError
            )
        }
    }

    private fun <T: Any> logData(t : T) {
        responseText.text = "$t"
        Log.d(TAG, "logData: $t")
    }

    private fun handleError(ex: Throwable?) {
        ex?.message?.let { Log.d(TAG, it) }
        ex?.printStackTrace()
    }

    private fun latLong(lat: Double, long: Double): String {
        return "$lat,$long"
    }

    /**
     * Function to convert timestamp to LocalDateTime object.
     * @param timeStamp String timestamp from the data
     * @param pattern Pattern of the data
     * @return returns LocalDateTime object
     */
    fun getDateFromTimeStamp(timeStamp: String, pattern: String): LocalDateTime {
        val formatter = DateTimeFormatter.ofPattern(pattern)
        return LocalDateTime.parse(timeStamp, formatter)
    }
}