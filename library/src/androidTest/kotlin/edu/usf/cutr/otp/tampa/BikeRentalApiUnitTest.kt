package edu.usf.cutr.otp.tampa

import edu.usf.cutr.otp.bike_rental.api.BikeRentalApi
import edu.usf.cutr.otp.bike_rental.model.Station
import org.junit.Assert
import org.junit.Test
import java.util.concurrent.CountDownLatch

class BikeRentalApiUnitTest {

    @Test
    fun testBikeRentalApi() {
        val bikeRentalApi =
            BikeRentalApi("https://raw.githubusercontent.com/CUTR-at-USF/opentripplanner-client-library/main/library/src/jvmTest/resources/tampa/bike_rental.json")
        var s0: Station? = Station()
        var s1: Station? = Station()
        val latch = CountDownLatch(1)
        bikeRentalApi.getBikeRental(
            success = {
                s0 = it.stations?.get(0)
                s1 = it.stations?.get(1)
                latch.countDown()

            },
            failure = {
                latch.countDown()
            }
        )

        latch.await()

        Assert.assertEquals("a68e1aa9-d5da-44aa-adae-303bb55de171", s0?.id)
        Assert.assertEquals("", s0?.name)
        Assert.assertEquals(-82.4469, s0?.x)
        Assert.assertEquals(27.99436, s0?.y)
        Assert.assertEquals(1, s0?.bikesAvailable)
        Assert.assertEquals(0, s0?.spacesAvailable)
        Assert.assertEquals(false, s0?.allowDropoff)
        Assert.assertEquals(true, s0?.isFloatingBike)
        Assert.assertEquals(false, s0?.isCarStation)
        Assert.assertEquals("GBFS", s0?.networks?.get(0))
        Assert.assertEquals(true, s0?.realTimeData)

        Assert.assertEquals("b5c57037-6bb4-4e03-9754-4bdd24515e3a", s1?.id)
        Assert.assertEquals("", s1?.name)
        Assert.assertEquals(-82.44429, s1?.x)
        Assert.assertEquals(28.00068, s1?.y)
        Assert.assertEquals(1, s1?.bikesAvailable)
        Assert.assertEquals(0, s1?.spacesAvailable)
        Assert.assertEquals(false, s1?.allowDropoff)
        Assert.assertEquals(true, s1?.isFloatingBike)
        Assert.assertEquals(false, s1?.isCarStation)
        Assert.assertEquals("GBFS", s1?.networks?.get(0))
        Assert.assertEquals(true, s1?.realTimeData)
    }
}