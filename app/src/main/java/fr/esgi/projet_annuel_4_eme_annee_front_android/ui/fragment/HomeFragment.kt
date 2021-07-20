package fr.esgi.projet_annuel_4_eme_annee_front_android.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.esgi.projet_annuel_4_eme_annee_front_android.R
import fr.esgi.projet_annuel_4_eme_annee_front_android.ui.Adapter.GameAdapter
import fr.esgi.projet_annuel_4_eme_annee_front_android.ui.Preferences.AppPreferences
import fr.esgi.projet_annuel_4_eme_annee_front_android.ui.model.Game
import fr.esgi.projet_annuel_4_eme_annee_front_android.ui.retrofit.RetrofitApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private var recyclerView: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycle_games)

        val gamesOfUser = mutableListOf<Game>()

        recyclerView?.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = GameAdapter(gamesOfUser)
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }

        RetrofitApi.apiGameService.getGames().enqueue(object : Callback<MutableList<Game>> {
            override fun onResponse(call: Call<MutableList<Game>>, response: Response<MutableList<Game>>) {
                if(response.isSuccessful) {
                    Log.d("--- HomeFragment ---", response.toString())
                    Log.d("--- HomeFragment ---", response.raw().toString())

                    val games = mutableListOf<Game>()
                    games.addAll(response.body())

                    val emailUserConnected = AppPreferences.email

                    games.forEach { game ->
                        game.usersInGame?.forEach { userIg ->
                            if(userIg.user?.email == emailUserConnected) {
                                gamesOfUser.add(game)
                            }
                        }
                    }

                    recyclerView?.adapter?.notifyDataSetChanged()
                } else {
                    Toast.makeText(context, "Error Occurred: ${response.message()}", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<MutableList<Game>>, t: Throwable) {
                Log.d("--- HomeFragment ---", call.toString())
            }
        })
    }
}