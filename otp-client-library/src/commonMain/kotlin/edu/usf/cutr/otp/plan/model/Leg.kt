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

package edu.usf.cutr.otp.plan.model

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable

@Serializable
data class Leg(
    val startTime: Long? = null,
    val endTime: Long? = null,
    val departureDelay: Int? = null,
    val arrivalDelay: Int? = null,
    val realTime: Boolean? = null,
    val distance: Double? = null,
    val pathway: Boolean? = null,
    val mode: String? = null,
    val transitLeg: Boolean? = null,
    val route: String? = null,
    val routeColor: String? = null,
    val routeId: String? = null,
    val routeTextColor: String? = null,
    val routeLongName: String? = null,
    val routeShortName: String? = null,
    val tripBlockId: String? = null,
    val headsign: String? = null,
    val tripId: String? = null,
    val intermediateStops: List<String>? = null,
    val serviceDate: String? = null,
    val agencyName: String? = null,
    val agencyUrl: String? = null,
    val agencyId: String? = null,
    val agencyTimeZoneOffset: Int? = null,
    val interlineWithPreviousLeg: Boolean? = null,
    val from: From_? = null,
    val to: To_? = null,
    val legGeometry: LegGeometry? = null,
    val steps: List<Step>? = null,
    val alerts: List<Alert>? = null,
    val rentedBike: Boolean? = null,
    val duration: Double? = null,
    val additionalProperties: MutableMap<String, @Polymorphic Any> = HashMap()
)