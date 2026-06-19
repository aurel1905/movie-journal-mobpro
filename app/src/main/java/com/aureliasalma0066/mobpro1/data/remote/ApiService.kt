package com.aureliasalma0066.mobpro1.data.remote

import com.aureliasalma0066.mobpro1.model.Film
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("films")
    suspend fun getFilms(
        @Header("apikey") apiKey: String,
        @Header("Authorization") authorization: String,
        @Query("user_id") userId: String
    ): List<Film>

    @Headers(
        "Content-Type: application/json",
        "Prefer: return=representation"
    )
    @POST("films")
    suspend fun addFilm(
        @Header("apikey") apiKey: String,
        @Header("Authorization") authorization: String,
        @Body film: Film
    ): Response<List<Film>>

    @DELETE("films")
    suspend fun deleteFilm(
        @Header("apikey") apiKey: String,
        @Header("Authorization") authorization: String,
        @Query("id") id: String
    ): Response<Unit>
}