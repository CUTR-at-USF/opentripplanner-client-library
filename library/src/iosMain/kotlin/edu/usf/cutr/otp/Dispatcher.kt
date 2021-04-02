package edu.usf.cutr.otp

import kotlinx.coroutines.CoroutineDispatcher

internal actual val ApplicationDispatcher: CoroutineDispatcher =
    NsQueueDispatcher(dispatch_get_main_queue())