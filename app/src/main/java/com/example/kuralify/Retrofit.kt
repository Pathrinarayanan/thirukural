package com.example.kuralify

import com.example.kuralify.model.KuralRequest
import com.example.kuralify.model.KuralResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitService {

    @POST("/get_embedding_with_kural")
    suspend fun getKuralId(
        @Body body : KuralRequest

    ): KuralResponse
}