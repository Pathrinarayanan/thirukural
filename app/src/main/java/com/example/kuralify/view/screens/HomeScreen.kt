package com.example.kuralify.view.screens

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import com.example.kuralify.R
import com.example.kuralify.model.MainViewModel
import com.example.kuralify.searchItemById
import com.example.kuralify.theme.*
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(viewModel: MainViewModel, jsonString: String) {
    var showResults by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.valluvar),
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = viewModel.text,
                onValueChange = { viewModel.text = it },
                placeholder = { Text("Enter the context to match Thirukural") },
                modifier = Modifier
                    .weight(1f)
                    .height(120.dp),
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryBlue,
                    unfocusedBorderColor = LightBlue,
                    focusedTextColor = DarkBlue
                )
            )

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(
                onClick = {
                    if(viewModel.text != "") {
                        if (showResults) {
                            showResults = false
                            viewModel.text = ""
                            viewModel.ids?.value = emptyList()
                        } else {
                            viewModel.viewModelScope.launch {
                                viewModel.getKuralId()
                                showResults = true
                            }
                        }
                    }
                },
                modifier = Modifier
                    .size(48.dp)
                    .background(PrimaryBlue, CircleShape)
            ) {
                Icon(
                    if (!showResults) Icons.Filled.KeyboardArrowUp else Icons.Filled.Refresh,
                    contentDescription = "Search",
                    tint = White
                )
            }
        }


        this.AnimatedVisibility(
            visible = showResults,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f)
                    .padding(top = 16.dp, bottom = 16.dp)
            ) {
                Column(
                    modifier = Modifier,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    viewModel.ids?.value?.forEach { id ->
                        val data = searchItemById(jsonString, id)
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp)
                                .shadow(
                                    elevation = 4.dp,
                                    shape = RoundedCornerShape(16.dp)
                                ),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = White
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = "Kural #${data?.id}",
                                    color = PrimaryBlue,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp
                                )
                                HorizontalDivider(color = LightBlue, thickness = 1.dp)
                                Text(
                                    text = (data?.kural ?: "").replace("<br />", "\n"),
                                    color = DarkBlue,
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.Justify,
                                    fontWeight = FontWeight.Medium
                                )
                                Text(
                                    text = "Transliteration:",
                                    color = PrimaryBlue,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 14.sp
                                )
                                Text(
                                    text = data?.transliteration ?: "",
                                    color = DarkBlue,
                                    fontSize = 14.sp
                                )
                                Text(
                                    text = "Explanation:",
                                    color = PrimaryBlue,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 14.sp
                                )
                                Text(
                                    text = data?.explanation ?: "",
                                    color = DarkBlue,
                                    fontSize = 14.sp
                                )
                                Text(
                                    text = "Couplet:",
                                    color = PrimaryBlue,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 14.sp
                                )
                                Text(
                                    text = data?.couplet ?: "",
                                    color = DarkBlue,
                                    fontSize = 14.sp
                                )
                            }
                        }

                    }
                }


            }


        }


    }
} 