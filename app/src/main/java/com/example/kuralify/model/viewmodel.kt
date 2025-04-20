package com.example.kuralify.model

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kuralify.MainApplication
import com.example.kuralify.RetrofitHelper
import com.example.kuralify.RetrofitService
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val service = RetrofitHelper.getInstance().create(RetrofitService::class.java)
    var ids : MutableState<List<Int>?>? = mutableStateOf(emptyList())
    var text by mutableStateOf("")
    private val _historyList = mutableStateOf<List<KuralEntity>>(emptyList())
    val historyList: List<KuralEntity> get() = _historyList.value
    var showResults by   mutableStateOf(false)
    val db = MainApplication.db

    init {
        loadHistory()
    }

    private fun loadHistory() {
        viewModelScope.launch {
            db.getDao().getHistory().observeForever { history ->
                _historyList.value = history
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addHistory() {
        viewModelScope.launch {
            db.getDao().addEntity(KuralEntity(prompt = text, ids = ids?.value))
            loadHistory()
        }
    }

    fun deleteHistoryItem(item: KuralEntity) {
        viewModelScope.launch {
            db.getDao().deleteEntity(item)
            loadHistory()
        }
    }

    suspend fun getKuralId() {
        val data = service.getKuralId(KuralRequest(text))
        ids?.value = data.kural_ids
    }
}