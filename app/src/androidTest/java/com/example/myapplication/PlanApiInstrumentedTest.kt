package com.example.myapplication

import androidx.test.ext.junit.runners.AndroidJUnit4
import edu.usf.cutr.otp.plan.api.PlanApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.coroutines.CoroutineContext


@RunWith(AndroidJUnit4::class)
class PlanApiInstrumentedTest : CoroutineScope {

    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = job + Main

    @Test
    fun callPlanApi() {
        job = Job()
        val planApi = PlanApi("https://raw.githubusercontent.com/CUTR-at-USF/opentripplanner-client-library/tests/app/src/androidTest/test_resources/plan.json")
        planApi.getPlan(
            success = {launch (Main) {

                // request parameters model
                assertEquals("false", it.requestParameters?.arriveBy)
                assertEquals("41.84712,-87.64678", it.requestParameters?.fromPlace)
                assertEquals("QUICK", it.requestParameters?.optimizeType.toString())
                assertEquals(0, it.requestParameters?.alightSlack)
                assertEquals("WALK", it.requestParameters?.traverseModes.toString())
                assertEquals("41.84584,-87.65214", it.requestParameters?.toPlace)
                assertEquals(1.7976931348623157E308, it.requestParameters?.maxWalkDistance)
                assertEquals(3, it.requestParameters?.numItineraries)

                // plan model
                assertEquals(1617479387000, it.plan?.date)

                assertEquals("Origin", it.plan?.from?.name)
                assertEquals(-87.64678, it.plan?.from?.lon)
                assertEquals(41.84712, it.plan?.from?.lat)
                assertEquals("NORMAL", it.plan?.from?.vertexType)

                assertEquals("Destination", it.plan?.to?.name)
                assertEquals(-87.65214, it.plan?.to?.lon)
                assertEquals(41.84584, it.plan?.to?.lat)
                assertEquals("NORMAL", it.plan?.to?.vertexType)

                assertEquals(328, it.plan?.itineraries?.get(0)?.duration)
                assertEquals(1617479387000, it.plan?.itineraries?.get(0)?.startTime)
                assertEquals(1617479715000, it.plan?.itineraries?.get(0)?.endTime)
                assertEquals(328, it.plan?.itineraries?.get(0)?.walkTime)
                assertEquals(0, it.plan?.itineraries?.get(0)?.transitTime)
                assertEquals(0, it.plan?.itineraries?.get(0)?.waitingTime)
                assertEquals(618.8409999999999, it.plan?.itineraries?.get(0)?.walkDistance)
                assertEquals(false, it.plan?.itineraries?.get(0)?.walkLimitExceeded)
                assertEquals(0.0, it.plan?.itineraries?.get(0)?.elevationLost)
                assertEquals(0.0, it.plan?.itineraries?.get(0)?.elevationGained)
                assertEquals(0, it.plan?.itineraries?.get(0)?.transfers)
                assertEquals(false, it.plan?.itineraries?.get(0)?.tooSloped)

                val legs = it.plan?.itineraries?.get(0)?.legs?.get(0)
                assertEquals(1617479387000, legs?.startTime)
                assertEquals(1617479715000, legs?.endTime)
                assertEquals(0, legs?.departureDelay)
                assertEquals(0, legs?.arrivalDelay)
                assertEquals(false, legs?.realTime)
                assertEquals(618.8409999999999, legs?.distance)
                assertEquals("WALK", legs?.mode)
                assertEquals("", legs?.route)
                assertEquals(-18000000, legs?.agencyTimeZoneOffset)
                assertEquals(false, legs?.interlineWithPreviousLeg)
                assertEquals(false, legs?.rentedBike)
                assertEquals(328.0, legs?.duration)



                assertEquals("Origin", legs?.from?.name)
                assertEquals(-87.64678, legs?.from?.lon)
                assertEquals(41.84712, legs?.from?.lat)
                assertEquals(1617479387000, legs?.from?.departure)
                assertEquals("NORMAL", legs?.from?.vertexType)

                assertEquals("Destination", legs?.to?.name)
                assertEquals(-87.65214, legs?.to?.lon)
                assertEquals(41.84584, legs?.to?.lat)
                assertEquals(1617479715000, legs?.to?.arrival)
                assertEquals("NORMAL", legs?.to?.vertexType)

                assertEquals("ogl~Fnm}uOQ@Tl@^bAl@|ABHB`@l@vARh@FNx@zBN`@Vn@|A`EP`@cEfDV|@Jd@Df@FN", legs?.legGeometry?.points)
                assertEquals(20, legs?.legGeometry?.length)

                assertEquals(9.928, legs?.steps?.get(0)?.distance)
                assertEquals("DEPART", legs?.steps?.get(0)?.relativeDirection)
                assertEquals("South Halsted Street", legs?.steps?.get(0)?.streetName)
                assertEquals("NORTH", legs?.steps?.get(0)?.absoluteDirection)
                assertEquals(false, legs?.steps?.get(0)?.stayOn)
                assertEquals(false, legs?.steps?.get(0)?.area)
                assertEquals(false, legs?.steps?.get(0)?.bogusName)
                assertEquals(-87.64647953852368, legs?.steps?.get(0)?.lon)
                assertEquals(41.847126653593136, legs?.steps?.get(0)?.lat)
                assertEquals("", legs?.steps?.get(0)?.elevation)

                // error model
                assertEquals(0, it.error?.id)
                assertEquals("NO_TRANSIT_TIMES", it.error?.message)

                // debug output model
                assertEquals(0, it.debugOutput?.precalculationTime)
                assertEquals(111, it.debugOutput?.directStreetRouterTime)
                assertEquals(0, it.debugOutput?.transitRouterTime)
                assertEquals(2, it.debugOutput?.filteringTime)
                assertEquals(1, it.debugOutput?.renderingTime)
                assertEquals(114, it.debugOutput?.totalTime)

                assertEquals(0, it.debugOutput?.transitRouterTimes?.tripPatternFilterTime)
                assertEquals(0, it.debugOutput?.transitRouterTimes?.accessEgressTime)
                assertEquals(0, it.debugOutput?.transitRouterTimes?.raptorSearchTime)
                assertEquals(0, it.debugOutput?.transitRouterTimes?.itineraryCreationTime)

                // elevation meta data model
                assertEquals(-33.87059709255653, it.elevationMetadata?.ellipsoidToGeoidDifference)
                assertEquals(false, it.elevationMetadata?.geoidElevation)
            }
        },
            failure = ::handleError
      )
    }

    private fun handleError(ex: Throwable?) {
        assertEquals(0, 1)
    }

}