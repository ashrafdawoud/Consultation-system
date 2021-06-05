package com.example.lawerapp.ViewModels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.lawerapp.Model.LayersModel
import com.example.lawerapp.Repository.FavouriteRepository
import com.example.lawerapp.Repository.PopularLawersRepository
import com.example.lawerapp.Utils.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
 class FavouriteViewModel @ViewModelInject constructor(
    private val favouriteRepository: FavouriteRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val _datasetAllLayers: MutableLiveData<DataState<List<LayersModel>>> = MutableLiveData()
    val _datasetDelete: MutableLiveData<DataState<Int>> = MutableLiveData()
    val _datasetInsert: MutableLiveData<DataState<Long>> = MutableLiveData()
    val _datasetgetone: MutableLiveData<DataState<Boolean>> = MutableLiveData()

    val datasetAllLayers: LiveData<DataState<List<LayersModel>>>
        get() = _datasetAllLayers
    val datasetDelete: LiveData<DataState<Int>>
        get() = _datasetDelete
    val datasetInsert: LiveData<DataState<Long>>
        get() = _datasetInsert
    val datasetgetone: LiveData<DataState<Boolean>>
        get() = _datasetgetone

    fun getAllUsers() {
        viewModelScope.launch {
            favouriteRepository.getAllFavLayers()
                .onEach {
                    _datasetAllLayers.value = it
                }.launchIn(viewModelScope)
        }
    }

    fun deleteuser(layersModel: LayersModel) {
        viewModelScope.launch {
            favouriteRepository.deleteFavLawyer(layersModel)
                .onEach {
                    _datasetDelete.value = it
                }.launchIn(viewModelScope)
        }
    }

    fun insertuser(layersModel: LayersModel) {
        viewModelScope.launch {
            favouriteRepository.insertFavLawyer(layersModel)
                .onEach {
                    _datasetInsert.value = it
                }.launchIn(viewModelScope)
        }
    }
    fun getoneuser(layersModel: LayersModel) {
        viewModelScope.launch {
            favouriteRepository.getoneLayer(layersModel.objectId)
                .onEach {
                    _datasetgetone.value = it
                }.launchIn(viewModelScope)
        }
    }
}