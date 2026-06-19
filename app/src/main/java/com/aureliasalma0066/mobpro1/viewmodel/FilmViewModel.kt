package com.aureliasalma0066.mobpro1.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aureliasalma0066.mobpro1.data.remote.ApiClient
import com.aureliasalma0066.mobpro1.model.Film
import com.aureliasalma0066.mobpro1.util.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FilmViewModel : ViewModel() {

    private val _films =
        MutableStateFlow<List<Film>>(emptyList())

    val films: StateFlow<List<Film>> = _films

    private val _loading =
        MutableStateFlow(false)

    val loading: StateFlow<Boolean> = _loading

    fun getFilms(userId: String) {

        viewModelScope.launch {

            try {

                _loading.value = true

                val result =
                    ApiClient.api.getFilms(
                        apiKey = Constants.API_KEY,
                        authorization = "Bearer ${Constants.API_KEY}",
                        userId = "eq.$userId"
                    )

                _films.value = result

            } catch (e: Exception) {

                Log.e(
                    "SUPABASE",
                    e.toString()
                )

                e.printStackTrace()
            }

            _loading.value = false
        }
    }

    fun addFilm(
        film: Film
    ) {

        viewModelScope.launch {

            try {

                _loading.value = true

                android.util.Log.d(
                    "SUPABASE",
                    "Kirim data: $film"
                )

                val response =
                    ApiClient.api.addFilm(
                        apiKey = Constants.API_KEY,
                        authorization =
                            "Bearer ${Constants.API_KEY}",
                        film = film
                    )

                Log.d(
                    "SUPABASE",
                    "CODE = ${response.code()}"
                )

                Log.d(
                    "SUPABASE",
                    "SUCCESS = ${response.isSuccessful}"
                )

                Log.d(
                    "SUPABASE",
                    "BODY = ${response.body()}"
                )

                getFilms(film.user_id)

            } catch (e: Exception) {

                android.util.Log.e(
                    "SUPABASE",
                    e.toString()
                )

                e.printStackTrace()
            }

            _loading.value = false
        }
    }

    fun deleteFilm(
        id: Long,
        userId: String
    ) {

        viewModelScope.launch {

            try {

                ApiClient.api.deleteFilm(
                    apiKey = Constants.API_KEY,
                    authorization = "Bearer ${Constants.API_KEY}",
                    id = "eq.$id"
                )

                getFilms(userId)

            } catch (e: Exception) {

                Log.e(
                    "SUPABASE",
                    e.toString()
                )

                e.printStackTrace()
            }
        }
    }
}