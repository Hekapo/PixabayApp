package com.example.pixabayapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pixabayapp.domain.models.SearchResponse
import com.example.pixabayapp.domain.usecase.SearchImageUseCase
import com.example.pixabayapp.domain.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchImageUseCase: SearchImageUseCase
) : ViewModel() {

    private val _searchStateFlow = MutableStateFlow<State<SearchResponse>>(State.loading(null))
    val searchStateFlow: StateFlow<State<SearchResponse>> get() = _searchStateFlow.asStateFlow()

    init {
        searchImage("24692428-f74523248653204592476e118", "flower")

    }

    fun searchImage(apiKey: String, query: String) {
        viewModelScope.launch {
            val result = searchImageUseCase.execute(apiKey, query)
            if (result != null) {
                _searchStateFlow.value = State.success(result)
            } else {
                _searchStateFlow.value = State.error("error", null)
            }
        }

    }


}
