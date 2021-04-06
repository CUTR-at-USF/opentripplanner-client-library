package edu.usf.cutr.otp

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal actual val ApplicationDispatcher: CoroutineDispatcher = Dispatchers.Default
