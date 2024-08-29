package dev.theananta.vallabh.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.theananta.vallabh.ui.theme.VallabhTheme
import androidx.compose.ui.unit.dp

@Composable
fun HomePage(modifier: Modifier = Modifier) {
    Text(
        text = "New component launched...",
        modifier = modifier.padding(10.dp)
    )
}


@Preview(showBackground = true)
@Composable
private fun HomePreview() {
    VallabhTheme {
        HomePage()
    }
}