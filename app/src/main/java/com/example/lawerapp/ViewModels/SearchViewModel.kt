package com.example.lawerapp.ViewModels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.lawerapp.Model.GovernmentModel
import com.example.lawerapp.Repository.GovernmentRepository
import com.example.lawerapp.Repository.PopularLawersRepository
import com.example.lawerapp.Utils.DataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SearchViewModel @ViewModelInject constructor(
    private val governmentRepository: GovernmentRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val _dataset: MutableLiveData<DataState<List<GovernmentModel>>> = MutableLiveData()
    val dataset: LiveData<DataState<List<GovernmentModel>>>
        get() = _dataset
    fun getgovernemts(){
        viewModelScope.launch {
            governmentRepository.get_government()
                .onEach {
                    _dataset.value=it
                }.launchIn(viewModelScope)
        }
    }
}