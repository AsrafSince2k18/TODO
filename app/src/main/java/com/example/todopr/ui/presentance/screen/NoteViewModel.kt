package com.example.todopr.ui.presentance.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todopr.domain.data.Notes
import com.example.todopr.domain.repository.Repository
import com.example.todopr.ui.presentance.statesAndEvent.NoteEvent
import com.example.todopr.ui.presentance.statesAndEvent.NotesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: Repository,
    private val savedStateHandle: SavedStateHandle) :
    ViewModel() {

    var noteState by mutableStateOf(NotesState())

    private val _notesList = MutableStateFlow<List<Notes>>(emptyList())
    val noteList = _notesList

    var currentId : Int?=null

    private val _uiEvent = MutableSharedFlow<UIEvent>()
    val uiEvent = _uiEvent.asSharedFlow()
    init {
        getAllNote()

    }

    init {
        fetchScreen()

    }

    fun getAllNote(){
        viewModelScope.launch {
            repository.getAllNote().collect{
                noteList.value=it
            }
        }
    }

    fun deleteNote(note : Notes){
        viewModelScope.launch {
            repository.deleteNote(notes = note)
        }
    }

        fun noteEvent(event : NoteEvent){
            when(event){
                is NoteEvent.EnterName -> {
                    noteState = noteState.copy(name = event.name)
                }
                is NoteEvent.EnterAge -> {
                    noteState = noteState.copy(age = event.age)
                }
                is NoteEvent.EnterAssignedDoctor -> {
                    noteState = noteState.copy(assignedDoctor = event.assignedDoctor)
                }

                is NoteEvent.EnterPrescription -> {
                    noteState = noteState.copy(prescription = event.prescription)
                }
                NoteEvent.GenderFemale -> {
                    noteState = noteState.copy(gender =2)
                }
                NoteEvent.GenderMale -> {
                    noteState = noteState.copy(gender = 1)
                }
                NoteEvent.SaveButton -> {
                    viewModelScope.launch {
                        try {
                            saveNote()
                            _uiEvent.emit(UIEvent.SaveButton)
                        }catch (e:Exception){
                            _uiEvent.emit(UIEvent.ShowToast(
                                toast = "Could not save ${e.message}"
                            ))
                        }
                    }
                }
            }
        }

    fun saveNote(){

        val age = noteState.age.toIntOrNull()
        when{
            noteState.name.isEmpty() -> throw TextFiledException("Enter the name")
            noteState.assignedDoctor.isEmpty() -> throw TextFiledException("Enter the doctorAssigned")
            noteState.prescription.isEmpty() -> throw TextFiledException("Enter the prescription")
            noteState.gender == 0 -> throw TextFiledException("Enter the gender")
            age == null || age <= 0 || age>=100 -> throw TextFiledException("Enter the correct format age")
        }
        val trimmedName = noteState.name.trim()
        val trimmedPreciption = noteState.prescription.trim()
        viewModelScope.launch {
            repository.addNote(notes = Notes(
                name = trimmedName,
                age = noteState.age,
                gender = noteState.gender,
                doctorAssigned = noteState.assignedDoctor,
                prescripton = trimmedPreciption,
                patientsId = currentId
            ))
        }
    }


    fun fetchScreen(){
            savedStateHandle.get<Int>(key ="noteId")?.let {noteId->
                if(noteId!=-1){
                    viewModelScope.launch {
                        repository.getNote(id = noteId)?.apply {
                          noteState= noteState.copy(
                               name = name,
                               age = age,
                               gender = gender,
                               assignedDoctor = doctorAssigned,
                               prescription = prescripton
                           )
                            currentId = noteId
                        }
                }
            }
        }
    }

    sealed class UIEvent(){
        data class ShowToast(val toast : String) : UIEvent()
        object SaveButton : UIEvent()
    }


}

class TextFiledException(message : String) : Exception(message)