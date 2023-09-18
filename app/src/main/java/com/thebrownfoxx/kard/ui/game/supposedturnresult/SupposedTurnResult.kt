package com.thebrownfoxx.kard.ui.game.supposedturnresult

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.kard.R
import com.thebrownfoxx.kard.logic.Player
import com.thebrownfoxx.kard.logic.turn.SupposedTurnResult
import com.thebrownfoxx.kard.ui.component.SimpleButton

@Composable
fun SupposedTurnResult(
    supposedTurnResult: SupposedTurnResult,
    onAcknowledge: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val text = when (supposedTurnResult) {
        is SupposedTurnResult.Attack -> "${supposedTurnResult.player.name} has queued ${supposedTurnResult.totalDamage} damage."
        is SupposedTurnResult.Block -> "${supposedTurnResult.player.name} chose to block."
        is SupposedTurnResult.Heal -> "${supposedTurnResult.player.name} chose to heal by ${Player.HealAmount} hp."
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        SupposedTurnResultIcon(supposedTurnResult = supposedTurnResult)
        Text(text = text)
        SimpleButton(text = stringResource(id = R.string.okay), onClick = onAcknowledge)
    }
}