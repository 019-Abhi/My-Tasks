package com.example.mytasks.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Label
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mytasks.data.local.entity.CategoryEntity
import com.example.mytasks.ui.theme.*
import com.example.mytasks.ui.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onCategoryClick: (CategoryEntity) -> Unit,
    onViewAllClick: () -> Unit,
    onLongTermGoalsClick: () -> Unit
) {
    val categories by viewModel.shortTermCategories.collectAsStateWithLifecycle(initialValue = emptyList())
    val scrollState = rememberScrollState()
    val isDark = isSystemInDarkTheme()

    // Dynamically choose a beautiful, non-neon premium gradient for the Long-term goals box
    val goalGradientColors = if (isDark) {
        listOf(
            Color(0xFF1E293B), // Deep slate matching your container dark
            Color(0xFF2E3A52)  // Slightly lighter slate blue for a premium subtle sheen
        )
    } else {
        listOf(
            MaterialTheme.colorScheme.primary,   // Primary Light (0xFF5D5FEF)
            MaterialTheme.colorScheme.secondary // Secondary Light for a smooth rich blend
        )
    }

    // Dynamic text/icon color for inside the long-term goals card
    val goalContentColor = if (isDark) MaterialTheme.colorScheme.onBackground else Color.White

    Scaffold(
        topBar = {
            Column {
                Spacer(modifier = Modifier.height(20.dp))
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "My Tasks",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.Transparent
                    )
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = innerPadding.calculateTopPadding() + 12.dp,
                    bottom = innerPadding.calculateBottomPadding() + 8.dp
                )
                .padding(horizontal = 20.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Focus Areas",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 800.dp),
                userScrollEnabled = false
            ) {
                items(categories) { category ->
                    CategoryCard(
                        name = category.name,
                        icon = Icons.AutoMirrored.Rounded.Label,
                        color = getCategoryColor(category.name)
                    ) {
                        onCategoryClick(category)
                    }
                }

                item(span = { GridItemSpan(if (categories.size % 2 == 0) 2 else 1) }) {
                    CategoryCard(
                        name = "View All",
                        icon = Icons.Rounded.GridView,
                        color = MaterialTheme.colorScheme.secondary,
                        isFullWidth = categories.size % 2 == 0
                    ) {
                        onViewAllClick()
                    }
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Growth",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(Brush.horizontalGradient(colors = goalGradientColors))
                    .clickable { onLongTermGoalsClick() },
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(20.dp)
                ) {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = if (isDark) {
                            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                        } else {
                            Color.White.copy(alpha = 0.2f)
                        },
                        modifier = Modifier.size(48.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Rounded.AutoGraph,
                                contentDescription = null,
                                modifier = Modifier.size(28.dp),
                                tint = if (isDark) MaterialTheme.colorScheme.primary else Color.White
                            )
                        }
                    }
                    Column {
                        Text(
                            text = "Long-term Goals",
                            style = MaterialTheme.typography.titleMedium,
                            color = goalContentColor,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Visualize your future",
                            style = MaterialTheme.typography.bodySmall,
                            color = goalContentColor.copy(alpha = 0.7f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryCard(
    name: String,
    icon: ImageVector,
    color: Color,
    isFullWidth: Boolean = false,
    onClick: () -> Unit
) {
    val isDark = isSystemInDarkTheme()
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .then(if (isFullWidth) Modifier.height(72.dp) else Modifier.aspectRatio(1.4f))
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = color.copy(alpha = if (isDark) 0.15f else 0.12f) // Slightly dropped dark alpha for cleaner look
        )
    ) {
        if (isFullWidth) {
            Row(
                modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = name,
                    modifier = Modifier.size(24.dp),
                    tint = color
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = color
                )
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = color.copy(alpha = 0.15f),
                    modifier = Modifier.size(40.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = icon,
                            contentDescription = name,
                            modifier = Modifier.size(20.dp),
                            tint = color
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = name,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = color,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}