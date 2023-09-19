package com.thebrownfoxx.kard.ui.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.twotone.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.kard.ui.theme.KardIcons
import com.thebrownfoxx.kard.ui.theme.KardTheme

@Composable
fun SimpleButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
    ) {
        AnimatedContent(
            targetState = text to icon,
            transitionSpec = { fadeIn() togetherWith fadeOut() },
        ) { (newText, newIcon) ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (newIcon != null) {
                    Icon(imageVector = newIcon, contentDescription = text)
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Text(
                    text = newText,
                    fontWeight = FontWeight.ExtraBold,
                )
            }
        }
    }
}

@Preview
@Composable
fun SimpleButtonPreview() {
    KardTheme {
        SimpleButton(
            icon = KardIcons.Check,
            text = "Okay",
            onClick = { /*TODO*/ },
            modifier = Modifier.padding(16.dp)
        )
    }
}