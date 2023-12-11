package com.example.todopr.ui.presentance.component.mainScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.todopr.domain.data.Notes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListCard(
    onItemClick : () ->Unit,
    onCardDelete : ()->Unit,
    notes: Notes
) {

    var showDialog by remember {
        mutableStateOf(false)
    }

    if(showDialog){
        NoteDialog(
            title ="Delete",
            body ="Are you sure want to delete patient" +
                    "\"${notes.name}\" from note list",
            onDialogDismiss = { showDialog=false },
            onConfirmButtonClicked = {onCardDelete()
            showDialog=false}
        )
    }

    Card(
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp,
            pressedElevation = 4.dp,
            focusedElevation = 2.dp
        ),
        modifier = Modifier.padding(8.dp),
        onClick = {onItemClick()}){
        Row (modifier = Modifier
            .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically){
            Column(modifier = Modifier
                .weight(2f)) {
                Text(text = notes.name,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis)

                Spacer(modifier = Modifier.height(8.dp))

                Text(text = notes.doctorAssigned,
                    style = MaterialTheme.typography.labelLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis)

            }
           IconButton(onClick = { showDialog=true }) {
               Icon(imageVector = Icons.Default.Delete,
                   contentDescription = "delete",
                   modifier = Modifier.weight(1f))
           }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun Preview() {
    ListCard(onItemClick = { /*TODO*/ }, notes = Notes("asraf","22",2,"mohamed","some",null), onCardDelete = {})
}