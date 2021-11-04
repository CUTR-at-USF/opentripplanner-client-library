package edu.usf.cutr.otp.serverinfo.api

import edu.usf.cutr.otp.Api
import edu.usf.cutr.otp.ApplicationDispatcher
import edu.usf.cutr.otp.serverinfo.model.ServerInfo
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

/**
 * Manager Class to make HTTP requests to the OTP Server Info API
 * @param url - Full url to the Server info API, Ex: "https://10.0.2.2:8080/otp"
 */
class ServerInfoApi (private val url: String): Api() {

    /**
     * Function that fetches Server information.
     * @param success -> Represents a successful communication with the server
     * @param failure -> Represents a failed outcome.
     */

    fun getServerInfo(
        success: (ServerInfo) -> Unit, failure: (Throwable?) -> Unit) {

        GlobalScope.launch(ApplicationDispatcher) {
            try {
                val parameters = ParametersBuilder()
                if (apiKeyName.isNotEmpty() && apiKeyValue.isNotEmpty()) {
                    parameters.append(apiKeyName, apiKeyValue)
                }
                val url = URLBuilder(
                    parameters = parameters,
                ).takeFrom(url).build()

                val httpClient = HttpClient {
                    install(HttpTimeout) {
                        requestTimeoutMillis = this@ServerInfoApi.requestTimeoutMillis
                        socketTimeoutMillis = this@ServerInfoApi.socketTimeoutMillis
                    }
                }
                if (debug) {
                    println(url)
                }
                val json = httpClient.get<String>(url)
                httpClient.close()
                val format = Json { ignoreUnknownKeys = true }
                format.decodeFromString(ServerInfo.serializer(), json).also(success)
            } catch (ex: Exception) {
                failure(ex)
            }
        }
    }
}
