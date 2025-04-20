package com.example.kuralify.view

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import com.example.kuralify.model.MainViewModel
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.indendshape.StraightIndent
import com.example.kuralify.theme.KuralifyTheme
import com.example.kuralify.theme.*
import com.example.kuralify.view.screens.HomeScreen
import com.example.kuralify.view.screens.HistoryScreen
import com.example.kuralify.view.screens.LessonsScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun MainScreen(viewModel: MainViewModel,jsonString: String) {
    KuralifyTheme {
        var selectedIndex by remember { mutableStateOf(0) }

        val barColor = LightBlue
        val ballColor = PrimaryBlue
        val contentColor = DarkBlue

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Kuralify",
                            color = White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = PrimaryBlue
                    )
                )
            },
            bottomBar = {
                AnimatedNavigationBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp),
                    selectedIndex = selectedIndex,
                    barColor = barColor,
                    ballColor = ballColor,
                    indentAnimation = StraightIndent(
                        animationSpec = tween(durationMillis = 250)
                    )
                ) {
                    val items = listOf(
                        NavBarItem("Home", Icons.Filled.Home),
                        NavBarItem("History", Icons.Filled.DateRange),
                        NavBarItem("Lessons", Icons.Filled.Person)
                    )

                    items.forEachIndexed { index, item ->
                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(top = 12.dp)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null
                                ) {
                                    selectedIndex = index
                                },
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.label,
                                tint = contentColor,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = item.label,
                                color = contentColor,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                when (selectedIndex) {
                    0 -> HomeScreen(viewModel = viewModel,jsonString)
                    1 -> HistoryScreen()
                    2 -> LessonsScreen()
                }
            }
        }
    }
}

data class NavBarItem(val label: String, val icon: ImageVector)

