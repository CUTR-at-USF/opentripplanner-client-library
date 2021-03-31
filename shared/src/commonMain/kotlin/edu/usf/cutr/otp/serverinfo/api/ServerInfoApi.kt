package edu.usf.cutr.otp.serverinfo.api

import edu.usf.cutr.otp.ApplicationDispatcher
import edu.usf.cutr.otp.serverinfo.model.ServerInfo
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class ServerInfoApi (private val url: String) {

    fun getServerInfo(
        success: (ServerInfo) -> Unit, failure: (Throwable?) -> Unit) {

        GlobalScope.launch(ApplicationDispatcher) {
            try {
                val url = URLBuilder(
                ).takeFrom(url).build()

                val json = HttpClient().get<String>(url)
                Json.decodeFromString(ServerInfo.serializer(), json).also(success)


            } catch (ex: Exception) {
                failure(ex)
            }
        }
    }
}