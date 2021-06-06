package com.example.lawerapp.ViewModels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.lawerapp.Model.CategoryModel
import com.example.lawerapp.Model.LayersModel
import com.example.lawerapp.Repository.CategoryRepository
import com.example.lawerapp.Repository.SearchRepository
import com.example.lawerapp.Utils.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class SearchResultViewModel @ViewModelInject constructor(
    private val searchRepository: SearchRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val _dataset: MutableLiveData<DataState<List<LayersModel>>> = MutableLiveData()

    val dataset: LiveData<DataState<List<LayersModel>>>
        get() = _dataset

    fun searchfunction(
        city: String,
        category: String,
        name: String
    ) {
        viewModelScope.launch {
            searchRepository.get_lawyers_without_with_name(city,category,name)
                .onEach {
                    _dataset.value=it
                }.launchIn(viewModelScope)
        }
    }
}