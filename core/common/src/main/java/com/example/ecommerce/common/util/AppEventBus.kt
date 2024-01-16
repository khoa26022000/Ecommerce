package com.example.ecommerce.common.util

import android.os.SystemClock
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object AppEventBus {
    private const val DISPATCH_THRESHOLD = 2000L
    private var lastTimeDispatched: Long = 0
    private var lastEvent: AppEvent = AppEvent.Underfined

    private val dispatcher = MutableSharedFlow<AppEvent>()
    val listener = dispatcher.asSharedFlow()

    suspend fun dispatchEvent(event: AppEvent) = dispatcher.emit(event)

    suspend fun dispatchSingleEvent(event: AppEvent) {
        if (event::class == lastEvent::class && SystemClock.elapsedRealtime() - lastTimeDispatched < DISPATCH_THRESHOLD) return
        lastTimeDispatched = SystemClock.elapsedRealtime()
        lastEvent = event
        dispatcher.emit(event)
    }
}
sealed class AppEvent {
    object NavigateToHome : AppEvent()

    data class OutOfSession(val needReset: Boolean? = false) : AppEvent()

    object Underfined : AppEvent()

    object  RestartApp : AppEvent()

    object  ShowNoNetworkDialog : AppEvent()

    object ShowCommonErrorDialog : AppEvent()
}