package com.example.nearby.ui.component.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nearby.data.model.Category

@Composable
fun NearbyCategoryFilterChipList(
    modifier: Modifier = Modifier,
    category: List<Category>,
    onSelectedCategoryChanged: (Category) -> Unit
) {
    var selectedCategoryId by remember { mutableStateOf(category.firstOrNull()?.id.orEmpty()) }

    LaunchedEffect(key1 = selectedCategoryId) {
        val selectedCategoryOrNull = category.find { it.id == selectedCategoryId }
        selectedCategoryOrNull?.let {
            onSelectedCategoryChanged(it)
        }
    }

    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = category, key = { it.id }) { category ->
            NearbyCategoryFilterChip(
                category = category,
                isSelected = category.id == selectedCategoryId,
                onClick = { isSelected ->
                    if (isSelected)
                        selectedCategoryId = category.id
                }
            )
        }
    }

}


@Preview
@Composable
private fun NearbyCategoryFilterChipListPreview() {
    NearbyCategoryFilterChipList(
        modifier = Modifier.fillMaxWidth(),
        category = listOf(
            Category(
                id = "1",
                name = "Alimentação"
            ),
            Category(
                id = "2",
                name = "Cinema"
            ),
            Category(
                id = "3",
                name = "Farmácia"
            ),
            Category(
                id = "4",
                name = "Supermercado"
            )
        ),
        onSelectedCategoryChanged = {}
    )


}