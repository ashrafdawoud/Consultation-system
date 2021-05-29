package com.example.lawerapp.ViewModels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.lawerapp.Model.LayersModel
import com.example.lawerapp.Repository.PopularLawersRepository
import com.example.lawerapp.Utils.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class CategoryLawersViewModel @ViewModelInject constructor(
    private val popularLawersRepository: PopularLawersRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) :ViewModel(){
    val _dataset: MutableLiveData<DataState<List<LayersModel>>> = MutableLiveData()
    val dataset : LiveData<DataState<List<LayersModel>>>
        get() = _dataset

    fun getCategoryLawyers(id:String){
        viewModelScope.launch {
            popularLawersRepository.getCategoryLawyers(id)
                .onEach {
                    _dataset.value=it
                }.launchIn(viewModelScope)
        }

    }
}