package com.example.kuralify.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.kuralify.RetrofitHelper
import com.example.kuralify.RetrofitService

class MainViewModel : ViewModel() {
    val service = RetrofitHelper.getInstance().create(RetrofitService::class.java)
    var ids : MutableState<List<Int>?>? = mutableStateOf(emptyList())
    var text by mutableStateOf("")
    suspend fun getKuralId() {
        val data  = service.getKuralId(KuralRequest(text))
         ids?.value = data.kural_ids
    }
}