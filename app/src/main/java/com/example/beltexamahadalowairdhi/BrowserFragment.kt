package com.example.beltexamahadalowairdhi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BrowserFragment : Fragment() {

    val GameModel by lazy { ViewModelProvider(this).get( myViewModel ::class.java) }

    lateinit var rvBrowser: RecyclerView

    lateinit var pc: Button
    lateinit var browser: Button
    lateinit var homeBtn: Button
    lateinit var showGames: ArrayList<Game.gamesItem>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_browser, container, false)

        //init

        rvBrowser= view.findViewById(R.id.rvBrowser)
        homeBtn = view.findViewById(R.id.backBtn)
        showGames = arrayListOf()

        //viewModel
        GameModel.getListgame()

        //init UI
        browser=view.findViewById(R.id.browserBtn)
        browser.setOnClickListener {
            //show game platform browser
            requestAPI("browser")
        }

        pc=view.findViewById(R.id.pcBtn)
        pc.setOnClickListener {
            //show game platform pc
                requestAPI("pc")

        }

        //Back to main fragment
        homeBtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_browserFragment_to_mainFragment)
        }


        return view
    }


    private fun requestAPI(gameType : String){
        val apiInterface : APIInterface = APIClient().getClient()!!.create(APIInterface::class.java)
        apiInterface!!.getGames(gameType)!!.enqueue(
            object : Callback<ArrayList<Game.gamesItem>>{
                override fun onResponse(
                    call: Call<ArrayList<Game.gamesItem>>,
                    response: Response<ArrayList<Game.gamesItem>>
                ) {
                    val resource = response.body()
                    updateRv(resource!!)

                }

                override fun onFailure(call: Call<ArrayList<Game.gamesItem>>, t: Throwable) {
                    call.cancel()
                }

            }
        )

    }

    fun updateRv(gametitle : ArrayList<Game.gamesItem>){
        rvBrowser.adapter = AdapterApi(this,gametitle)
        rvBrowser.layoutManager = LinearLayoutManager(requireContext())
    }

    fun saveToLocal(showData:Game.gamesItem){
        val show = GameEntity(0,showData.title,showData.platform,showData.genre,showData.shortDescription,showData.thumbnail)
        GameModel.addgame(show)
        Toast.makeText(requireContext(), "Game Added ", Toast.LENGTH_SHORT).show()
    }


}