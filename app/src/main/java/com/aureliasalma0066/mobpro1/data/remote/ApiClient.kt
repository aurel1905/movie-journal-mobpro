package com.aureliasalma0066.mobpro1.data.remote

import com.aureliasalma0066.mobpro1.util.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiClient {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val api: ApiService by lazy {

        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(moshi)
            )
            .build()
            .create(ApiService::class.java)
    }
}

