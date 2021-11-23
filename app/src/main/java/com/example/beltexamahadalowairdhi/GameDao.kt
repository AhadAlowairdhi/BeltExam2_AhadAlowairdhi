package com.example.beltexamahadalowairdhi

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GameDao {
    @Query("SELECT * FROM games")
    fun getAllgames(): LiveData<List<GameEntity>>

    @Insert
    fun insertGame(game: GameEntity)

    @Delete
    fun deleteGame(game: GameEntity)

    @Update
    fun updtGame(game: GameEntity)
}