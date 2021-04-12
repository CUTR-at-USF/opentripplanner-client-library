package edu.usf.cutr.otp.demo.tampa

import edu.usf.cutr.otp.plan.api.PlanApi
import edu.usf.cutr.otp.plan.model.Planner
import edu.usf.cutr.otp.plan.model.RequestParameters
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.concurrent.CountDownLatch

class PlanApiUnitTest {

    @ExperimentalCoroutinesApi
    @Test
    fun testPlanApi() {
        val planApi =
            PlanApi(
                "https://raw.githubusercontent.com/CUTR-at-USF/opentripplanner-client-library/tests/library/src/jvmTest/kotlin/edu/usf/cutr/otp/resources/tampa/plan.json",
                RequestParameters()
            )
        var planner = Planner()
        val latch = CountDownLatch(1)

        planApi.getPlan(
            success = {
                planner = it
                latch.countDown()
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

        assertEquals("28.05198,-82.43042", planner.requestParameters?.fromPlace)
        assertEquals("27.99380,-82.48260", planner.requestParameters?.toPlace)

        assertEquals(1618170988000, planner.plan?.date)
        assertEquals("22nd St @ 115th Av", planner.plan?.from?.name)
        assertEquals("1:4886", planner.plan?.from?.stopId)
        assertEquals("4886", planner.plan?.from?.stopCode)
        assertEquals("4886", planner.plan?.from?.platformCode)
        assertEquals(-82.434516, planner.plan?.from?.lon)
        assertEquals(28.052049, planner.plan?.from?.lat)
        assertEquals(48, planner.plan?.from?.stopIndex)
        assertEquals("TRANSIT", planner.plan?.from?.vertexType)

        assertEquals("Armenia Av @ Crest Av", planner.plan?.to?.name)
        assertEquals("1:5246", planner.plan?.from?.stopId)
        assertEquals("5246", planner.plan?.from?.stopCode)
        assertEquals("5246", planner.plan?.from?.platformCode)
        assertEquals(-82.484411, planner.plan?.to?.lon)
        assertEquals(27.993709, planner.plan?.to?.lat)
        assertEquals(24, planner.plan?.from?.stopIndex)
        assertEquals("TRANSIT", planner.plan?.to?.vertexType)

        assertEquals(6569, planner.plan?.itineraries?.get(0)?.duration)
        assertEquals(1618173471000, planner.plan?.itineraries?.get(0)?.startTime)
        assertEquals(1618180040000, planner.plan?.itineraries?.get(0)?.endTime)
        assertEquals(0, planner.plan?.itineraries?.get(0)?.walkTime)
        assertEquals(4083, planner.plan?.itineraries?.get(0)?.transitTime)
        assertEquals(2486, planner.plan?.itineraries?.get(0)?.waitingTime)
        assertEquals(0.0, planner.plan?.itineraries?.get(0)?.walkDistance)
        assertEquals(false, planner.plan?.itineraries?.get(0)?.walkLimitExceeded)
        assertEquals(0.0, planner.plan?.itineraries?.get(0)?.elevationLost)
        assertEquals(0.0, planner.plan?.itineraries?.get(0)?.elevationGained)
        assertEquals(3, planner.plan?.itineraries?.get(0)?.transfers)
        assertEquals(false, planner.plan?.itineraries?.get(0)?.tooSloped)

        assertEquals(
            2,
            planner.plan?.itineraries?.get(0)?.fare?.fare?.regular?.currency?.defaultFractionDigits
        )
        assertEquals(
            "USD",
            planner.plan?.itineraries?.get(0)?.fare?.fare?.regular?.currency?.currencyCode
        )
        assertEquals(
            "USD",
            planner.plan?.itineraries?.get(0)?.fare?.fare?.regular?.currency?.currency
        )
        assertEquals("$", planner.plan?.itineraries?.get(0)?.fare?.fare?.regular?.currency?.symbol)
        assertEquals(200, planner.plan?.itineraries?.get(0)?.fare?.fare?.regular?.cents)

        assertEquals(
            "1:1",
            planner.plan?.itineraries?.get(0)?.fare?.details?.regular?.get(0)?.fareId
        )
        assertEquals(
            2,
            planner.plan?.itineraries?.get(0)?.fare?.details?.regular?.get(0)?.price?.currency?.defaultFractionDigits
        )
        assertEquals(
            "USD",
            planner.plan?.itineraries?.get(0)?.fare?.details?.regular?.get(0)?.price?.currency?.currencyCode
        )
        assertEquals(
            "USD",
            planner.plan?.itineraries?.get(0)?.fare?.details?.regular?.get(0)?.price?.currency?.currency
        )
        assertEquals(
            "$",
            planner.plan?.itineraries?.get(0)?.fare?.details?.regular?.get(0)?.price?.currency?.symbol
        )
        assertEquals(
            "1:1",
            planner.plan?.itineraries?.get(0)?.fare?.details?.regular?.get(0)?.routes?.get(0)
        )

        val legs = planner.plan?.itineraries?.get(0)?.legs?.get(0)
        assertEquals(1618173471000, legs?.startTime)
        assertEquals(1618173900000, legs?.endTime)
        assertEquals(0, legs?.departureDelay)
        assertEquals(0, legs?.arrivalDelay)
        assertEquals(false, legs?.realTime)
        assertEquals(2719.7720919457793, legs?.distance)
        assertEquals("BUS", legs?.mode)
        assertEquals("22nd Street", legs?.route)
        assertEquals(true, legs?.pathway)
        assertEquals(true, legs?.transitLeg)
        assertEquals("22nd Street", legs?.route)
        assertEquals("Hillsborough Area Regional Transit", legs?.agencyName)
        assertEquals("http://www.gohart.org", legs?.agencyUrl)
        assertEquals("09346D", legs?.routeColor)
        assertEquals("1:12", legs?.routeId)
        assertEquals("FFFFFF", legs?.routeTextColor)
        assertEquals("North to University Area", legs?.headsign)
        assertEquals("1:1", legs?.agencyId)
        assertEquals("1:1673432", legs?.tripId)
        assertEquals("2021-04-11", legs?.serviceDate)

        assertEquals("22nd St @ 115th Av", legs?.from?.name)
        assertEquals("1:4886", legs?.from?.stopId)
        assertEquals("4886", legs?.from?.stopCode)
        assertEquals("4886", legs?.from?.platformCode)
        assertEquals(-82.434516, legs?.from?.lon)
        assertEquals(28.052049, legs?.from?.lat)
        assertEquals(1618173471000, legs?.from?.departure)
        assertEquals(48, legs?.from?.stopIndex)
        assertEquals("TRANSIT", legs?.from?.vertexType)

        assertEquals("University Area Transit Center", legs?.to?.name)
        assertEquals("1:6497", legs?.from?.stopId)
        assertEquals("6497", legs?.from?.stopCode)
        assertEquals("6497", legs?.from?.platformCode)
        assertEquals(55, legs?.to?.stopIndex)
        assertEquals(-82.429922, legs?.to?.lon)
        assertEquals(28.066021, legs?.to?.lat)
        assertEquals(1618173900000, legs?.to?.arrival)
        assertEquals(1618174800000, legs?.to?.departure)
        assertEquals("TRANSIT", legs?.to?.vertexType)

        assertEquals(
            "g|ejD|ncvNs@?qGB??gC@o@@iC@?z@?V?P?TwB?[d@]d@EH?Z?j@?R???N?d@EHKP?J?d@?d@?f@?f@?d@?b@?h@?f@?b@?`@g@?a@?c@?e@?a@?c@?c@?e@?c@GUQCIEKAM?mA?k@?e@Ee@Oe@Qe@Si@Qe@Wq@M]Ai@?[oC?u@?MAKECE?KAQ?iD_B?iDAmA???g@?{DAqA?}@???E?mC?aFA??k@A?iE?oE?G???eA?uB?{E?gEcB?",
            legs?.legGeometry?.points
        )
        assertEquals(90, legs?.legGeometry?.length)
        assertEquals("12", legs?.routeShortName)
        assertEquals("22nd Street", legs?.routeLongName)
        assertEquals(0, legs?.agencyTimeZoneOffset)
        assertEquals(429.0, legs?.duration)

        assertEquals(4800, planner.metadata?.searchWindowUsed)
        assertEquals(1618175788000, planner.metadata?.nextDateTime)
        assertEquals(1618166188000, planner.metadata?.prevDateTime)

        assertEquals(0, planner.debugOutput?.precalculationTime)
        assertEquals(2, planner.debugOutput?.directStreetRouterTime)
        assertEquals(49, planner.debugOutput?.transitRouterTime)
        assertEquals(1, planner.debugOutput?.filteringTime)
        assertEquals(0, planner.debugOutput?.renderingTime)
        assertEquals(52, planner.debugOutput?.totalTime)

        assertEquals(
            3,
            planner.debugOutput?.transitRouterTimes?.tripPatternFilterTime
        )
        assertEquals(1, planner.debugOutput?.transitRouterTimes?.accessEgressTime)
        assertEquals(12, planner.debugOutput?.transitRouterTimes?.raptorSearchTime)
        assertEquals(
            33,
            planner.debugOutput?.transitRouterTimes?.itineraryCreationTime
        )

        assertEquals(
            -26.105507516167194,
            planner.elevationMetadata?.ellipsoidToGeoidDifference
        )
        assertEquals(false, planner.elevationMetadata?.geoidElevation)
    }
}