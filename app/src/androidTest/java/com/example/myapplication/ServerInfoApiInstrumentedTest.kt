package com.example.myapplication

import androidx.test.ext.junit.runners.AndroidJUnit4
import edu.usf.cutr.otp.serverinfo.api.ServerInfoApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.coroutines.CoroutineContext

@RunWith(AndroidJUnit4::class)
class ServerInfoApiInstrumentedTest: CoroutineScope {
    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = job + Main

    @Test
    fun callServerInfoApi() {
        job = Job()

        val serverInfoApi =
            ServerInfoApi("https://raw.githubusercontent.com/CUTR-at-USF/opentripplanner-client-library/tests/app/src/androidTest/test_resources/server_info.json")
        serverInfoApi.getServerInfo(
            success = {
                launch(Main) {
                    assertEquals("2.0.0", it.serverVersion?.version)
                    assertEquals(2, it.serverVersion?.major)
                    assertEquals(0, it.serverVersion?.minor)
                    assertEquals(0, it.serverVersion?.incremental)
                    assertEquals("", it.serverVersion?.qualifier)
                    assertEquals("a64576040edff06e84033f2e78991a5ba56b2f78", it.serverVersion?.commit)
                    assertEquals("v2.0.0", it.serverVersion?.branch)
                    assertEquals("v2.0.0", it.serverVersion?.describe)
                    assertEquals("2020-11-27T12:58:18+0000", it.serverVersion?.commitTime)
                    assertEquals("2020-11-27T13:02:08+0000", it.serverVersion?.buildTime)
                    assertEquals(false, it.serverVersion?.dirty)
                    assertEquals("2000000", it.serverVersion?.uid)
                    assertEquals("version: 2.0.0, commit: a64576040edff06e84033f2e78991a5ba56b2f78, branch: v2.0.0\"", it.serverVersion?.longVersionString)
                    assertEquals("OpenTripPlanner 2.0.0 a64576040edff06e84033f2e78991a5ba56b2f78", it.serverVersion?.shortVersionString)

                    assertEquals("unknown", it.cpuName)
                    assertEquals(0, it.nCores)
                }
            },
            failure = ::handleError
        )
    }

    private fun handleError(ex: Throwable?) {
        assertEquals(0, 1)
    }

}