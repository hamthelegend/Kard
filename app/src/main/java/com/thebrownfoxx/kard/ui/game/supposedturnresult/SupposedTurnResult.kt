package com.thebrownfoxx.kard.ui.game.supposedturnresult

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.twotone.Check
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.kard.R
import com.thebrownfoxx.kard.logic.Player
import com.thebrownfoxx.kard.logic.turn.SupposedTurnResult
import com.thebrownfoxx.kard.ui.component.SimpleButton
import com.thebrownfoxx.kard.ui.theme.KardIcons
import com.thebrownfoxx.kard.ui.theme.KardTheme

@Composable
fun SupposedTurnResult(
    supposedTurnResult: SupposedTurnResult,
    onAcknowledge: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val text = when (supposedTurnResult) {
        is SupposedTurnResult.Attack -> stringResource(
            R.string.attack_supposed_result,
            supposedTurnResult.player.name,
            supposedTurnResult.totalDamage,
        )

        is SupposedTurnResult.Block -> stringResource(
            if (supposedTurnResult.blockedAnything) R.string.block_supposed_result_successful
            else R.string.block_supposed_result_failed,
            supposedTurnResult.player.name,
        )

        is SupposedTurnResult.Heal -> stringResource(
            R.string.heal_supposed_result,
            supposedTurnResult.player.name,
            Player.HealAmount,
        )
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
    ) {
        SupposedTurnResultIcon(supposedTurnResult = supposedTurnResult)
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
        )
        SimpleButton(
            icon = KardIcons.Check,
            text = stringResource(id = R.string.okay),
            onClick = onAcknowledge,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview
@Composable
fun SupposedTurnResultPreview() {
    KardTheme {
        SupposedTurnResult(
            supposedTurnResult = SupposedTurnResult.Attack(
                player = Player(id = 1, name = "Player"),
                rolledDie = 3,
                doubleDamage = false,
                totalDamage = 20,
            ),
            onAcknowledge = {},
            modifier = Modifier.padding(16.dp),
        )
    }
}