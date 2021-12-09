package com.example.pixabayapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pixabayapp.domain.models.SearchResponse
import com.example.pixabayapp.domain.usecase.SearchImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.example.pixabayapp.domain.utils.State
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchImageUseCase: SearchImageUseCase
) : ViewModel() {

    private var searchLiveData = MutableLiveData<State<SearchResponse>>()

    fun getSearchLiveData(): LiveData<State<SearchResponse>> {
        return searchLiveData
    }

    fun searchImage(apiKey: String, query: String) {
        viewModelScope.launch {
            searchLiveData.postValue(State.loading(null))
            val result = searchImageUseCase.execute(apiKey, query)
            if(result!= null){
                searchLiveData.postValue(State.success(result))

            }
        }

    }


}
