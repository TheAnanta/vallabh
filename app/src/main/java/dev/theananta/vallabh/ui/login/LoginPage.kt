package dev.theananta.vallabh.ui.login

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.Firebase
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import dev.theananta.vallabh.R
import dev.theananta.vallabh.ui.theme.VallabhTheme
import kotlinx.coroutines.launch

@Composable
fun LoginPage(modifier: Modifier = Modifier, onAuthenticated: () -> Unit = {}) {
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    Scaffold(snackbarHost = {
        SnackbarHost(snackbarHostState)
    }) { innerPadding ->
        Column(modifier = modifier.padding(innerPadding)) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = null,
                Modifier
                    .weight(1f)
                    .fillMaxWidth(), contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(24.dp)) {
                Text("Login", style = MaterialTheme.typography.displayLarge)
                Text(
                    "Enter your credentials to login",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 24.dp)
                )
                LoginWithGoogleButton(onAuthenticated = onAuthenticated, onFailed = { error ->
                    snackbarHostState.showSnackbar(error)
                })
            }
        }
    }
}

@Composable
fun LoginWithGoogleButton(
    modifier: Modifier = Modifier,
    onAuthenticated: () -> Unit = {},
    onFailed: suspend (String) -> Unit = {}
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    Button(onClick = {
        val gso =
            GetSignInWithGoogleOption.Builder("553663159560-a7pj5e94jk66u7q3b12j30m44a3nasdm.apps.googleusercontent.com")
                .build()
        coroutineScope.launch {
            val result = CredentialManager.create(context).getCredential(
                context = context, request = GetCredentialRequest.Builder().setCredentialOptions(
                    listOf(gso),
                ).build()
            )
            val credential = result.credential
            val TAG = "LoginWithGoogleButton"
            when (credential) {
                is CustomCredential -> {
                    if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                        try {
                            // Use googleIdTokenCredential and extract id to validate and
                            // authenticate on your server.
                            val googleIdTokenCredential = GoogleIdTokenCredential
                                .createFrom(credential.data)
                            val firebaseCredential = GoogleAuthProvider.getCredential(
                                googleIdTokenCredential.idToken,
                                null
                            )
                            Firebase.auth.signInWithCredential(firebaseCredential)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        onAuthenticated()
                                    } else {
                                        //handle the error here
                                        coroutineScope.launch {
                                            onFailed(
                                                task.exception?.localizedMessage
                                                    ?: "An error occurred"
                                            )
                                        }
                                    }
                                }

                        } catch (e: GoogleIdTokenParsingException) {
                            Log.e(TAG, "Received an invalid google id token response", e)
                        }
                    } else {
                        // Catch any unrecognized credential type here.
                        Log.e(TAG, "Unexpected type of credential")
                    }
                }

                else -> {
                    // Catch any unrecognized credential type here.
                    Log.e(TAG, "Unexpected type of credential")
                }
            }
        }


    }) {
        Text("Sign in with Google")
    }
}

@Preview
@Composable
private fun LoginPagePreview() {
    VallabhTheme {
        LoginPage()
    }
}