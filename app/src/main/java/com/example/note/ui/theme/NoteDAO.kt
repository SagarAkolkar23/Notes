package com.example.note.ui.theme

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.net.IDN

@Dao
interface NoteDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun NewNote(entity: entity)

    @Delete
    suspend fun DeleteNote(entity: entity)

    @Query("select * from entity")
    suspend fun getNotes() : List<entity>
}