package com.example.lawerapp.ViewModels

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.lawerapp.Model.CalenderModel
import com.example.lawerapp.Model.LayersModel
import com.example.lawerapp.Repository.CalenderRepository
import com.example.lawerapp.Retrofit.Entities.SucssesEntity
import com.example.lawerapp.Utils.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
@ExperimentalCoroutinesApi
class CalenderViewModel @ViewModelInject constructor(
    private val calenderRepository: CalenderRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) :ViewModel() {
    val _dataset:MutableLiveData<DataState<List<CalenderModel>>> = MutableLiveData()
    val dataset : LiveData<DataState<List<CalenderModel>>>
        get() = _dataset
    private val _dataStateappo: MutableLiveData<DataState<SucssesEntity>> = MutableLiveData()
    val dataStatesignup: LiveData<DataState<SucssesEntity>>
        get() = _dataStateappo
    fun getdata(objectid:String,date:String){
        viewModelScope.launch {
            calenderRepository.getreservedDates(objectid,date)
                .onEach {
                    _dataset.value=it
                }.launchIn(viewModelScope)
        }

    }
    fun postappointent(map: HashMap<String, String>) {
        viewModelScope.launch {
            calenderRepository.postappointment(map)
                .onEach {
                    _dataStateappo.value=it
                }.launchIn(viewModelScope)
        }
    }
}