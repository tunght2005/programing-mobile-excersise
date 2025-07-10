package com.example.datastore.ui.themeapp

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("theme_pref")

class ThemePreference(private val context: Context) {

    companion object {
        val THEME_KEY = stringPreferencesKey("app_theme")
    }

    val themeFlow: Flow<AppTheme> = context.dataStore.data.map { preferences ->
        when (preferences[THEME_KEY]) {
            "DARK" -> AppTheme.DARK
            "PINK" -> AppTheme.PINK
            "BLUE" -> AppTheme.BLUE
            else -> AppTheme.LIGHT
        }
    }

    suspend fun saveTheme(theme: AppTheme) {
        context.dataStore.edit { prefs ->
            prefs[THEME_KEY] = theme.name
        }
    }
}

