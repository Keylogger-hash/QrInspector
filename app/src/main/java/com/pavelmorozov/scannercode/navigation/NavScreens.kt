package com.pavelmorozov.scannercode.navigation

sealed class NavScreens(val route: String) {
    object ScannerPage : NavScreens("Scanner")
    object StartPreview: NavScreens("StartPreview")
    object History: NavScreens("History")
}