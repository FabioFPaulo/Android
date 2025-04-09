package com.fabiofpaulo.todolist.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.fabiofpaulo.todolist.enums.TodoStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoTabs(
    onQuery: (query: String) -> Unit,
    modifier: Modifier = Modifier,
    actions: List<@Composable (() -> Unit)>,
    content: @Composable ((indexSelected: Int) -> Unit)
) {
    var titleSelected by remember { mutableIntStateOf(0) }
    val titles = listOf("All") + TodoStatus.entries.map { it.name }

    var query by remember { mutableStateOf("") }

    PrimaryScrollableTabRow(
        modifier = modifier,
        selectedTabIndex = titleSelected,
        edgePadding = 0.dp,
        tabs = {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = titleSelected == index,
                    onClick = { titleSelected = index },
                    text = { Text(title, maxLines = 2, overflow = TextOverflow.Ellipsis) })
            }
        }
    )

    Column(
        modifier = Modifier.padding(top = 30.dp, bottom = 10.dp)
    ) {
        TextField(
            value = query,
            onValueChange = {
                query = it
                onQuery(it)
            },
            label = { Text("Search") },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            },
            trailingIcon = {
                if (!query.isEmpty()) {
                    IconButton(onClick = {
                        query = ""
                        onQuery("")
                    }) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = null)
                    }
                }
            }


        )
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            actions.forEach {
                it()
            }
        }
        Text(
            "List Data",
            color = MaterialTheme.colorScheme.outline,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold
        )
        content(titleSelected)
    }

}