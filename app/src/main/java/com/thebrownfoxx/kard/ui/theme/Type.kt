package com.thebrownfoxx.kard.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import com.thebrownfoxx.kard.R

private val jetbrainsMono = FontFamily(
    Font(R.font.jetbrains_mono)
)

// Set of Material typography styles to start with
private val defaultTypography = Typography()

val Typography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = jetbrainsMono),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = jetbrainsMono),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = jetbrainsMono),
    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = jetbrainsMono),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = jetbrainsMono),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = jetbrainsMono),
    titleLarge = defaultTypography.titleLarge.copy(fontFamily = jetbrainsMono),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = jetbrainsMono),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = jetbrainsMono),
    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = jetbrainsMono),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = jetbrainsMono),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = jetbrainsMono),
    labelLarge = defaultTypography.labelLarge.copy(fontFamily = jetbrainsMono),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = jetbrainsMono),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = jetbrainsMono)
)


@Preview
@Composable
fun TypographyPreview() {
    KardTheme {
        Column {
            Text(text = "Display Large", style = MaterialTheme.typography.displayLarge)
            Text(text = "Display Medium", style = MaterialTheme.typography.displayMedium)
            Text(text = "Display Small", style = MaterialTheme.typography.displaySmall)
            Text(text = "Headline Large", style = MaterialTheme.typography.headlineLarge)
            Text(text = "Headline Medium", style = MaterialTheme.typography.headlineMedium)
            Text(text = "Headline Small", style = MaterialTheme.typography.headlineSmall)
            Text(text = "Title Large", style = MaterialTheme.typography.titleLarge)
            Text(text = "Title Medium", style = MaterialTheme.typography.titleMedium)
            Text(text = "Title Small", style = MaterialTheme.typography.titleSmall)
            Text(text = "Body Large", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Body Medium", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Body Small", style = MaterialTheme.typography.bodySmall)
            Text(text = "Label Large", style = MaterialTheme.typography.labelLarge)
            Text(text = "Label Medium", style = MaterialTheme.typography.labelMedium)
            Text(text = "Label Small", style = MaterialTheme.typography.labelSmall)
        }
    }
}