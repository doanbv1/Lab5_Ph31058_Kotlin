package com.ph31058.lab5_ph31058

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ph31058.lab5_ph31058.ui.theme.Lab5_Ph31058Theme
import java.time.format.TextStyle

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab5_Ph31058Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Bai1() {
    var userName by remember { mutableStateOf("") }
    var passWord by remember { mutableStateOf("") }
    var checked by remember { mutableStateOf(true) }
    var showDialog by remember { mutableStateOf(false) }
    var dialogMessage by remember { mutableStateOf("") }

    val paddingModifier = Modifier.padding(20.dp)
    val flex1 = Modifier.fillMaxSize()
    Column(
        modifier = flex1,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier,
            elevation = CardDefaults.cardElevation(10.dp),
        ) {
            Column(
                modifier = paddingModifier,
            ) {
                Image(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "a",
                )
                OutlinedTextField(
                    value = userName,
                    onValueChange = { userName = it },
                    label = { Text(text = "User Name") }

                )
                OutlinedTextField(
                    value = passWord,
                    onValueChange = { passWord = it },
                    label = { Text(text = "Pass Word") }
                )
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Switch(
                        checked = checked,
                        onCheckedChange = {
                            checked = it
                        }
                    )
                    Text(text = "Remember Me", modifier = Modifier.padding(start = 10.dp))
                }
                if (showDialog) {
                    MyDialog(
                        onConfirmation = { showDialog = false },
                        dialogTitle = "Notification",
                        dialogMessage = dialogMessage
                    )
                }
                Button(
                    modifier = Modifier.width(280.dp),
                    onClick = {
                        if (userName.equals("admin") && passWord.equals("admin123")) {
                            dialogMessage = "Login Successful"
                        } else {
                            dialogMessage = "Sai tên đăng nhập hoặc mật khẩu"
                        }
                        showDialog = true
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
                ) {
                    Text(text = "Login", color = Color.White)
                }

            }
        }
    }

}

@Composable
fun MyDialog(
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogMessage: String
) {
    androidx.compose.ui.window.Dialog(onDismissRequest = { /*TODO*/ }) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),
            modifier = Modifier.padding(20.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = dialogTitle, style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = dialogMessage, style = MaterialTheme.typography.bodyMedium)
                Button(onClick = { onConfirmation() }) {
                    Text(text = "Okay")
                }
            }
        }
    }
}

@Composable
fun bai2() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val isChecked = remember { mutableStateOf(false) }
            if (isChecked.value) {
                Image(
                    painter = painterResource(id = R.drawable.img_on),
                    contentDescription = "Light is On",
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
            } else {
                Image(
                    painter = painterResource(
                        id =
                        R.drawable.img_off
                    ),
                    contentDescription = "Light is Off",
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
            }
            Spacer(modifier = Modifier.height(24.dp))

            Switch(
                checked = isChecked.value,
                onCheckedChange = { isChecked.value = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Green,
                    uncheckedThumbColor = Color.Gray,
                    checkedTrackColor = Color.Green.copy(alpha = 0.5f),
                    uncheckedTrackColor = Color.Gray.copy(
                        alpha =
                        0.5f
                    ),
                    checkedBorderColor = Color.Green.copy(
                        alpha =
                        0.75f
                    ),
                )
            )
        }
    }

}


@Composable
fun Bai3() {
    val categories = listOf(
        "Fiction", "Mystery", "Science Fiction", "Fantasy", "Adventure", "Historical", "Horror",
        "Romance"
    )
    val suggestions = listOf(
        "Biography", "Cookbook", "Poetry",
        "Self-help", "Thriller"
    )
    var selectedCategories by remember {
        mutableStateOf(setOf<String>())
    }
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()


    ) {
        Text(
            "Choose a category:", style =
            MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        AssistChip(
            onClick = { /* Do something */ },
            label = { Text("Need help?") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        CategoryChips(categories, selectedCategories, onCategorySelected = { category ->
            selectedCategories = if (selectedCategories.contains(category))
                selectedCategories - category
            else
                selectedCategories + category
        })
        Spacer(modifier = Modifier.height(16.dp))
        SuggestionChips(suggestions, selectedCategories,
            onSuggestionSelected = { suggestion ->
                selectedCategories = selectedCategories + suggestion
            })
        if (selectedCategories.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            SelectedCategoriesChips(selectedCategories, onCategoryRemoved =
            { category ->
                selectedCategories = selectedCategories - category
            })
            Spacer(modifier = Modifier.height(4.dp))
            AssistChip(
                onClick = { selectedCategories = setOf() },
                label = {
                    Text(text = "Clear selections")
                },
                colors = AssistChipDefaults.assistChipColors(
                    containerColor
                    = Color.DarkGray
                ),
                border = AssistChipDefaults.assistChipBorder(
                    borderColor =
                    Color.Black
                )
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectedCategoriesChips(
    selectedCategories: Set<String>,
    onCategoryRemoved: (String) -> Unit
) {
    Text(
        "Selected categories:", style =
        MaterialTheme.typography.titleMedium
    )
    Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
        selectedCategories.forEach { selectedCategory ->
            InputChip(
                selected = true,
                onClick = { },
                label = { Text(selectedCategory) },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.AddCircle,
                        contentDescription = "Deselect",
                        modifier = Modifier
                            .clickable {
                                onCategoryRemoved(selectedCategory)
                            }
                            .size(18.dp)
                    )
                },
                modifier = Modifier.padding(end = 8.dp),
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryChips(
    categories: List<String>,
    selectedCategories: Set<String>,
    onCategorySelected: (String) -> Unit
) {
    Text("Choose categories:", style = MaterialTheme.typography.titleMedium)
    Spacer(modifier = Modifier.height(8.dp))
    Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
        categories.forEach { category ->
            FilterChip(
                selected = selectedCategories.contains(category),
                onClick = { onCategorySelected(category) },
                label = { Text(category) },
                modifier = Modifier.padding(end = 8.dp)
            )
        }
    }
}

@Composable
fun SuggestionChips(
    suggestions: List<String>,
    selectedCategories: Set<String>,
    onSuggestionSelected: (String) -> Unit
) {
    Text("Suggestions:", style = MaterialTheme.typography.titleMedium)
    Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
        suggestions.forEach { suggestion ->
            val isSelected = selectedCategories.contains(suggestion)
            val chipColors = if (isSelected) {
                SuggestionChipDefaults.suggestionChipColors(
                    containerColor = Color.LightGray
                )
            } else {
                SuggestionChipDefaults.suggestionChipColors()
            }
            SuggestionChip(
                onClick = { onSuggestionSelected(suggestion) },
                label = { Text(suggestion) },
                colors = chipColors,
                modifier = Modifier.padding(end = 8.dp)
            )
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun GreetingPreview() {
//    Bai1()
//    bai2()
    Bai3()

}