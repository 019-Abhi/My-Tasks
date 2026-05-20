package com.example.mytasks.data.repository

import com.example.mytasks.data.local.datastore.NavigationPrefs
import kotlinx.coroutines.flow.Flow

class NavigationRepository(private val navigationPrefs: NavigationPrefs) {
    val lastRoute: Flow<String?> = navigationPrefs.lastRoute

    suspend fun saveLastRoute(route: String) {
        navigationPrefs.saveLastRoute(route)
    }
}
