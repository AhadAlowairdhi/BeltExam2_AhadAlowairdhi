package com.example.beltexamahadalowairdhi

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games") // name of the table of database
data class GameEntity (
    //the 6 column in my database
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id")val id: Int = 0,// the primary key of the row
    @ColumnInfo(name = "title")val gameTitle: String,
    @ColumnInfo(name = "platform")val platform: String,
    @ColumnInfo(name = "genre")val genre: String,
    @ColumnInfo(name = "shortdescrip")val shortdescrip: String = "",
    @ColumnInfo(name = "gameImg")val thumbnail: String
)
