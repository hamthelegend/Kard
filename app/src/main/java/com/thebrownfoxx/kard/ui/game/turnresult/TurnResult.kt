package com.thebrownfoxx.kard.ui.game.turnresult

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.kard.R
import com.thebrownfoxx.kard.logic.Player
import com.thebrownfoxx.kard.logic.turn.TurnResult
import com.thebrownfoxx.kard.ui.component.SimpleButton
import com.thebrownfoxx.kard.ui.theme.KardIcons

@Composable
fun TurnResult(
    turnResult: TurnResult,
    onAcknowledge: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val text = when (turnResult) {
        is TurnResult.AttackAttack -> if (turnResult.winner != null && turnResult.loser != null) {
            stringResource(
                R.string.attack_attack,
                turnResult.winner.name,
                turnResult.damageDealt,
                turnResult.loser.name
            )
        } else {
            stringResource(R.string.attack_attack_tie)
        }

        is TurnResult.AttackBlock -> stringResource(
            R.string.attack_block,
            turnResult.block.player.name,
            turnResult.damageBlocked,
            turnResult.attack.player.name,
            turnResult.damageDealt,
            turnResult.block.player.name
        )

        is TurnResult.AttackHeal -> stringResource(
            R.string.attack_heal,
            turnResult.heal.player.name,
            turnResult.attack.player.name,
            turnResult.attack.totalDamage,
            turnResult.heal.player.name
        )

        is TurnResult.BlockBlock -> stringResource(R.string.block_block)
        is TurnResult.BlockHeal -> stringResource(
            R.string.block_heal,
            turnResult.heal.player.name,
            Player.HealAmount
        )

        is TurnResult.HealHeal -> stringResource(R.string.heal_heal, Player.HealAmount)
    }

    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
        )
        SimpleButton(
            icon = KardIcons.Check,
            text = stringResource(id = R.string.okay),
            onClick = onAcknowledge,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}