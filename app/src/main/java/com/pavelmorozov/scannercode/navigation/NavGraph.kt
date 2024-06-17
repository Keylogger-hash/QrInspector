package com.pavelmorozov.scannercode.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pavelmorozov.scannercode.scanner.ScannerPage
import com.pavelmorozov.scannercode.startpreview.StartPreview
import com.pavelmorozov.scannercode.history.History

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = NavScreens.StartPreview.route
    ) {
        composable(route = NavScreens.ScannerPage.route) {
            ScannerPage(navController)
        }
        composable(NavScreens.StartPreview.route){
            StartPreview(navController)
        }
        composable(NavScreens.History.route){
            History()
        }
    }

}