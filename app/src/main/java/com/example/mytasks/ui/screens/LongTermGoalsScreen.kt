package com.example.mytasks.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.FormatStrikethrough
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mytasks.ui.utils.StrikeThroughVisualTransformation
import com.example.mytasks.ui.viewmodel.LongTermViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LongTermGoalsScreen(
    categoryName: String,
    viewModel: LongTermViewModel,
    onBackClick: () -> Unit
) {
    val currentGoal by viewModel.currentGoal.collectAsStateWithLifecycle()
    
    var textFieldValue by remember { mutableStateOf(TextFieldValue("")) }
    val visualTransformation = remember { StrikeThroughVisualTransformation() }
    val focusRequester = remember { FocusRequester() }

    // Update text field value when goal changes (but only if it's a first load or external update)
    LaunchedEffect(currentGoal) {
        if (currentGoal != null && currentGoal?.content != textFieldValue.text) {
            textFieldValue = TextFieldValue(
                text = currentGoal?.content ?: "",
                selection = TextRange(currentGoal?.content?.length ?: 0)
            )
        }
    }

    // Auto-focus the editor when the screen opens
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(categoryName, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            // Strike-through button - only formatting tool visible
            SmallFloatingActionButton(
                onClick = {
                    val start = textFieldValue.selection.start
                    val end = textFieldValue.selection.end
                    val selectedText = textFieldValue.text.substring(start, end)
                    
                    if (selectedText.isNotEmpty()) {
                        val newText = textFieldValue.text.replaceRange(
                            start,
                            end,
                            "~~$selectedText~~"
                        )
                        textFieldValue = textFieldValue.copy(
                            text = newText,
                            selection = TextRange(start, start + selectedText.length + 4)
                        )
                        viewModel.updateGoalContent(newText)
                    }
                },
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.onTertiary
            ) {
                Icon(Icons.Rounded.FormatStrikethrough, contentDescription = "Strike-through")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            TextField(
                value = textFieldValue,
                onValueChange = {
                    textFieldValue = it
                    viewModel.updateGoalContent(it.text) // Auto-save
                },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .focusRequester(focusRequester),
                placeholder = { Text("Write your $categoryName goals here...") },
                visualTransformation = visualTransformation,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    lineHeight = MaterialTheme.typography.bodyLarge.lineHeight * 1.2f
                )
            )
        }
    }
}
