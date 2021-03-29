package edu.usf.cutr.otp.plan.api

import edu.usf.cutr.otp.ApplicationDispatcher
import edu.usf.cutr.otp.plan.model.Planner
import edu.usf.cutr.otp.plan.model.RequestParameters
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

class PlannerApi(private val host: String,
                 private val port: Int,
                 private val requestParameters: RequestParameters) {

    //private val testUrl = "http://10.0.2.2:8080/otp/routers/default/plan?fromPlace=41.84712%2c-87.64678&toPlace=41.84584%2c-87.65214"

    fun getPlan(
        success: (Planner) -> Unit, failure: (Throwable?) -> Unit) {

        GlobalScope.launch(ApplicationDispatcher) {
            try {
                val parameters = buildParameters(requestParameters)
                val url = URLBuilder(
                    protocol = URLProtocol.HTTP,
                    host = host,
                    port = port,
                    parameters = parameters,
                ).path("otp", "routers", "default", "plan").buildString()
                val json = HttpClient().get<String>(url)

                Json.decodeFromString(Planner.serializer(), json).also(success)
            } catch (ex: Exception) {
                failure(ex)
            }
        }
    }

    private fun buildParameters(requestParameters: RequestParameters): ParametersBuilder {
        val parameters = ParametersBuilder()
        if (requestParameters.fromPlace != null) {
            parameters.append("fromPlace", requestParameters.fromPlace.toString())
        }
        if (requestParameters.toPlace != null) {
            parameters.append("toPlace", requestParameters.toPlace.toString())
        }
        if (requestParameters.arriveBy != null) {
            parameters.append("arriveBy", requestParameters.arriveBy.toString())
        }
        if (requestParameters.alightSlack != null) {
            parameters.append("alightSlack", requestParameters.alightSlack.toString())
        }
        if (requestParameters.traverseModes != null) {
            parameters.append("traverseModes", requestParameters.traverseModes.toString())
        }
        if (requestParameters.optimizeType != null) {
            parameters.append("optimizeType", requestParameters.optimizeType.toString())
        }
        if (requestParameters.intermediatePlaces != null) {
            parameters.append("intermediatePlaces", requestParameters.intermediatePlaces.toString())
        }
        if (requestParameters.maxWalkDistance != null) {
            parameters.append("maxWalkDistance", requestParameters.maxWalkDistance.toString())
        }
        if (requestParameters.triangleTimeFactor != null) {
            parameters.append("triangleTimeFactor", requestParameters.triangleTimeFactor.toString())
        }
        if (requestParameters.triangleSlopeFactor != null) {
            parameters.append("triangleSlopeFactor", requestParameters.triangleSlopeFactor.toString())
        }
        if (requestParameters.triangleSafetyFactor != null) {
            parameters.append("triangleSafetyFactor", requestParameters.triangleSafetyFactor.toString())
        }
        if (requestParameters.wheelchair != null) {
            parameters.append("wheelchair", requestParameters.wheelchair.toString())
        }
        if (requestParameters.date != null) {
            parameters.append("date", requestParameters.date.toString())
        }
        if (requestParameters.numItineraries != null) {
            parameters.append("numItineraries", requestParameters.numItineraries.toString())
        }
        if (requestParameters.preferredRoutes != null) {
            parameters.append("preferredRoutes", requestParameters.preferredRoutes.toString())
        }
        if (requestParameters.unpreferredRoutes != null) {
            parameters.append("unpreferredRoutes", requestParameters.unpreferredRoutes.toString())
        }
        return parameters
    }

}