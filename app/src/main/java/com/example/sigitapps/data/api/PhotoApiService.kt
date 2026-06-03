package com.example.sigitapps.data.api

import com.example.sigitapps.data.model.PhotoModel
import retrofit2.http.GET

interface PhotoApiService {
    @GET("v2/list")
    suspend fun getPhotos(): List<PhotoModel>
}