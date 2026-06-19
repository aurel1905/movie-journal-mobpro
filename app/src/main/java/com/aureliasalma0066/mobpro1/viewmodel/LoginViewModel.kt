package com.aureliasalma0066.mobpro1.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.aureliasalma0066.mobpro1.data.datastore.UserDataStore
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LoginViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val datastore =
        UserDataStore(application)

    val isLoggedIn =
        datastore.loginFlow.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            false
        )

    val name =
        datastore.nameFlow.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            ""
        )

    val email =
        datastore.emailFlow.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            ""
        )

    fun login(
        name: String,
        email: String
    ) {

        viewModelScope.launch {

            datastore.saveUser(
                name,
                email
            )
        }
    }

    fun logout() {

        viewModelScope.launch {

            datastore.logout()
        }
    }
}