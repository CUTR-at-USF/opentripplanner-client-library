package edu.usf.cutr.otp.planner.api

import edu.usf.cutr.otp.ApplicationDispatcher
import edu.usf.cutr.otp.planner.model.Planner
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json

class PlannerApi() {
    private val testUrl = "http://10.0.2.2:8080/otp/routers/default/plan?fromPlace=41.84712%2c-87.64678&toPlace=41.84584%2c-87.65214"

    fun getPlannerResource(
        success: (Planner) -> Unit, failure: (Throwable?) -> Unit) {

        GlobalScope.launch(ApplicationDispatcher) {
            try {
                val url = testUrl

                val json = HttpClient().get<String>(url)

                Json.decodeFromString(Planner.serializer(), json).also(success)
            } catch (ex: Exception) {
                failure(ex)
            }
        }
        }

}