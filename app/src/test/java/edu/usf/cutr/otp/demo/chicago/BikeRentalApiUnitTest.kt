package edu.usf.cutr.otp.demo.chicago

import edu.usf.cutr.otp.bike_rental.api.BikeRentalApi
import edu.usf.cutr.otp.bike_rental.model.Station
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Test
import java.util.concurrent.CountDownLatch

class BikeRentalApiUnitTest {

    @ExperimentalCoroutinesApi
    @Test
    fun testBikeRentalApi() {
        val bikeRentalApi =
            BikeRentalApi("https://raw.githubusercontent.com/CUTR-at-USF/opentripplanner-client-library/tests/library/src/jvmTest/kotlin/edu/usf/cutr/otp/resources/chicago/bike_rental.json")
        var s0: Station? = Station()
        var s1: Station? = Station()
        val latch = CountDownLatch(1)
        bikeRentalApi.getBikeRental(
            success = {
                latch.countDown()
                s0 = it.stations?.get(0)
                s1 = it.stations?.get(1)
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

        Assert.assertEquals("07ed36f0149a00dcd9a91f4d225bc4bb", s0?.id)
        Assert.assertEquals("07ed36f0149a00dcd9a91f4d225bc4bb", s0?.name)
        Assert.assertEquals(-87.64600616666667, s0?.x)
        Assert.assertEquals(41.82783533333333, s0?.y)
        Assert.assertEquals(1, s0?.bikesAvailable)
        Assert.assertEquals(0, s0?.spacesAvailable)
        Assert.assertEquals(false, s0?.allowDropoff)
        Assert.assertEquals(true, s0?.isFloatingBike)
        Assert.assertEquals(false, s0?.isCarStation)
        Assert.assertEquals("GBFS", s0?.networks?.get(0))
        Assert.assertEquals(true, s0?.realTimeData)

        Assert.assertEquals("dada93746259d171611c91134ce06f70", s1?.id)
        Assert.assertEquals("dada93746259d171611c91134ce06f70", s1?.name)
        Assert.assertEquals(-87.6340975, s1?.x)
        Assert.assertEquals(41.84555266666667, s1?.y)
        Assert.assertEquals(1, s1?.bikesAvailable)
        Assert.assertEquals(0, s1?.spacesAvailable)
        Assert.assertEquals(false, s1?.allowDropoff)
        Assert.assertEquals(true, s1?.isFloatingBike)
        Assert.assertEquals(false, s1?.isCarStation)
        Assert.assertEquals("GBFS", s1?.networks?.get(0))
        Assert.assertEquals(true, s1?.realTimeData)

    }
}