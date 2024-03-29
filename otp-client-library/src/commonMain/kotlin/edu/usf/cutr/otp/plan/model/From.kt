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
data class From
    (
    val name: String? = null,
    val stopId: String? = null,
    val stopCode: String? = null,
    val platformCode: String? = null,
    val stopIndex: Int? = null,
    val stopSequence: Int? = null,
    val lon: Double? = null,
    val lat: Double? = null,
    val vertexType: String? = null,
    val additionalProperties: MutableMap<String, @Polymorphic Any> = HashMap()
)