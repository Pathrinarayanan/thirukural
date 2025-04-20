package com.example.kuralify.view.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kuralify.model.LessonItem
import com.example.kuralify.model.leaderLessons
import com.example.kuralify.searchItemById
import com.example.kuralify.theme.DarkBlue
import com.example.kuralify.theme.PrimaryBlue

@Composable
fun LessonsScreen(jsonString: String) {
    var selectedLesson by remember { mutableStateOf<LessonItem?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (selectedLesson != null) {
            // Show lesson details
            LessonDetails(
                lesson = selectedLesson!!,
                jsonString = jsonString,
                onBackClick = { selectedLesson = null }
            )
        } else {
            // Show lessons list
            Text(
                text = "Life Lessons",
                fontSize = 24.sp,
                color = DarkBlue,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(leaderLessons) { lesson ->
                    LessonCard(
                        lesson = lesson,
                        onClick = { selectedLesson = lesson }
                    )
                }
            }
        }
    }
}

@Composable
fun LessonCard(
    lesson: LessonItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeightIn(120.dp,160.dp)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            PrimaryBlue.copy(alpha = 0.8f),
                            PrimaryBlue.copy(alpha = 0.4f)
                        )
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = lesson.title,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = lesson.description,
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun LessonDetails(
    lesson: LessonItem,
    jsonString: String,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Back button and title
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = DarkBlue
                )
            }
            Text(
                text = lesson.title,
                fontSize = 20.sp,
                color = DarkBlue,
                fontWeight = FontWeight.Bold
            )
        }

        // Kural details
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(lesson.kuralIds) { id ->
                val data = searchItemById(jsonString, id.kuralId)
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = 8.dp),
                    shape = RoundedCornerShape(16.dp),
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
                            fontWeight = FontWeight.Bold,
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
                        Text(
                            text = "TakeAway:",
                            color = PrimaryBlue,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp
                        )
                        Text(
                            text = id?.quote ?: "",
                            color = DarkBlue,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
} 