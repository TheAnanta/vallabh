package dev.theananta.vallabh.ui.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.theananta.vallabh.R
import dev.theananta.vallabh.ui.theme.VallabhTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterPage(modifier: Modifier = Modifier, registerUser: () -> Unit = {}) {
    var section by remember {
        mutableStateOf(0)
    }
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(stringResource(R.string.app_name))
        })
    }) { innerPadding ->
        LazyColumn(
            modifier = modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (section == 0) {
                item {
                    Icon(
                        Icons.Rounded.AccountCircle,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(vertical = 36.dp)
                            .size(120.dp)
                    )
                }
            }
            item {
                RegisterPageSection(
                    title = "Your personal information",
                    subtitle = "This will allow us draft better health goals and time table for you",
                    nextSection = {
                        section++
                    }, finishSection = {
                        registerUser()
                    }, isLastSection = section == 2
                ) {
                    Column {
//                    TextField() //The user textfield
                        Row {
                            //textfield
                            //textfield
                            //textfield
                        }
                        //textfield
                    }
                }
            }
        }
    }
}

@Composable
fun RegisterPageSection(
    modifier: Modifier = Modifier,
    sectionTitle: String = "ONE",
    title: String,
    subtitle: String,
    isLastSection: Boolean = false,
    nextSection: () -> Unit = {},
    finishSection: () -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    Column(modifier = modifier.padding(16.dp)) {
        Text(
            "SECTION $sectionTitle",
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.alpha(0.5f)
        )
        Text(title, style = MaterialTheme.typography.titleMedium)
        Text(
            subtitle,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(bottom = 24.dp)
                .alpha(0.7f)
        )
        content()
        Button(
            onClick = if (isLastSection) finishSection else nextSection,
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(if (isLastSection) "Finish" else "Next")
            Icon(
                Icons.AutoMirrored.Rounded.ArrowForward,
                contentDescription = null,
                modifier = Modifier.padding(start = 12.dp)
            )
        }
    }
}

@Preview
@Composable
private fun RegisterPageSectionPreview() {
    VallabhTheme {
        RegisterPageSection(title = "Title", subtitle = "Subtitle")
    }
}

@Preview
@Composable
private fun RegisterPagePreview() {
    VallabhTheme {
        RegisterPage()
    }
}