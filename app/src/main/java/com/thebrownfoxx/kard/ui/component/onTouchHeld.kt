package com.thebrownfoxx.kard.ui.component

import android.util.Log
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.pointer.pointerInput
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.time.Duration
import kotlin.time.Duration.Companion.nanoseconds
import kotlin.time.Duration.Companion.seconds

fun Modifier.onTouchHeld(
    pollDelay: Duration = 1.seconds / 240,
    onRelease: () -> Unit = {},
    onHold: (timeElapsed: Duration) -> Unit,
) = composed {
    val scope = rememberCoroutineScope()
    pointerInput(Unit) {
        detectTapGestures(
            onPress = {
                val initialDownTime = System.nanoTime()
                var released = false
                scope.launch {
                    withContext(Dispatchers.Default) {
                        tryAwaitRelease()
                        released = true
                    }
                }
                scope.launch {
                    withContext(Dispatchers.Default) {
                        while (!released) {
                            Log.d("TouchHeld", "released = $released")
                            val timeElapsed = System.nanoTime() - initialDownTime
                            onHold(timeElapsed.nanoseconds)
                            delay(pollDelay)
                        }
                        onRelease()
                    }
                }
            },
        )
    }
}