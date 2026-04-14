package com.naraka.heroes.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naraka.heroes.model.HeroWithSkills
import com.naraka.heroes.repository.HeroRepository
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

    // 编辑对话框是否显示
    private val _isEditDialogVisible = MutableStateFlow(false)
    val isEditDialogVisible: StateFlow<Boolean> = _isEditDialogVisible.asStateFlow()

    // 编辑框中的临时文本
    private val _editingBio = MutableStateFlow("")
    val editingBio: StateFlow<String> = _editingBio.asStateFlow()

    init {
        loadHeroDetail()
    }

    private fun loadHeroDetail() {
        viewModelScope.launch {
            heroRepository.getHeroWithSkills(heroId).collect {
                _heroWithSkills.value = it
            }
        }
    }

    fun showEditDialog() {
        val current = _heroWithSkills.value?.hero ?: return
        // 编辑框初始值：有用户自定义则用自定义，否则用官方简介
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
            // 保存后重新加载，刷新 UI
            heroRepository.getHeroWithSkills(heroId).collect {
                _heroWithSkills.value = it
            }
        }
        _isEditDialogVisible.value = false
    }

    fun resetUserBio() {
        viewModelScope.launch {
            heroRepository.updateUserBio(heroId, "")
            heroRepository.getHeroWithSkills(heroId).collect {
                _heroWithSkills.value = it
            }
        }
        _isEditDialogVisible.value = false
    }
}
