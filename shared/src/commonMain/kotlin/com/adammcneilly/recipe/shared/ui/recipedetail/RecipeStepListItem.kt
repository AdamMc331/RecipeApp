package com.adammcneilly.recipe.shared.ui.recipedetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adammcneilly.recipe.shared.ui.displaymodels.RecipeStepDisplayModel

@Composable
fun RecipeStepListItem(
    step: RecipeStepDisplayModel,
    modifier: Modifier = Modifier,
) {
    val paragraphStyle = ParagraphStyle(
        textIndent = TextIndent(restLine = 12.sp),
        lineBreak = LineBreak.Paragraph,
    )

    Row(
        modifier = modifier
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = "${step.index}.",
        )

        Text(
            text = step.instructions,
        )
    }
}
