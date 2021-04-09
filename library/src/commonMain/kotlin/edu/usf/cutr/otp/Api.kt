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

package edu.usf.cutr.otp

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

/**
 * Manager Class to make HTTP requests to the OTP Bike Rental API
 * @param url - Full URL to Bike Rental Information, Ex: "https://10.0.2.2:8080/otp/routers/default/bike_rental"
 */

abstract class Api {
    internal var apiKeyName: String = ""
    internal var apiKeyValue: String = ""

    /**
     * Sets the API key if needed to make the request
     * @name name of the API key in the URL parameter
     * @value value of the API key in the URL parameter
     */
    fun apiKey(name: String, value: String) {
        apiKeyName = name
        apiKeyValue = value
    }
}
