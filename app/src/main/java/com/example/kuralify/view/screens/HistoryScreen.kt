package com.example.kuralify.view.screens

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.kuralify.model.KuralEntity
import com.example.kuralify.model.MainViewModel
import com.example.kuralify.theme.DarkBlue
import com.example.kuralify.theme.PrimaryBlue
import com.example.kuralify.searchItemById

@Composable
fun HistoryScreen(viewModel: MainViewModel, jsonString: String) {
    var selectedHistoryItem by remember { mutableStateOf<KuralEntity?>(null) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var historyItemToDelete by remember { mutableStateOf<KuralEntity?>(null) }
    val historyList = viewModel.historyList

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Your Search History",
            fontSize = 24.sp,
            color = DarkBlue,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))

        if (historyList.isEmpty()) {
            Text(
                text = "No search history yet",
                fontSize = 16.sp,
                color = DarkBlue,
                textAlign = TextAlign.Center
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(historyList) { historyItem ->
                    var showDropdown by remember { mutableStateOf(false) }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = PrimaryBlue.copy(alpha = 0.1f)
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clickable {
                                            selectedHistoryItem = if (selectedHistoryItem == historyItem) null else historyItem
                                        }
                                ) {
                                    Text(
                                        text = historyItem.prompt ?: "",
                                        color = DarkBlue,
                                        fontSize = 16.sp
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = "Kural IDs: ${historyItem.ids?.joinToString(", ") ?: "None"}",
                                        color = DarkBlue,
                                        fontSize = 14.sp
                                    )
                                }
                                
                                Box {
                                    IconButton(
                                        onClick = { showDropdown = true }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.MoreVert,
                                            contentDescription = "More options",
                                            tint = DarkBlue
                                        )
                                    }
                                    
                                    DropdownMenu(
                                        expanded = showDropdown,
                                        onDismissRequest = { showDropdown = false }
                                    ) {
                                        DropdownMenuItem(
                                            text = { Text("Delete") },
                                            onClick = {
                                                showDropdown = false
                                                historyItemToDelete = historyItem
                                                showDeleteDialog = true
                                            },
                                            leadingIcon = {
                                                Icon(
                                                    imageVector = Icons.Default.Delete,
                                                    contentDescription = "Delete"
                                                )
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // Show Thirukural details when item is selected
                    if (selectedHistoryItem == historyItem) {
                        AnimatedVisibility(
                            visible = true,
                            enter = fadeIn() + expandVertically(),
                            exit = fadeOut() + shrinkVertically()
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                historyItem.ids?.forEach { id ->
                                    val data = searchItemById(jsonString, id)
                                    Card(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 8.dp),
                                        shape = MaterialTheme.shapes.medium,
                                        colors = CardDefaults.cardColors(
                                            containerColor = PrimaryBlue.copy(alpha = 0.1f)
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
                                                fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                                                fontSize = 18.sp
                                            )
                                            Divider(color = PrimaryBlue.copy(alpha = 0.3f))
                                            Text(
                                                text = (data?.kural ?: "").replace("<br />", "\n"),
                                                color = DarkBlue,
                                                fontSize = 16.sp,
                                                textAlign = TextAlign.Justify
                                            )
                                            Text(
                                                text = "Transliteration:",
                                                color = PrimaryBlue,
                                                fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
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
                                                fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
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
                                                fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
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
        }
    }

    // Delete Confirmation Dialog
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Delete History Item") },
            text = { Text("Are you sure you want to delete this history item?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        historyItemToDelete?.let { item ->
                            viewModel.deleteHistoryItem(item)
                        }
                        showDeleteDialog = false
                    }
                ) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDeleteDialog = false }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
} 