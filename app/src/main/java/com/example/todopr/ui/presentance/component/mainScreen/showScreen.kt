package com.example.todopr.ui.presentance.component.mainScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todopr.domain.data.Notes
import com.example.todopr.ui.presentance.screen.NoteViewModel
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun ShowScreen(
    onAddNote : () -> Unit,
    onItemClick : (Int?) ->Unit,
    noteViewModel: NoteViewModel = hiltViewModel()
) {

    val noteItem by noteViewModel.noteList.collectAsState()


    Scaffold(
        topBar = { TopHeader()},
        floatingActionButton = {
            NoteFab(onClick = {onAddNote()})
        },
    )
    {paddingValues ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(4.dp),
            modifier = Modifier.padding(paddingValues)
        ){
            items(noteItem){
                ListCard(
                    onItemClick = {
                                 onItemClick(it.patientsId)
                    },
                    onCardDelete = {
                                   noteViewModel.deleteNote(it)
                    },
                    notes =it
                )
            }
        }
    }

    if(noteItem.isEmpty()){
        Box(modifier = Modifier
            .fillMaxSize(),
            contentAlignment = Alignment.Center){
            Text(text = "Add Notes Details" +
                    "by using add button",
                style = MaterialTheme.typography.labelMedium)
        }
    }

}