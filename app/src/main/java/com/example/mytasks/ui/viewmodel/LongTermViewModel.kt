package com.example.mytasks.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mytasks.data.local.entity.LongTermGoalEntity
import com.example.mytasks.data.repository.LongTermGoalRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class LongTermViewModel(
    private val repository: LongTermGoalRepository,
    private val categoryId: Long
) : ViewModel() {

    private val _currentGoal = MutableStateFlow<LongTermGoalEntity?>(null)
    val currentGoal: StateFlow<LongTermGoalEntity?> = _currentGoal.asStateFlow()

    private val _pendingContent = MutableStateFlow<String?>(null)

    init {
        loadGoal()
        observeAutoSave()
    }

    private fun loadGoal() {
        viewModelScope.launch {
            val goal = repository.getGoalByCategoryId(categoryId)
            if (goal != null) {
                _currentGoal.value = goal
            } else {
                val newGoal = LongTermGoalEntity(categoryId = categoryId, content = "")
                _currentGoal.value = newGoal
            }
        }
    }

    @OptIn(FlowPreview::class)
    private fun observeAutoSave() {
        _pendingContent
            .filterNotNull()
            .debounce(1000) // Auto-save after 1 second of inactivity
            .distinctUntilChanged()
            .onEach { content ->
                saveContent(content)
            }
            .launchIn(viewModelScope)
    }

    fun updateGoalContent(content: String) {
        // Update UI state immediately
        _currentGoal.value = _currentGoal.value?.copy(content = content)
        // Trigger debounced save
        _pendingContent.value = content
    }

    private suspend fun saveContent(content: String) {
        val current = _currentGoal.value ?: return
        repository.insertGoal(current.copy(content = content))
    }
}

class LongTermViewModelFactory(
    private val repository: LongTermGoalRepository,
    private val categoryId: Long
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LongTermViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LongTermViewModel(repository, categoryId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
