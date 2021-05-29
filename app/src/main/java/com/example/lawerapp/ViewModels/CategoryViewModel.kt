package com.example.lawerapp.ViewModels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.lawerapp.Model.CategoryModel
import com.example.lawerapp.Repository.CategoryRepository
import com.example.lawerapp.Repository.UserRepository
import com.example.lawerapp.Utils.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class CategoryViewModel @ViewModelInject constructor(
    private val categoryRepository: CategoryRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val _dataset:MutableLiveData<DataState<List<CategoryModel>>> = MutableLiveData()

    val dataset :LiveData<DataState<List<CategoryModel>>>
    get() = _dataset

    fun getCategoryFromInternet(){
        viewModelScope.launch {
            categoryRepository.getCategory()
                .onEach { dataState ->
                    _dataset.value=dataState
                }.launchIn(viewModelScope)
        }
    }
}