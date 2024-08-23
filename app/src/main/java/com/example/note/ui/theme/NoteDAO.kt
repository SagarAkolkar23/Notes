package com.example.note.ui.theme

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun NewNote(entity: entity)

    @Query("select * from entity")
    fun getNotes() : Flow<List<entity>>

    @Query("DELETE from entity where id = :ID")
    suspend fun Deleteid(ID : Int)
}