package com.example.beltexamahadalowairdhi

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class myViewModel(activity: Application) : AndroidViewModel(activity){
    private val gameshow : LiveData<List<GameEntity>>

    // object
    val objUnidb = GameDB.getInstance(activity).GameDao()

    init {
        gameshow = objUnidb.getAllgames()
    }

    fun getListgame(): LiveData<List<GameEntity>> {
        return gameshow
    }

    fun addgame(game : GameEntity){
        CoroutineScope(Dispatchers.IO).launch {
            objUnidb.insertGame(game)
        }
    }

    fun updtgame(game: GameEntity){
        CoroutineScope(Dispatchers.IO).launch {
            objUnidb.updtGame(game)
        }
    }

    fun delgame(game: GameEntity){
        CoroutineScope(Dispatchers.IO).launch {
            objUnidb.deleteGame(game)
        }
    }

}