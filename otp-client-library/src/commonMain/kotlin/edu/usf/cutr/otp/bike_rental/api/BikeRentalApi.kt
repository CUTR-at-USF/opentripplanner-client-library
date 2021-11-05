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

import edu.usf.cutr.otp.Api
import edu.usf.cutr.otp.ApplicationDispatcher
import edu.usf.cutr.otp.bike_rental.model.Stations
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

/**
 * Manager Class to make HTTP requests to the OTP Bike Rental API
 * @param url - Full URL to Bike Rental Information, Ex: "https://10.0.2.2:8080/otp/routers/default/bike_rental"
 */

class BikeRentalApi(private val url: String,
                    private val locale: String? = null,
                    private val lowerLeft: String? = null,
                    private val upperRight: String? = null): Api() {

    /**
     * Function that fetches Bike Rental information.
     * @param success -> Represents a successful communication with the server
     * @param failure -> Represents a failed outcome.
     */
    fun getBikeRental(
        success: (Stations) -> Unit, failure: (Throwable?) -> Unit) {

        GlobalScope.launch(ApplicationDispatcher) {
            try {
                val parameters = buildParameters(locale, lowerLeft, upperRight)
                val url = URLBuilder(
                    parameters = parameters,
                ).takeFrom(url).buildString()

                val httpClient = HttpClient {
                    install(HttpTimeout) {
                        requestTimeoutMillis = this@BikeRentalApi.requestTimeoutMillis
                        socketTimeoutMillis = this@BikeRentalApi.socketTimeoutMillis
                    }
                }
                if (debug) {
                    println(url)
                }
                val json = httpClient.get<String>(url)
                httpClient.close()
                if (debug) {
                    println(json)
                }
                val format = Json { ignoreUnknownKeys = this@BikeRentalApi.ignoreUnknownKeys }
                format.decodeFromString(Stations.serializer(), json).also(success)
            } catch (ex: Exception) {
                failure(ex)
            }
        }
    }

    /**
     * Function to build the parameter values that is appended to the URL above
     */
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
        if (apiKeyName.isNotEmpty() && apiKeyValue.isNotEmpty()) {
            parameters.append(apiKeyName, apiKeyValue)
        }
        return parameters
    }
}
