package edu.usf.cutr.otp.demo.chicago

import edu.usf.cutr.otp.serverinfo.api.ServerInfoApi
import edu.usf.cutr.otp.serverinfo.model.ServerInfo
import org.junit.Assert
import org.junit.Test
import java.util.concurrent.CountDownLatch

class ServerInfoApiUnitTest {
    @Test
    fun testServerInfoApi() {
        val serverInfoApi =
            ServerInfoApi("https://raw.githubusercontent.com/CUTR-at-USF/opentripplanner-client-library/tests/library/src/jvmTest/kotlin/edu/usf/cutr/otp/resources/chicago/server_info.json")

        var serverInfo = ServerInfo()
        val latch = CountDownLatch(1)

        serverInfoApi.getServerInfo(
            success = {
                serverInfo = it
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

        Assert.assertEquals("2.0.0", serverInfo.serverVersion?.version)
        Assert.assertEquals(2, serverInfo.serverVersion?.major)
        Assert.assertEquals(0, serverInfo.serverVersion?.minor)
        Assert.assertEquals(0, serverInfo.serverVersion?.incremental)
        Assert.assertEquals("", serverInfo.serverVersion?.qualifier)
        Assert.assertEquals(
            "a64576040edff06e84033f2e78991a5ba56b2f78",
            serverInfo.serverVersion?.commit
        )
        Assert.assertEquals("v2.0.0", serverInfo.serverVersion?.branch)
        Assert.assertEquals("v2.0.0", serverInfo.serverVersion?.describe)
        Assert.assertEquals("2020-11-27T12:58:18+0000", serverInfo.serverVersion?.commitTime)
        Assert.assertEquals("2020-11-27T13:02:08+0000", serverInfo.serverVersion?.buildTime)
        Assert.assertEquals(false, serverInfo.serverVersion?.dirty)
        Assert.assertEquals(2000000, serverInfo.serverVersion?.uid)
        Assert.assertEquals(
            "version: 2.0.0, commit: a64576040edff06e84033f2e78991a5ba56b2f78, branch: v2.0.0",
            serverInfo.serverVersion?.longVersionString
        )
        Assert.assertEquals(
            "OpenTripPlanner 2.0.0 a64576040edff06e84033f2e78991a5ba56b2f78",
            serverInfo.serverVersion?.shortVersionString
        )

        Assert.assertEquals("unknown", serverInfo.cpuName)
        Assert.assertEquals(0, serverInfo.nCores)
    }
}