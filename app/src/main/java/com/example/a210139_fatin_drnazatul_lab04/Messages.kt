package com.example.a210139_fatin_drnazatul_lab04

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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

data class ChatPreview(val name: String, val lastMsg: String, val time: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessagesScreen(navController: NavHostController) {
    val colorScheme = MaterialTheme.colorScheme

    val chats = listOf(
        ChatPreview("NearBakery", "Your croissants are ready for pickup!", "10:30 AM"),
        ChatPreview("Mira", "Is the tuna still available?", "Yesterday"),
        ChatPreview("Amar", "Thanks for the bread!", "Monday")
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
                        text = "MESSAGES",
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
        ) {
            LazyColumn {
                items(chats) { chat ->
                    ChatItem(chat, colorScheme)
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        color = colorScheme.outlineVariant.copy(alpha = 0.5f)
                    )
                }
            }
        }
    }
}

@Composable
fun ChatItem(chat: ChatPreview, colorScheme: ColorScheme) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* if nak tekan the button, kena add something here */ }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // profile
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Text(chat.name.take(1), fontWeight = FontWeight.Bold, color = colorScheme.onPrimaryContainer)
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(text = chat.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = chat.lastMsg, fontSize = 14.sp, color = colorScheme.onSurfaceVariant, maxLines = 1)
        }

        Text(text = chat.time, fontSize = 12.sp, color = colorScheme.outline)
    }
}