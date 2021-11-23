package com.example.beltexamahadalowairdhi

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [GameEntity::class],version = 1,exportSchema = false)
abstract class GameDB : RoomDatabase() {

    companion object {
        var instance: GameDB? = null
        fun getInstance(ctx: Context): GameDB {
            if (instance != null)
            {
                return instance as GameDB
            }
            instance= Room.databaseBuilder(ctx,GameDB::class.java,"uni").run {
                allowMainThreadQueries() }.build()
            return instance as GameDB
        }
    }

    abstract fun GameDao(): GameDao
}