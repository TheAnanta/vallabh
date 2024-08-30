package dev.theananta.vallabh.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.theananta.vallabh.data.onBoardingData
import dev.theananta.vallabh.ui.login.LoginPage
import dev.theananta.vallabh.ui.onboarding.OnBoardingPage
import dev.theananta.vallabh.ui.register.RegisterPage
import dev.theananta.vallabh.ui.splash.SplashPage

@Composable
fun VallabhNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = "/onboarding"
    ) {
        composable("/") {
            SplashPage()
        }
        composable("/onboarding") {
            OnBoardingPage(onboardingPagesData = onBoardingData)
        }
        composable("/register") {
            RegisterPage()
        }
        composable("/login") {
            LoginPage {
                navController.navigate("/register")
            }
        }
    }
}