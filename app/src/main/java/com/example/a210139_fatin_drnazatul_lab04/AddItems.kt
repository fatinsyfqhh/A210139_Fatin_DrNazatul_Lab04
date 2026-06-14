package com.example.a210139_fatin_drnazatul_lab04

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItemsScreen(navController: NavHostController, viewModel: ResQBiteViewModel) { // Added viewModel parameter
    val colorScheme = MaterialTheme.colorScheme

    // collect the current food list from ViewModel to display it below
    val currentFoodList by viewModel.foodList.collectAsState()

    // state for the form fields
    var foodName by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    val categories = listOf("Bakery", "Canned", "Packed Meals", "Fruits", "Drinks", "Snacks")
    var expanded by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf(categories[0]) }

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
                        text = "GIVE AWAY",
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
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Sharing is Caring!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = colorScheme.primary
            )

            Text("Fill in the details of the food you'd like to share with the community.")

            OutlinedTextField(
                value = foodName,
                onValueChange = { foodName = it },
                label = { Text("Food Name") },
                placeholder = { Text("e.g. 3 packs of Fried Rice") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            // dropdown
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = selectedCategory,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Category") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                    modifier = Modifier.menuAnchor().fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    categories.forEach { category ->
                        DropdownMenuItem(
                            text = { Text(category) },
                            onClick = {
                                selectedCategory = category
                                expanded = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }
            }

            OutlinedTextField(
                value = quantity,
                onValueChange = { quantity = it },
                label = { Text("Quantity / Portion") },
                placeholder = { Text("e.g. 2 servings") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )
            OutlinedTextField(
                value = location,
                onValueChange = { location = it },
                label = { Text("Pick-up Location") },
                placeholder = { Text("e.g. Kolej Keris Mas") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            // submit button
            Button(
                onClick = {
                    if (foodName.isNotEmpty()) {
                        // new FoodItem object
                        val newItem = FoodItem(
                            title = foodName,
                            distance = "0.0km",
                            user = "Me (You)",
                            imageRes = R.drawable.resqlogo
                        )
                        // save to viewModel
                        viewModel.addFoodItem(newItem)

                        // clear the form
                        foodName = ""
                        quantity = ""
                        location = ""
                    }
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorScheme.tertiary)
            ) {
                Text("List My Food!", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }

            // show recently added items
            if (currentFoodList.isNotEmpty()) {
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                Text(text = "Your Recent Listings", fontWeight = FontWeight.Bold, fontSize = 18.sp)

                // show items added via ViewModel
                currentFoodList.reversed().forEach { item ->
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = colorScheme.surfaceVariant.copy(alpha = 0.3f))
                    ) {
                        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "🎁", fontSize = 24.sp)
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(text = item.title, fontWeight = FontWeight.Bold)
                                Text(text = "Location: $location", fontSize = 12.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}