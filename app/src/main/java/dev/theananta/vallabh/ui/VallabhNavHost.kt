package dev.theananta.vallabh.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.functions.functions
import dev.theananta.vallabh.data.onBoardingData
import dev.theananta.vallabh.ui.login.LoginPage
import dev.theananta.vallabh.ui.onboarding.OnBoardingPage
import dev.theananta.vallabh.ui.splash.SplashPage


@Composable
fun VallabhNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = "/register"
    ) {
        composable("/") {
            SplashPage() {
                navController.navigate("/onboarding")
            }
        }
        composable("/onboarding") {
            OnBoardingPage(onboardingPagesData = onBoardingData) {
                navController.navigate("/login")
            }
        }
        composable("/register") {
//            RegisterPage(firebaseUser = null) {
            val input = hashMapOf(
                "name" to "chandan",
                "email" to "khamitarsaichanandsfj;lasd",
                "age" to 20,
                "lifestyle" to "ACTIVE",
                "gender" to "male",
                "dailyRoutine" to listOf(
                    listOf(
                        "collge",
                        mapOf(
                            "hour" to 8,
                            "minutes" to 0
                        )
                    )
                ),
                "levelOfCooking" to "INTERMEDIATE",
                "numberOfDishes" to 1,
                "favouriteFoodItems" to listOf("gulab jamun"),
                "flexiNutritionPreferences" to mapOf(
                    "type" to "VEGETARIAN",
                    "spicePreferences" to 1,
                    "sweetOrHotPreferences" to 1,
                    "allergies" to listOf("none"),
                    "dietaryRestrictions" to listOf("milk")
                ),
                "healthGoals" to listOf("gym"),
                "healthIssues" to listOf("none"),
                "healthMetrics" to listOf(
                    mapOf(
                        "weight" to 60,
                        "height" to 1.8
                    )
                )
            )
            var data by remember {
                mutableStateOf<HashMap<String, Any>?>(null)
            }
            LaunchedEffect(true) {
                val task = Firebase.functions.getHttpsCallable("menuSuggestionFlow").call(input)
                    .continueWith { task ->
                        // This continuation runs on either success or failure, but if the task
                        // has failed then result will throw an Exception which will be
                        // propagated down.
                        val result = task.result?.data as HashMap<String, Any>
                        Log.d("Sunday", result["sunday"].toString())
                        Log.d("Tuesday", result["tuesday"].toString())
                        Log.d("Friday", result["friday"].toString())
                        result
                    }
                task.addOnCompleteListener { task ->
                    data = task.result
                }.addOnFailureListener { e ->
                    Log.e("ERROR", e.toString())
                }

            }
            LazyColumn(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.Top) {
                if (data != null) {

                    items(items = data!!.entries.toList()) { (key, value) ->
                        Card(modifier = Modifier.padding(12.dp)) {
                            Text(key)
                            (value as HashMap<String, Any>).entries.forEach { (key1, value2) ->
                                Text(key1)
                                (value2 as HashMap<String, Any>).entries.forEach { (key3, value3) ->
                                    Text(key3)
                                    Text(value3.toString())
                                }
                            }
                        }
                    }
                } else {
                    item {
                        Text("Loading")
                    }
                }
            }
//
        }
        composable("/login") {
            LoginPage {
                navController.navigate("/register")
            }
        }
    }
}