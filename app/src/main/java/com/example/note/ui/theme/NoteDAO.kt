package com.example.note.ui.theme

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import java.net.IDN

@Dao
interface NoteDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun NewNote(entity: entity)

    @Delete
    suspend fun DeleteNote(entity: entity)

    @Query("select * from entity")
    fun getNotes() : Flow<List<entity>>
}