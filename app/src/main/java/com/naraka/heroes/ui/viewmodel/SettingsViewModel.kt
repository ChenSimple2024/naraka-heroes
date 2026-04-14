package com.naraka.heroes.ui.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

enum class AppLanguage { CHINESE, ENGLISH }

@HiltViewModel
class SettingsViewModel @Inject constructor() : ViewModel() {
    private val _language = MutableStateFlow(AppLanguage.CHINESE)
    val language: StateFlow<AppLanguage> = _language.asStateFlow()

    fun toggleLanguage() {
        _language.value = if (_language.value == AppLanguage.CHINESE)
            AppLanguage.ENGLISH else AppLanguage.CHINESE
    }
}
