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
import androidx.compose.ui.unit.dp
import dev.theananta.vallabh.R

@Composable
fun OnBoardingPage(modifier: Modifier = Modifier) {
    Column {
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
        Text(
            text = "We're here to help",
            style = MaterialTheme.typography.displayMedium
        )
        Text(
            text = "Lorem empsum Lorem empsum Lorem empsum Lorem empsum Lorem empsum",
            style = MaterialTheme.typography.titleMedium
        )
    }

}