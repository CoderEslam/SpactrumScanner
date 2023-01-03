package com.doubleclick.spactrumscanner.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.doubleclick.spactrumscanner.Repo.Repository
import com.doubleclick.spactrumscanner.model.ResponseData
import androidx.lifecycle.viewModelScope
import com.doubleclick.spactrumscanner.model.Login
import com.doubleclick.spactrumscanner.model.SendData
import com.doubleclick.spactrumscanner.model.UserData
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

/**
 * Created By Eslam Ghazy on 1/3/2023
 */
class ScannerViewModel(private val repository: Repository) : ViewModel() {

    var myResponseScanner: MutableLiveData<Call<ResponseData>> = MutableLiveData()
    var myResponseLogin: MutableLiveData<Call<UserData>> = MutableLiveData()

    fun pushData(token: String, sendData: SendData) {
        viewModelScope.launch {
            val response = repository.postData(token, sendData)
            myResponseScanner.value = response
        }
    }

    fun loginData(login: Login) {
        viewModelScope.launch {
            val response = repository.loginData(login)
            myResponseLogin.value = response
        }
    }

}