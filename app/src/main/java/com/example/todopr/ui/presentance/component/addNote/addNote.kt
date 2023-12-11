package com.example.todopr.ui.presentance.component.addNote

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todopr.domain.data.Notes
import com.example.todopr.ui.presentance.screen.NoteViewModel
import com.example.todopr.ui.presentance.statesAndEvent.NoteEvent
import com.example.todopr.ui.presentance.statesAndEvent.NotesState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddNote(
    onBackClick :() ->Unit,
    onSuccess : () -> Unit,
    noteViewModel: NoteViewModel = hiltViewModel()
) {

    val state = noteViewModel.noteState

    val focusRequest = remember {
        FocusRequester()
    }
    val focusManger = LocalFocusManager.current

    LaunchedEffect(key1 = Unit){
        delay(500)
        focusRequest.requestFocus()
    }

    val context = LocalContext.current

    LaunchedEffect(key1 = Unit){
        noteViewModel.uiEvent.collectLatest {event ->
        when(event){
            NoteViewModel.UIEvent.SaveButton -> {
                onSuccess()
                Toast.makeText(context, "Saved success", Toast.LENGTH_SHORT).show()

            }
            is NoteViewModel.UIEvent.ShowToast -> {
                Toast.makeText(context, event.toast, Toast.LENGTH_SHORT).show()
            }
        }

        }
    }

    Scaffold (
        topBar = {
            NoteDetailTopBar(onBackClick)
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp)
            ) {
                OutlinedTextField(
                    value = state.name,
                    onValueChange = {name->
                        noteViewModel.noteEvent(event = NoteEvent.EnterName(name= name))
                    },
                    textStyle = MaterialTheme.typography.labelLarge,
                    label = { Text(text = "Enter the name") },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {focusManger.moveFocus(FocusDirection.Next)}
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(4))
                        .focusRequester(focusRequest)

                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    OutlinedTextField(
                        modifier = Modifier
                            .weight(1f),
                        value =state.age,
                        onValueChange ={age->
                            noteViewModel.noteEvent(event = NoteEvent.EnterAge(age))
                        },
                        label = { Text(text = "Age")},
                        textStyle = MaterialTheme.typography.labelMedium,
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {focusManger.moveFocus(FocusDirection.Next)}
                        )
                    )
                    RadioGroup(
                        onClick = {
                            noteViewModel.noteEvent(event = NoteEvent.GenderMale)
                        },
                        text = "Male",
                        selected = state.gender == 1,
                        modifier = Modifier.weight(1f)
                    )
                    RadioGroup(
                        onClick = {
                                  noteViewModel.noteEvent(event = NoteEvent.GenderFemale)
                        },
                        text = "Female",
                        selected = state.gender == 2,
                        modifier = Modifier.weight(1f)
                    )

                }

                OutlinedTextField(
                    value = state.assignedDoctor,
                    onValueChange = {assignedDoctor->
                                    noteViewModel.noteEvent(event = NoteEvent.EnterAssignedDoctor(assignedDoctor))
                    },
                    textStyle = MaterialTheme.typography.labelLarge,
                    label = { Text(text = "Assigned Doctor's name") },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {focusManger.moveFocus(focusDirection = FocusDirection.Next)}
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(4))

                )

                OutlinedTextField(
                    value = state.prescription,
                    onValueChange = { presction ->
                                    noteViewModel.noteEvent(event = NoteEvent.EnterPrescription(prescription = presction))
                    },
                    textStyle = MaterialTheme.typography.labelLarge,
                    label = { Text(text = "Prescription") },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {focusManger.moveFocus(FocusDirection.Next)}
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)

                )

                Button(
                    onClick = { noteViewModel.noteEvent(event = NoteEvent.SaveButton) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Save",
                        style = MaterialTheme.typography.labelMedium
                    )
                }

            }

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailTopBar(onBackClick: () -> Unit) {

            TopAppBar(title = {
                Text(text = "Add Note",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold)
                        },
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(imageVector = Icons.Default.ArrowBack,
                            contentDescription = "back")
                    }
                }
                )

}


@Composable
fun RadioGroup(
    modifier: Modifier = Modifier,
    onClick :() ->Unit,
    text : String,
    selected : Boolean
) {

    Row (modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center){
        RadioButton(
            selected =selected,
            onClick = { onClick() },

            )
        Text(text = text,
            style = MaterialTheme.typography.labelMedium)
    }


}

