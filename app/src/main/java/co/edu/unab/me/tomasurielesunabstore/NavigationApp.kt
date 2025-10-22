package co.edu.unab.me.tomasurielesunabstore

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun NavigationApp() {
    val navController = rememberNavController()

    val auth = Firebase.auth
    val currentUser = auth.currentUser
    val startDestination = if (currentUser != null) "home" else "login"

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable("login") {
            LoginScreen(
                onClickRegister = { navController.navigate("register") },
                onSuccesfullLogin = {
                    navController.navigate("home") {
                        popUpTo(navController.graph.startDestinationId) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable("register") {
            RegisterScreen(
                onClickBack = { navController.popBackStack() },
                onSuccessfulRegister = {
                    navController.navigate("home") {
                        popUpTo(navController.graph.startDestinationId) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable("home") {
            HomeScreen(
                onClickLogout = {
                    // HomeScreen ya hace auth.signOut(); aquí solo navegamos
                    navController.navigate("login") {
                        popUpTo(navController.graph.startDestinationId) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}