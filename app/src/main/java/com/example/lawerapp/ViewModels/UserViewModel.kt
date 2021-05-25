package com.example.lawerapp.ViewModels

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.lawerapp.Model.UserModel
import com.example.lawerapp.Repository.UserRepository
import com.example.lawerapp.Utils.DataState
import com.example.lawerapp.Utils.MainStateEvent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class UserViewModel
@ViewModelInject
constructor(
    private val userRepository: UserRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _dataState: MutableLiveData<DataState<List<UserModel>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<UserModel>>>
        get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent){
        viewModelScope.launch {
            when(mainStateEvent){
                is MainStateEvent.GetBlogsEvent -> {
                    userRepository.getBlogs()
                        .onEach {dataState ->
                            _dataState.value = dataState
                            Log.e("usersertest","$dataState")
                        }
                        .launchIn(viewModelScope)
                }

                MainStateEvent.None -> {
                    Log.e("usersertest","none")
                }
            }
        }
    }

}

