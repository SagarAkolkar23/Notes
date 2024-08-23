package com.example.note.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Delete
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class noteview(private val dao: NoteDAO) :ViewModel(){
    val note : Flow<List<entity>> = dao.getNotes()


    fun Deleteid(ID : Int) {
        viewModelScope.launch {
            dao.Deleteid(ID)
        }
    }

}