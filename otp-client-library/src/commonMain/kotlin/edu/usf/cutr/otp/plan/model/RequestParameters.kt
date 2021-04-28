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

import edu.usf.cutr.otp.plan.model.core.OptimizeType
import edu.usf.cutr.otp.plan.model.core.TraverseModes
import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable

@Serializable
data class RequestParameters
    (
    val fromPlace: String? = null,
    val toPlace: String? = null,
    val alightSlack: Int? = null,
    val arriveBy: String? = null,
    val traverseModes: TraverseModes? = null,
    val optimizeType: OptimizeType? = OptimizeType.QUICK,
    val intermediatePlaces: List<String>? = null,
    val maxWalkDistance: Double? = Double.MAX_VALUE,
    val triangleTimeFactor: Double? = null,
    val triangleSlopeFactor: Double? = null,
    val triangleSafetyFactor: Double? = null,
    val wheelchair: String? = null,
    val date: String? = null, // supported date format is MM-dd-yyyy. ex: 09-30-2020
    val time: String? = null, // supported time format is h:mma, ex 7:45PM
    val numItineraries: Int? = 3,
    val preferredRoutes: List<String>? = null,
    val unpreferredRoutes: List<String>? = null,
    val additionalProperties: MutableMap<String, @Polymorphic Any> = HashMap()
)
