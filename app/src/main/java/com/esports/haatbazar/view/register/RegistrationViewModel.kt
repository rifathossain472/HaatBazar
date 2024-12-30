package com.esports.haatbazar.view.register

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.esports.haatbazar.core.DataState
import com.esports.haatbazar.data.AuthService

class RegistrationViewModel : ViewModel() {
    private val _registrationResponce = MutableLiveData<DataState<RegisterUser>>()

    val registrationResponce: MutableLiveData<DataState<RegisterUser>> = _registrationResponce

    fun userRegister(user: RegisterUser) {
        _registrationResponce.postValue(DataState.Loading())

        val authService = AuthService()

        authService.userRegistration(user).addOnSuccessListener {
            _registrationResponce.postValue(DataState.Success(user))
            Log.d("TAG", "userRegister: Success")
        }.addOnFailureListener {
            _registrationResponce.postValue(DataState.Error(it.message))
            Log.d("TAG", "userRegister: ${it.message}")
        }
    }
}