package dev.theananta.vallabh.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.theananta.vallabh.R
import dev.theananta.vallabh.data.OnBoardingStateHolder
import dev.theananta.vallabh.data.onBoardingData

@Composable
fun OnBoardingPageHolder(modifier: Modifier = Modifier, onBoardingData: OnBoardingStateHolder) {
    Column(modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(24.dp)) {
            Surface(
                modifier = Modifier.size(32.dp),
                color = MaterialTheme.colorScheme.surfaceVariant
            ) { }
            Text(
                text = stringResource(R.string.app_name),
                modifier = Modifier.padding(start = 12.dp)
            )
        }
        Image(
            painter = painterResource(R.drawable.bheem_fighting),
            contentDescription = "Bheem & Dhuryodhan Fight",
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight(Alignment.Bottom)
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Column(modifier = Modifier.padding(24.dp)) {
            Text(
                text = stringResource(onBoardingData.title),
                style = MaterialTheme.typography.displayMedium
            )
            Text(
                text = stringResource(onBoardingData.subTitle),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.fillMaxWidth(0.9f)
            )
            Text(
                text = stringResource(onBoardingData.description),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }

}

@Preview
@Composable
private fun OnBoardingStateHolderPreview() {
    OnBoardingPageHolder(onBoardingData = onBoardingData[1])
}