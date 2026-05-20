package com.example.mytasks.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "navigation_prefs")

class NavigationPrefs(private val context: Context) {

    companion object {
        val LAST_ROUTE = stringPreferencesKey("last_route")
    }

    val lastRoute: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[LAST_ROUTE]
        }

    suspend fun saveLastRoute(route: String) {
        context.dataStore.edit { preferences ->
            preferences[LAST_ROUTE] = route
        }
    }
}
