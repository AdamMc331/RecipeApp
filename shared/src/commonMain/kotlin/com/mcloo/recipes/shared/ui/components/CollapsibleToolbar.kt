package com.mcloo.recipes.shared.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mcloo.recipes.shared.ui.components.CollapsibleToolbar.COLLAPSED_TOOLBAR_HEIGHT
import com.mcloo.recipes.shared.ui.components.CollapsibleToolbar.EXPANDED_TOOLBAR_HEIGHT
import com.mcloo.recipes.shared.ui.utils.convertToDp
import com.mcloo.recipes.shared.ui.utils.convertToPx

object CollapsibleToolbar {
    val EXPANDED_TOOLBAR_HEIGHT = 192.dp
    val COLLAPSED_TOOLBAR_HEIGHT = 56.dp
}

data class CollapsibleToolbarState(
    val toolbarHeightDp: Dp,
    val scrollRatio: Float,
    val nestedScrollConnection: NestedScrollConnection,
)

@Composable
fun rememberCollapsibleToolbarState(): CollapsibleToolbarState {
    val expandedToolbarHeightPx = EXPANDED_TOOLBAR_HEIGHT.convertToPx()
    val collapsedToolbarHeightPx = COLLAPSED_TOOLBAR_HEIGHT.convertToPx()

    var toolbarOffsetHeightPx by remember {
        mutableStateOf(0f)
    }

    val toolbarHeightDp = EXPANDED_TOOLBAR_HEIGHT + toolbarOffsetHeightPx.convertToDp()

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(
                available: Offset,
                source: NestedScrollSource,
            ): Offset {
                val delta = available.y
                val newOffset = toolbarOffsetHeightPx + delta
                val minOffset = -expandedToolbarHeightPx + collapsedToolbarHeightPx
                toolbarOffsetHeightPx = newOffset.coerceIn(minOffset, 0f)

                // Watch the scroll, but don't do anything,
                // so the lazy column still scrolls normally.
                return Offset.Zero
            }
        }
    }

    val totalScrollDistance = (EXPANDED_TOOLBAR_HEIGHT - COLLAPSED_TOOLBAR_HEIGHT)
    val availableScrollDistance = (toolbarHeightDp - COLLAPSED_TOOLBAR_HEIGHT)
    val ratio = availableScrollDistance / totalScrollDistance

    return remember(
        toolbarOffsetHeightPx,
        ratio,
        nestedScrollConnection,
    ) {
        CollapsibleToolbarState(
            toolbarHeightDp = toolbarHeightDp,
            scrollRatio = ratio,
            nestedScrollConnection = nestedScrollConnection,
        )
    }
}
