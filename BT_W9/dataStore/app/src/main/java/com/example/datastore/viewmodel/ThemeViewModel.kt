package com.example.datastore.viewmodel


import android.app.Application
import androidx.lifecycle.*
import com.example.datastore.ui.themeapp.AppTheme
import com.example.datastore.ui.themeapp.ThemePreference
import kotlinx.coroutines.launch

class ThemeViewModel(application: Application) : AndroidViewModel(application) {

    private val themePref = ThemePreference(application)
    val currentTheme: LiveData<AppTheme> = themePref.getTheme.asLiveData()

    fun setTheme(theme: AppTheme) {
        viewModelScope.launch {
            themePref.saveTheme(theme)
        }
    }
}
