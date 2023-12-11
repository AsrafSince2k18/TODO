package com.example.todopr.ui.presentance.statesAndEvent

sealed class NoteEvent{
    data class EnterName(val name : String) : NoteEvent()
    data class EnterAge(val age : String) : NoteEvent()
    data class EnterAssignedDoctor(val assignedDoctor : String) : NoteEvent()
    data class EnterPrescription(val prescription : String) : NoteEvent()

    object GenderMale : NoteEvent()
    object GenderFemale : NoteEvent()
    object SaveButton : NoteEvent()


}
