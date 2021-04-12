package edu.usf.cutr.otp.demo.chicago

import edu.usf.cutr.otp.plan.api.PlanApi
import edu.usf.cutr.otp.plan.model.Planner
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Test
import java.util.concurrent.CountDownLatch

class PlanApiUnitTest {

    @ExperimentalCoroutinesApi
    @Test
    fun testPlanApi() {
        val planApi =
            PlanApi("https://raw.githubusercontent.com/CUTR-at-USF/opentripplanner-client-library/tests/library/src/jvmTest/kotlin/edu/usf/cutr/otp/resources/chicago/plan.json")
        var planner = Planner()
        val latch = CountDownLatch(1)

        planApi.getPlan(
            success = {
                latch.countDown()
                planner = it
            },
            failure = {
                latch.countDown()
            }
        )

        try {
            latch.await()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        Assert.assertEquals("false", planner.requestParameters?.arriveBy)
        Assert.assertEquals("41.84712,-87.64678", planner.requestParameters?.fromPlace)
        Assert.assertEquals("QUICK", planner.requestParameters?.optimizeType.toString())
        Assert.assertEquals(0, planner.requestParameters?.alightSlack)
        Assert.assertEquals("WALK", planner.requestParameters?.traverseModes.toString())
        Assert.assertEquals("41.84584,-87.65214", planner.requestParameters?.toPlace)
        Assert.assertEquals(
            1.7976931348623157E308,
            planner.requestParameters?.maxWalkDistance
        )
        Assert.assertEquals(3, planner.requestParameters?.numItineraries)

        // plan model
        Assert.assertEquals(1617479387000, planner.plan?.date)

        Assert.assertEquals("Origin", planner.plan?.from?.name)
        Assert.assertEquals(-87.64678, planner.plan?.from?.lon)
        Assert.assertEquals(41.84712, planner.plan?.from?.lat)
        Assert.assertEquals("NORMAL", planner.plan?.from?.vertexType)

        Assert.assertEquals("Destination", planner.plan?.to?.name)
        Assert.assertEquals(-87.65214, planner.plan?.to?.lon)
        Assert.assertEquals(41.84584, planner.plan?.to?.lat)
        Assert.assertEquals("NORMAL", planner.plan?.to?.vertexType)

        Assert.assertEquals(328, planner.plan?.itineraries?.get(0)?.duration)
        Assert.assertEquals(1617479387000, planner.plan?.itineraries?.get(0)?.startTime)
        Assert.assertEquals(1617479715000, planner.plan?.itineraries?.get(0)?.endTime)
        Assert.assertEquals(328, planner.plan?.itineraries?.get(0)?.walkTime)
        Assert.assertEquals(0, planner.plan?.itineraries?.get(0)?.transitTime)
        Assert.assertEquals(0, planner.plan?.itineraries?.get(0)?.waitingTime)
        Assert.assertEquals(
            618.8409999999999,
            planner.plan?.itineraries?.get(0)?.walkDistance
        )
        Assert.assertEquals(false, planner.plan?.itineraries?.get(0)?.walkLimitExceeded)
        Assert.assertEquals(0.0, planner.plan?.itineraries?.get(0)?.elevationLost)
        Assert.assertEquals(0.0, planner.plan?.itineraries?.get(0)?.elevationGained)
        Assert.assertEquals(0, planner.plan?.itineraries?.get(0)?.transfers)
        Assert.assertEquals(false, planner.plan?.itineraries?.get(0)?.tooSloped)

        val legs = planner.plan?.itineraries?.get(0)?.legs?.get(0)
        Assert.assertEquals(1617479387000, legs?.startTime)
        Assert.assertEquals(1617479715000, legs?.endTime)
        Assert.assertEquals(0, legs?.departureDelay)
        Assert.assertEquals(0, legs?.arrivalDelay)
        Assert.assertEquals(false, legs?.realTime)
        Assert.assertEquals(618.8409999999999, legs?.distance)
        Assert.assertEquals("WALK", legs?.mode)
        Assert.assertEquals("", legs?.route)
        Assert.assertEquals(-18000000, legs?.agencyTimeZoneOffset)
        Assert.assertEquals(false, legs?.interlineWithPreviousLeg)
        Assert.assertEquals(false, legs?.rentedBike)
        Assert.assertEquals(328.0, legs?.duration)



        Assert.assertEquals("Origin", legs?.from?.name)
        Assert.assertEquals(-87.64678, legs?.from?.lon)
        Assert.assertEquals(41.84712, legs?.from?.lat)
        Assert.assertEquals(1617479387000, legs?.from?.departure)
        Assert.assertEquals("NORMAL", legs?.from?.vertexType)

        Assert.assertEquals("Destination", legs?.to?.name)
        Assert.assertEquals(-87.65214, legs?.to?.lon)
        Assert.assertEquals(41.84584, legs?.to?.lat)
        Assert.assertEquals(1617479715000, legs?.to?.arrival)
        Assert.assertEquals("NORMAL", legs?.to?.vertexType)

        Assert.assertEquals(
            "ogl~Fnm}uOQ@Tl@^bAl@|ABHB`@l@vARh@FNx@zBN`@Vn@|A`EP`@cEfDV|@Jd@Df@FN",
            legs?.legGeometry?.points
        )
        Assert.assertEquals(20, legs?.legGeometry?.length)

        Assert.assertEquals(9.928, legs?.steps?.get(0)?.distance)
        Assert.assertEquals("DEPART", legs?.steps?.get(0)?.relativeDirection)
        Assert.assertEquals("South Halsted Street", legs?.steps?.get(0)?.streetName)
        Assert.assertEquals("NORTH", legs?.steps?.get(0)?.absoluteDirection)
        Assert.assertEquals(false, legs?.steps?.get(0)?.stayOn)
        Assert.assertEquals(false, legs?.steps?.get(0)?.area)
        Assert.assertEquals(false, legs?.steps?.get(0)?.bogusName)
        Assert.assertEquals(-87.64647953852368, legs?.steps?.get(0)?.lon)
        Assert.assertEquals(41.847126653593136, legs?.steps?.get(0)?.lat)
        Assert.assertEquals("", legs?.steps?.get(0)?.elevation)

        // error model
        Assert.assertEquals(0, planner.error?.id)
        Assert.assertEquals("NO_TRANSIT_TIMES", planner.error?.message)

        // debug output model
        Assert.assertEquals(0, planner.debugOutput?.precalculationTime)
        Assert.assertEquals(111, planner.debugOutput?.directStreetRouterTime)
        Assert.assertEquals(0, planner.debugOutput?.transitRouterTime)
        Assert.assertEquals(2, planner.debugOutput?.filteringTime)
        Assert.assertEquals(1, planner.debugOutput?.renderingTime)
        Assert.assertEquals(114, planner.debugOutput?.totalTime)

        Assert.assertEquals(
            0,
            planner.debugOutput?.transitRouterTimes?.tripPatternFilterTime
        )
        Assert.assertEquals(0, planner.debugOutput?.transitRouterTimes?.accessEgressTime)
        Assert.assertEquals(0, planner.debugOutput?.transitRouterTimes?.raptorSearchTime)
        Assert.assertEquals(
            0,
            planner.debugOutput?.transitRouterTimes?.itineraryCreationTime
        )

        // elevation meta data model
        Assert.assertEquals(
            -33.87059709255653,
            planner.elevationMetadata?.ellipsoidToGeoidDifference
        )
        Assert.assertEquals(false, planner.elevationMetadata?.geoidElevation)

    }
}


