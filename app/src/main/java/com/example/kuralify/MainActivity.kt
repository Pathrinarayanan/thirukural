package com.example.kuralify

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.kuralify.model.MainViewModel
import com.example.kuralify.model.ThirukkuralData
import com.example.kuralify.ui.theme.KuralifyTheme
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private  lateinit var viewmodel : MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel = ViewModelProvider(this).get(MainViewModel::class.java)
        enableEdgeToEdge()
        val jsonData = loadJson(context = applicationContext, "thirukkural.json")
        setContent {
            KuralifyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   MainScreen(viewmodel,jsonData)
                }
            }
        }
    }
}


@Composable
fun MainScreen(viewModel: MainViewModel, jsonString: String){
    Column (
        modifier = Modifier.fillMaxSize()
    ){
        OutlinedTextField(
            value = viewModel.text,
            onValueChange = {
                viewModel.text = it
            },
            modifier = Modifier.padding(50.dp).width(300.dp)
                .height(50.dp)
        )
        Button(
            onClick = {
               viewModel.viewModelScope.launch {
                   viewModel.getKuralId()
               }
            }
        ) {
            Text( "Search")
        }
        Column(
            Modifier,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            viewModel.ids?.value?.forEach {id->
                val data = searchItemById(jsonString,id)
                Text("kural id : ${data?.id}")
                Text("kural  : ${data?.kural}")
                Text("Explanation: ${data?.couplet}")
            }
        }
    }

}

fun loadJson(context : Context, fileName: String) : String{
    return context.assets.open(fileName).bufferedReader()
        .use {
            it.readText()
        }
}
fun searchItemById(jsonString: String, targetId: Int): ThirukkuralData? {
    val gson = Gson()
    val itemType = object : TypeToken<List<ThirukkuralData>>(){}.type
    val itemList: List<ThirukkuralData> = gson.fromJson(jsonString, itemType)
    return itemList.find { it.id == targetId }
}
