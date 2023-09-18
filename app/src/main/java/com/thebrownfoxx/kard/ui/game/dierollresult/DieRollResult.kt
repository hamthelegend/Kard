package com.thebrownfoxx.kard.ui.game.dierollresult

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.twotone.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.kard.R
import com.thebrownfoxx.kard.ui.component.ActionCard
import com.thebrownfoxx.kard.ui.component.die.Die
import com.thebrownfoxx.kard.ui.theme.KardIcons
import com.thebrownfoxx.kard.ui.theme.KardTheme

@Composable
fun DieRollResult(
    value: Int,
    onAttackAcknowledged: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val title = stringResource(R.string.you_rolled, value)

    ActionCard(
        title = title,
        actionButtonIcon = KardIcons.Check,
        actionButtonText = stringResource(id = R.string.okay),
        onActionButtonClick = onAttackAcknowledged,
        modifier = modifier,
    ) {
        Die(value = value)
    }
}

@Preview
@Composable
fun DieRollResultPreview() {
    KardTheme {
        DieRollResult(
            value = 5,
            onAttackAcknowledged = {},
            modifier = Modifier.padding(16.dp),
        )
    }
}