package com.thebrownfoxx.kard.ui.game.supposedturnresult

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material.icons.twotone.Shield
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.kard.R
import com.thebrownfoxx.kard.logic.turn.SupposedTurnResult
import com.thebrownfoxx.kard.ui.theme.KardIcons

@Composable
fun SupposedTurnResultIcon(
    supposedTurnResult: SupposedTurnResult,
    modifier: Modifier = Modifier,
) {
    val icon = when (supposedTurnResult) {
        is SupposedTurnResult.Attack -> when (supposedTurnResult.doubleDamage) {
            true -> ImageVector.vectorResource(id = R.drawable.axe)
            false -> ImageVector.vectorResource(id = R.drawable.sword)
        }

        is SupposedTurnResult.Block -> KardIcons.Shield
        is SupposedTurnResult.Heal -> KardIcons.Favorite
    }

    val contentDescription = stringResource(
        when (supposedTurnResult) {
            is SupposedTurnResult.Attack -> R.string.attack
            is SupposedTurnResult.Block -> R.string.block
            is SupposedTurnResult.Heal -> R.string.heal
        }
    )

    Surface(
        shape = CircleShape,
        color = MaterialTheme.colorScheme.tertiaryContainer,
        modifier = modifier,
    ) {
        Box(
            modifier = modifier.size(64.dp),
        ) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }
}