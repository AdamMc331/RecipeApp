package com.mcloo.recipes.shared

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Inspiration: https://mohammedev.hashnode.dev/usedebounce-in-androids-jetpack-compose
 *
 * This is a helper extension function that will debounce changes to a Compose state property.
 *
 * It will run a delay for the supplied [delayMillis], and if this property remains
 * unchanged during that time, it will trigger the [onChange] callback.
 *
 * If the property does change within the [delayMillis], the job is cancelled and a callback
 * will not occur.
 */
@Composable
fun <T> T.useDebounce(
    delayMillis: Long = 300L,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    onChange: suspend (T) -> Unit,
): T {
    val state by rememberUpdatedState(this)

    DisposableEffect(state) {
        val job = coroutineScope.launch {
            delay(delayMillis)
            onChange(state)
        }

        onDispose {
            job.cancel()
        }
    }

    return state
}
