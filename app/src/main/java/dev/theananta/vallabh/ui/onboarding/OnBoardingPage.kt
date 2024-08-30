package dev.theananta.vallabh.ui.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.theananta.vallabh.data.OnBoardingStateHolder
import dev.theananta.vallabh.data.onBoardingData
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    onboardingPagesData: List<OnBoardingStateHolder> = listOf(), onCompleteFlow: () -> Unit = {}
) {
    val onboardingPageState = rememberPagerState(pageCount = { onboardingPagesData.size })
    val coroutineScope = rememberCoroutineScope()
    Column(modifier = modifier) {
        HorizontalPager(state = onboardingPageState, modifier = Modifier.weight(1f)) { page ->
            OnBoardingPageHolder(onBoardingData = onboardingPagesData[page])
        }
        Row(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(bottom = 24.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (i in 0..onboardingPagesData.size - 1) {
                Surface(
                    modifier = Modifier.size(12.dp),
                    color = if (onboardingPageState.currentPage == i) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceContainer,
                    shape = CircleShape
                ) { }
            }
            TextButton(
                onClick = {
                    if (onboardingPageState.currentPage == onboardingPagesData.size - 1) {
                        onCompleteFlow()
                    } else {
                        coroutineScope.launch {
                            onboardingPageState.animateScrollToPage(onboardingPageState.currentPage + 1)
                        }
                    }
                }, modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.End)
            ) {
                Text("Next")
                Icon(
                    Icons.AutoMirrored.Rounded.ArrowForward,
                    contentDescription = null,
                    modifier = Modifier.padding(start = 12.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun OnBoardingPagePreview() {
    OnBoardingPage(onboardingPagesData = onBoardingData)
}