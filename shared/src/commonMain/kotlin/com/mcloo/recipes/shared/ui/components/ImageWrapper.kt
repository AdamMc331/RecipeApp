package com.mcloo.recipes.shared.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImage
import com.mcloo.recipes.shared.ui.displaymodels.ImageDisplayModel
import org.jetbrains.compose.resources.painterResource

@Composable
fun ImageWrapper(
    image: ImageDisplayModel,
    contentDescription: String?,
    modifier: Modifier = Modifier,
) {
    when (image) {
        is ImageDisplayModel.Local -> {
            LocalImage(
                image = image,
                contentDescription = contentDescription,
                modifier = modifier,
            )
        }
        is ImageDisplayModel.Remote -> {
            RemoteImage(
                image = image,
                contentDescription = contentDescription,
                modifier = modifier,
            )
        }
    }
}

@Composable
private fun RemoteImage(
    image: ImageDisplayModel.Remote,
    contentDescription: String?,
    modifier: Modifier = Modifier,
) {
    AsyncImage(
        model = image.url,
        contentDescription = contentDescription,
        modifier = modifier,
    )
}

@Composable
private fun LocalImage(
    image: ImageDisplayModel.Local,
    contentDescription: String?,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(image.id),
        contentDescription = contentDescription,
        modifier = modifier,
    )
}
