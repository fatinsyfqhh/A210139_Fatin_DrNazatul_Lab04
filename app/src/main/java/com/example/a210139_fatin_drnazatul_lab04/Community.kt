package com.example.a210139_fatin_drnazatul_lab04

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunityScreen(navController: NavHostController) {
    val colorScheme = MaterialTheme.colorScheme

    // leaderboard data
    val heroes = listOf(
        "Qila" to "24 items",
        "Amar" to "19 items",
        "Mira" to "12 items",
        "Mark" to "12 items",
        "Xuein" to "8 items"
    )

    Scaffold(
        topBar = {
            Surface(
                color = colorScheme.surfaceVariant.copy(alpha = 0.5f),
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 20.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) { Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back to Home",
                        tint = colorScheme.tertiary
                    ) }
                    Text(
                        text = "COMMUNITY",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = colorScheme.tertiary
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorScheme.background)
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            // Impact Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = colorScheme.primaryContainer)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Your Impact 🌱", fontWeight = FontWeight.Bold, color = colorScheme.onPrimaryContainer)
                        Text("You've saved 15kg of food!", fontSize = 14.sp)
                    }
                    Text("Level 4", fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(text = "Top Rescuers this Week", fontSize = 18.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(12.dp))

            // Leaderboard
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(heroes.size) { index ->
                    val (name, score) = heroes[index]
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = colorScheme.surface,
                        tonalElevation = 2.dp
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("${index + 1}", fontWeight = FontWeight.Bold, modifier = Modifier.width(30.dp))
                            Box(modifier = Modifier.size(36.dp).clip(CircleShape).background(colorScheme.secondaryContainer))
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(text = name, modifier = Modifier.weight(1f), fontWeight = FontWeight.Medium)
                            Text(text = score, color = colorScheme.tertiary, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}