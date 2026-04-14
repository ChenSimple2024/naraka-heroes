package com.naraka.heroes.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naraka.heroes.model.Hero
import com.naraka.heroes.repository.HeroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroListViewModel @Inject constructor(
    private val heroRepository: HeroRepository
) : ViewModel() {

    private val _heroList = MutableStateFlow<List<Hero>>(emptyList())
    val heroList: StateFlow<List<Hero>> = _heroList.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        loadHeroes()
    }

    private fun loadHeroes() {
        viewModelScope.launch {
            _isLoading.value = true
            heroRepository.loadHeroes(
                onError = {
                    _errorMessage.value = it
                    _isLoading.value = false  // 出错时也要结束 loading
                }
            ).collect { heroes ->
                _heroList.value = heroes
                _isLoading.value = false
            }
        }
    }
}
