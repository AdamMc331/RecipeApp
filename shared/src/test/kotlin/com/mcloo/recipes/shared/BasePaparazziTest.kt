package com.mcloo.recipes.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import app.cash.paparazzi.Paparazzi
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import com.mcloo.recipes.shared.theme.RecipeTheme
import org.junit.Rule
import org.junit.runner.RunWith

/**
 * This base class allows us to write Paparazzi tests that validate composable content in both light and dark theme
 * using a parameterized test. Just extend this base class and call [snapshot] with your composable content.
 */
@RunWith(TestParameterInjector::class)
abstract class BasePaparazziTest {
    @get:Rule
    val _paparazzi = Paparazzi()

    @TestParameter
    val useDarkTheme: Boolean = false

    /**
     * Validates the supplied [content] in both light and dark theme.
     */
    fun snapshot(
        screenPadding: Dp = 16.dp,
        content: @Composable () -> Unit,
    ) {
        _paparazzi.snapshot {
            RecipeTheme(
                darkTheme = useDarkTheme,
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(screenPadding),
                    ) {
                        content()
                    }
                }
            }
        }
    }
}
