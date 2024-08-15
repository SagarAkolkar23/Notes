package com.example.note.ui.theme

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class entity(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val text : String
)