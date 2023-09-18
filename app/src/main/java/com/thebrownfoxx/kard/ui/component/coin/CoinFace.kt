package com.thebrownfoxx.kard.ui.component.coin

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.twotone.Android
import androidx.compose.material.icons.twotone.Toll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.kard.R
import com.thebrownfoxx.kard.logic.extension.CoinFace
import com.thebrownfoxx.kard.ui.theme.KardIcons
import com.thebrownfoxx.kard.ui.theme.KardTheme

@Composable
fun NullCoinFace(modifier: Modifier = Modifier) {
    Box(modifier = modifier.size(64.dp)) {
        Text(
            text = "?",
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.align(Alignment.Center),
        )
    }
}

@Composable
fun CoinFace(
    value: CoinFace,
    modifier: Modifier = Modifier,
) {
    val icon = if (value == CoinFace.Heads) KardIcons.Android else KardIcons.Toll
    val contentDescription = stringResource(
        if (value == CoinFace.Heads) R.string.heads else R.string.tails
    )

    Box(modifier = modifier.size(64.dp)) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            modifier = Modifier.align(Alignment.Center).size(32.dp),
        )
    }
}

@Preview
@Composable
fun NullCoinFacePreview() {
    KardTheme {
        NullCoinFace(modifier = Modifier.padding(16.dp))
    }
}

@Preview
@Composable
fun HeadsCoinFacePreview() {
    KardTheme {
        CoinFace(
            value = CoinFace.Heads,
            modifier = Modifier.padding(16.dp),
        )
    }
}

@Preview
@Composable
fun TailsCoinFacePreview() {
    KardTheme {
        CoinFace(
            value = CoinFace.Tails,
            modifier = Modifier.padding(16.dp),
        )
    }
}