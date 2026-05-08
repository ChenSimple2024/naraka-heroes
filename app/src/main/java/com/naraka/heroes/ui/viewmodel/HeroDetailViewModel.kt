package com.naraka.heroes.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naraka.heroes.core.data.repository.HeroRepository
import com.naraka.heroes.core.model.HeroWithSkills
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val heroRepository: HeroRepository
) : ViewModel() {

    private val heroId: Long = checkNotNull(savedStateHandle["heroId"])

    private val _heroWithSkills = MutableStateFlow<HeroWithSkills?>(null)
    val heroWithSkills: StateFlow<HeroWithSkills?> = _heroWithSkills.asStateFlow()

    private val _isEditDialogVisible = MutableStateFlow(false)
    val isEditDialogVisible: StateFlow<Boolean> = _isEditDialogVisible.asStateFlow()

    private val _editingBio = MutableStateFlow("")
    val editingBio: StateFlow<String> = _editingBio.asStateFlow()

    init {
        loadHeroDetail()
    }

    private fun loadHeroDetail() {
        viewModelScope.launch {
            _heroWithSkills.value = heroRepository.getHeroWithSkills(heroId)
        }
    }

    fun showEditDialog() {
        val current = _heroWithSkills.value?.hero ?: return
        _editingBio.value = current.userBio.ifBlank { current.bio }
        _isEditDialogVisible.value = true
    }

    fun dismissEditDialog() {
        _isEditDialogVisible.value = false
    }

    fun onEditingBioChange(text: String) {
        _editingBio.value = text
    }

    fun saveUserBio() {
        viewModelScope.launch {
            heroRepository.updateUserBio(heroId, _editingBio.value.trim())
            _heroWithSkills.value = heroRepository.getHeroWithSkills(heroId)
        }
        _isEditDialogVisible.value = false
    }

    fun resetUserBio() {
        viewModelScope.launch {
            heroRepository.updateUserBio(heroId, "")
            _heroWithSkills.value = heroRepository.getHeroWithSkills(heroId)
        }
        _isEditDialogVisible.value = false
    }
}
