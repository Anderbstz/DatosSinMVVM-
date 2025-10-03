package com.example.lab_07

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val userDao = UserDatabase.getDatabase(application).userDao()

    // Insertar usuario
    fun insert(user: User) {
        viewModelScope.launch {
            userDao.insert(user)
        }
    }

    // Obtener todos los usuarios
    val allUsers = liveData {
        emit(userDao.getAll())
    }

    // ✅ Eliminar último usuario
    fun deleteLast() {
        viewModelScope.launch {
            userDao.deleteLast()
        }
    }
}

