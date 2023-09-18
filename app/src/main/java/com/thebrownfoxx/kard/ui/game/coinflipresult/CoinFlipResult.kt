package com.thebrownfoxx.kard.ui.game.coinflipresult

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.twotone.Check
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.kard.R
import com.thebrownfoxx.kard.logic.extension.CoinFace
import com.thebrownfoxx.kard.ui.component.ActionCard
import com.thebrownfoxx.kard.ui.component.coin.Coin
import com.thebrownfoxx.kard.ui.theme.KardIcons
import com.thebrownfoxx.kard.ui.theme.KardTheme

@Composable
fun CoinFlipResult(
    guessedCoin: CoinFace,
    flippedCoin: CoinFace,
    onBlockAcknowledged: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val title = when (guessedCoin) {
        flippedCoin -> stringResource(R.string.coins_matched)
        else -> stringResource(R.string.coins_did_not_match)
    }

    ActionCard(
        title = title,
        actionButtonIcon = KardIcons.Check,
        actionButtonText = stringResource(id = R.string.okay),
        onActionButtonClick = onBlockAcknowledged,
        modifier = modifier,
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(32.dp, Alignment.CenterHorizontally)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            ) {
                Coin(value = guessedCoin)
                Text(text = stringResource(R.string.picked))
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            ) {
                Coin(value = flippedCoin)
                Text(text = stringResource(R.string.flipped))
            }
        }
    }
}

@Preview
@Composable
fun CoinFlipResultPreview() {
    KardTheme {
        CoinFlipResult(
            guessedCoin = CoinFace.Heads,
            flippedCoin = CoinFace.Tails,
            onBlockAcknowledged = {},
            modifier = Modifier.padding(16.dp),
        )
    }
}