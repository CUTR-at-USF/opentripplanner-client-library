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

package edu.usf.cutr.otp.bike_rental.api

import edu.usf.cutr.otp.ApplicationDispatcher
import edu.usf.cutr.otp.bike_rental.model.Stations
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class BikeRentalApi(private val url: String,
                    private val locale: String? = null,
                    private val lowerLeft: String? = null,
                    private val upperRight: String? = null) {

    fun getBikeRental(
        success: (Stations) -> Unit, failure: (Throwable?) -> Unit) {

        GlobalScope.launch(ApplicationDispatcher) {
            try {
                val parameters = buildParameters(locale, lowerLeft, upperRight)
                val url = URLBuilder(
                    parameters = parameters,
                ).takeFrom(url).buildString()

                val json = HttpClient().get<String>(url)
                Json.decodeFromString(Stations.serializer(), json).also(success)

            } catch (ex: Exception) {
                failure(ex)
            }
        }
    }

    private fun buildParameters(locale: String?, lowerLeft: String?, upperRight: String?): ParametersBuilder {
        val parameters = ParametersBuilder()
        if (locale != null) {
            parameters.append("locale", locale)
        }
        if (lowerLeft != null) {
            parameters.append("lowerLeft", lowerLeft)
        }
        if (upperRight != null) {
            parameters.append("upperRight", upperRight)
        }
        return parameters
    }
}