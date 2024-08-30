package dev.theananta.vallabh.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.theananta.vallabh.R
import dev.theananta.vallabh.ui.theme.VallabhTheme
import kotlinx.coroutines.delay

@Composable
fun SplashPage(modifier: Modifier = Modifier, navigate: ()->Unit = {}) {
    LaunchedEffect(true) {
        delay(2000)
        navigate()
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 44.dp)
    ) {

        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.displayLarge
        )
        Text(
            text = "your tasty personal chef",
            style = MaterialTheme.typography.titleMedium
        )
        LinearProgressIndicator(
            modifier = Modifier.padding(top = 24.dp)
        )
        Image(
            painter = painterResource(R.drawable.kumba_1),
            contentDescription = "The Bheema having food of ladduus",
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight(Alignment.Bottom)
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashPagePreview() {
    VallabhTheme {
        SplashPage()
    }
}