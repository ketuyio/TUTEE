package com.example.tutee.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tutee.ui.theme.screens.home.TutorsScreen
import com.example.tutee.ui.theme.screens.loginprefference.LoginScreen
import com.example.tutee.ui.theme.screens.signup.SignupScreen
import com.example.tutee.ui.theme.screens.splash.SplashScreen
import com.example.tutee.ui.theme.screens.teachers.AddTutorScreen
import com.example.tutee.ui.theme.screens.teachers.ViewTutorScreen

@SuppressLint("ComposableDestinationInComposeScope")
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController:NavHostController = rememberNavController(),
    startDestination:String = SPLASH_URL
){
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier){
        composable(SPLASH_URL){
            SplashScreen(navController = navController)
        }
        composable(LOGIN_URL){
            LoginScreen(navController = navController)
        }
        composable(SIGNUP_URL){
            SignupScreen(navController = navController)
        }
        composable(HOME_URL){
            TutorsScreen(navController = navController)
        }
        composable(UPLOAD_URL){
            AddTutorScreen(navController = navController)
        }
        composable(TUTORS_URL){
            ViewTutorScreen(navController = navController)
        }


}
}