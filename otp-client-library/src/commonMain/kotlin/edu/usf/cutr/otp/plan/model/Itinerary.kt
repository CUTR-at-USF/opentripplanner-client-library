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
data class Itinerary (
    val duration: Int? = null,
    val startTime: Long? = null,
    val endTime: Long? = null,
    val walkTime: Int? = null,
    val transitTime: Int? = null,
    val waitingTime: Int? = null,
    val walkDistance: Double? = null,
    val walkLimitExceeded: Boolean? = null,
    val generalizedCost: Int? = null,
    val elevationLost: Double? = null,
    val elevationGained: Double? = null,
    val transfers: Int? = null,
    val fare: Fare? = null,
    val legs: List<Leg>? = null,
    val tooSloped: Boolean? = null,
    val arrivedAtDestinationWithRentedBicycle: Boolean? = null,
    val additionalProperties: MutableMap<String,@Polymorphic Any> = HashMap()
)