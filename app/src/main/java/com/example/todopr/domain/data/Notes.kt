package com.example.todopr.domain.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todopr.utils.Utils.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class Notes(
    val name :String,
    val age : String,
    val gender : Int,
    val doctorAssigned : String,
    val prescripton : String,
    @PrimaryKey(autoGenerate = true)
    val patientsId : Int?=null

)
