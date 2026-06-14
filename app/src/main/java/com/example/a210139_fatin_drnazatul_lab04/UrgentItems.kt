package com.example.a210139_fatin_drnazatul_lab04

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UrgentItemsScreen(navController: NavHostController) {
    val colorScheme = MaterialTheme.colorScheme

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
                        text = "⏰ EXPIRING SOON",
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
            // header info card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = colorScheme.primaryContainer)
            ) {
                Text(
                    text = "Rescue these items within Bangi before they're gone!",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Nearby Rescues",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(12.dp))

            // list of items
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                item {
                    UrgentItemBox(
                        storeName = "EMart24 UKM",
                        itemName = "Egg Mayo Sandwiches (3x)",
                        timeLeft = "45 mins left",
                        colorScheme = colorScheme
                    )
                }
                item {
                    UrgentItemBox(
                        storeName = "DECTAR",
                        itemName = "Nasi Ayam Masak Merah (5x)",
                        timeLeft = "1 hour left",
                        colorScheme = colorScheme
                    )
                }
                item {
                    UrgentItemBox(
                        storeName = "Family Mart",
                        itemName = "Onigiri Tuna (2x)",
                        timeLeft = "15 mins left",
                        colorScheme = colorScheme
                    )
                }
            }
        }
    }
}

@Composable
fun UrgentItemBox(storeName: String, itemName: String, timeLeft: String, colorScheme: ColorScheme) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(2.dp, colorScheme.tertiary),
        colors = CardDefaults.cardColors(containerColor = colorScheme.surface)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = storeName,
                    fontSize = 12.sp,
                    color = colorScheme.tertiary,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = itemName,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            // urgency Badge
            Surface(
                color = colorScheme.errorContainer,
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = timeLeft,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    color = colorScheme.onErrorContainer,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}