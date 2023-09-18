package com.thebrownfoxx.kard.ui.game.turnresult

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.twotone.Check
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.kard.R
import com.thebrownfoxx.kard.logic.Player
import com.thebrownfoxx.kard.logic.turn.TurnResult
import com.thebrownfoxx.kard.ui.component.SimpleButton
import com.thebrownfoxx.kard.ui.extension.Elevation
import com.thebrownfoxx.kard.ui.theme.KardIcons

@Composable
fun TurnResult(
    turnResult: TurnResult,
    onAcknowledge: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val text = when (turnResult) {
        is TurnResult.AttackAttack -> if (turnResult.winner != null && turnResult.loser != null) {
            "${turnResult.winner.name} won the duel and dealt ${turnResult.damageDealt} to ${turnResult.loser.name}."
        } else {
            "It's a tie. No damage. was dealt."
        }
        is TurnResult.AttackBlock -> "${turnResult.block.player.name} has blocked ${turnResult.damageBlocked} damage. ${turnResult.attack.player.name} has dealt ${turnResult.damageDealt} damage to ${turnResult.block.player.name}."
        is TurnResult.AttackHeal -> "${turnResult.heal.player.name}'s healing was canceled. ${turnResult.attack.player.name} has dealt ${turnResult.attack.totalDamage} to ${turnResult.heal.player.name}"
        is TurnResult.BlockBlock -> "Nothing happened."
        is TurnResult.BlockHeal -> "${turnResult.heal.player.name} healed by ${Player.HealAmount} hp."
        is TurnResult.HealHeal -> "Both players healed by ${Player.HealAmount} hp."
    }

    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        tonalElevation = Elevation.level(1),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        ) {
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
}