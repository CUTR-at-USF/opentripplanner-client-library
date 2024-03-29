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

/**
 * Abstract class that holds information common to all API request types
 */

abstract class Api {
    internal var apiKeyName: String = ""
    internal var apiKeyValue: String = ""
    internal var requestTimeoutMillis: Long = 10000L
    internal var connectionTimeoutMillis: Long = 10000L
    internal var socketTimeoutMillis: Long = 10000L
    internal var debug: Boolean = false
    internal var ignoreUnknownKeys: Boolean = true

    /**
     * Sets the API key if needed to make the request
     * @name name of the API key in the URL parameter
     * @value value of the API key in the URL parameter
     */
    fun apiKey(name: String, value: String) {
        apiKeyName = name
        apiKeyValue = value
    }

    /**
     * Sets the HTTP request timeout (a time period required to process an HTTP call: from sending a request to receiving a response) to [timeoutMillis], which is in milliseconds
     */
    fun requestTimeOutMillis(timeoutMillis: Long) {
        requestTimeoutMillis = timeoutMillis
    }

    /**
     * Sets the socket timeout (a maximum time of inactivity between two data packets when exchanging data with a server) to [timeoutMillis], which is in milliseconds
     */
    fun socketTimeOutMillis(timeoutMillis: Long) {
        socketTimeoutMillis = timeoutMillis
    }

    /**
     * Set the [value] to true to print out debug information
     */
    fun debug(value: Boolean) {
        debug = value
    }

    /**
     * Set the [value] to false to fail on unknown fields in the OTP JSON response.
     */
    fun ignoreUnknownKeys(value: Boolean) {
        ignoreUnknownKeys = value
    }
}
