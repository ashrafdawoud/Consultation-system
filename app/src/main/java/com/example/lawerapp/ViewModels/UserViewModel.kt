package com.example.lawerapp.ViewModels

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.lawerapp.Model.UserModel
import com.example.lawerapp.Repository.UserRepository
import com.example.lawerapp.Retrofit.Entities.SucssesEntity
import com.example.lawerapp.Retrofit.Entities.UserRetrofitEntity
import com.example.lawerapp.Retrofit.RetrofitInterface
import com.example.lawerapp.Utils.DataState
import com.example.lawerapp.Utils.MainStateEvent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi
class UserViewModel
@ViewModelInject
constructor(
    private val userRepository: UserRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _dataState: MutableLiveData<DataState<UserModel>> = MutableLiveData()
    private val _dataStateSignup: MutableLiveData<DataState<SucssesEntity>> = MutableLiveData()

    val dataState: LiveData<DataState<UserModel>>
        get() = _dataState

    val dataStatesignup: LiveData<DataState<SucssesEntity>>
        get() = _dataStateSignup

    fun setStateEvent(mainStateEvent: MainStateEvent, email: String, password: String) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.GetBlogsEvent -> {
                    userRepository.getBlogs(email, password)
                        .onEach { dataState ->
                            _dataState.value = dataState
                            Log.e("usersertest", "$dataState")
                        }
                        .launchIn(viewModelScope)
                }

                MainStateEvent.None -> {
                    Log.e("usersertest", "none")
                }
            }
        }
    }

    fun postuser(map: HashMap<String, String>) {
        viewModelScope.launch {
            userRepository.postuser(map).onEach { dataState ->
            _dataStateSignup.value=dataState
                Log.e("usersertestsignup", "$dataState")
            }.launchIn(viewModelScope)
        }
    }
    /*suspend fun getdatafromapi() {
        val client = OkHttpClient().newBuilder().connectTimeout(250, TimeUnit.SECONDS)
            .writeTimeout(250, TimeUnit.SECONDS)
            .readTimeout(250, TimeUnit.SECONDS)
            .build()
        val request: Request = Request.Builder()
            .header("X-Parse-Application-Id","m4fV65YIwkezflLzC5dVe8t1XMlWyV1FUsZ2IVkw")
            .header("X-Parse-REST-API-Key","0SISQ04mVSUHOEGL6q5VwkE9UuS3OFySUkZ0BL8L")
            //.header("dataType", "json")
            .url("https://parseapi.back4app.com/classes/User?where={\"email\":\"ashraf@gmail.com\",\"password\":\"123456\"}")
            .method("GET", null)
            .build()
        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                response.body?.let { Log.e("okhttp_res", it.string()) }

            }
        })
    }*/

}

