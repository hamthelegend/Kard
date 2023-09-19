package com.thebrownfoxx.kard.ui.game.gameover

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.twotone.AccountCircle
import androidx.compose.material.icons.twotone.Check
import androidx.compose.material.icons.twotone.Memory
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.kard.R
import com.thebrownfoxx.kard.ui.component.SimpleButton
import com.thebrownfoxx.kard.ui.theme.KardIcons

@Composable
fun GameOver(
    show: Boolean,
    playerWon: Boolean,
    onAcknowledge: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val icon = if (playerWon) KardIcons.AccountCircle else KardIcons.Memory
    val iconContentDescription = stringResource(if (playerWon) R.string.player else R.string.ai)
    val title = stringResource(if (playerWon) R.string.you_won else R.string.you_lost)
    val text =
        stringResource(if (playerWon) R.string.player_won_text else R.string.player_lost_text)

    if (show) {
        AlertDialog(
            onDismissRequest = onAcknowledge,
            icon = {
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.tertiaryContainer,
                    modifier = modifier,
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = iconContentDescription,
                        modifier = Modifier
                            .padding(16.dp)
                            .size(32.dp),
                    )
                }
            },
            title = { Text(text = title) },
            text = {
                Text(
                    text = text,
                    textAlign = TextAlign.Center,
                )
            },
            confirmButton = {
                SimpleButton(
                    icon = KardIcons.Check,
                    text = stringResource(id = R.string.okay),
                    onClick = onAcknowledge,
                    modifier = Modifier.fillMaxWidth(),
                )
            },
            modifier = modifier,
        )
    }
}

// TODO: Rename onChanged to onChange