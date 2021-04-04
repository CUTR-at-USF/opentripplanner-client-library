package com.example.myapplication

import androidx.test.ext.junit.runners.AndroidJUnit4
import edu.usf.cutr.otp.bike_rental.api.BikeRentalApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.coroutines.CoroutineContext

@RunWith(AndroidJUnit4::class)
class BikeRentalApiInstrumentedTest: CoroutineScope {
    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = job + Main

    @Test
    fun callBikeRentalApi() {
        job = Job()

        val bikeRentalApi = BikeRentalApi("https://raw.githubusercontent.com/CUTR-at-USF/opentripplanner-client-library/tests/app/src/androidTest/test_resources/bike_rental.json")
        bikeRentalApi.getBikeRental(
            success = { launch (Main) {

                val s0 = it.stations?.get(0)
                assertEquals("07ed36f0149a00dcd9a91f4d225bc4bb", s0?.id)
                assertEquals("07ed36f0149a00dcd9a91f4d225bc4bb", s0?.name)
                assertEquals( -87.64600616666667, s0?.x)
                assertEquals(41.82783533333333, s0?.y)
                assertEquals(1, s0?.bikesAvailable)
                assertEquals(0, s0?.spacesAvailable)
                assertEquals(false, s0?.allowDropoff)
                assertEquals(true, s0?.isFloatingBike)
                assertEquals(false, s0?.isCarStation)
                assertEquals("GBFS", s0?.networks?.get(0))
                assertEquals(true, s0?.realTimeData)

                val s1 = it.stations?.get(1)
                assertEquals("dada93746259d171611c91134ce06f70", s1?.id)
                assertEquals("dada93746259d171611c91134ce06f70", s1?.name)
                assertEquals( -87.6340975, s1?.x)
                assertEquals(41.84555266666667, s1?.y)
                assertEquals(1, s1?.bikesAvailable)
                assertEquals(0, s1?.spacesAvailable)
                assertEquals(false, s1?.allowDropoff)
                assertEquals(true, s1?.isFloatingBike)
                assertEquals(false, s1?.isCarStation)
                assertEquals("GBFS", s1?.networks?.get(0))
                assertEquals(true, s1?.realTimeData)
            }
        },
            failure = ::handleError
        )
    }

    private fun handleError(ex: Throwable?) {
        assertEquals(0, 1)
    }
}