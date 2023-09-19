package com.thebrownfoxx.kard.ui.game.gameover

import androidx.compose.material.icons.twotone.AccountCircle
import androidx.compose.material.icons.twotone.Memory
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.thebrownfoxx.kard.R
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
    val text = stringResource(if (playerWon) R.string.player_won_text else R.string.player_lost_text)

    if (show) {
        AlertDialog(
            onDismissRequest = onAcknowledge,
            icon = { Icon(imageVector = icon, contentDescription = iconContentDescription) },
            title = { Text(text = title) },
            text = { Text(text = text) },
            confirmButton = {
                TextButton(onClick = onAcknowledge) {
                    Text(text = stringResource(id = R.string.okay))
                }
            },
            modifier = modifier,
        )
    }
}

// TODO: Rename onChanged to onChange