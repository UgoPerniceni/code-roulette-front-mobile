package fr.esgi.projet_annuel_4_eme_annee_front_android.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.esgi.projet_annuel_4_eme_annee_front_android.R
import fr.esgi.projet_annuel_4_eme_annee_front_android.ui.Adapter.RankAdapter
import fr.esgi.projet_annuel_4_eme_annee_front_android.ui.model.User
import fr.esgi.projet_annuel_4_eme_annee_front_android.ui.retrofit.RetrofitApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class DashboardFragment : Fragment(), View.OnClickListener {

    private var recyclerView: RecyclerView? = null
    private var returnButton: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycle_users)
        returnButton = view.findViewById(R.id.dashboard_button_return)

        val users = mutableListOf<User>()

        recyclerView?.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = RankAdapter(users)
        }

        RetrofitApi.apiUserService.getUsers().enqueue(object : Callback<MutableList<User>>{
            override fun onResponse(
                call: Call<MutableList<User>>,
                response: Response<MutableList<User>>,
            ) {
                if(response.isSuccessful) {
                    Log.d("--- DashBoardFragment ---", response.toString())
                    Log.d("--- DashBoardFragment ---", response.raw().toString())

                    users.addAll(response.body())
                    users.sortByDescending { it.elo }

                    recyclerView?.adapter?.notifyDataSetChanged()
                } else {
                    Toast.makeText(context, "Error Occurred: ${response.message()}", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<MutableList<User>>, t: Throwable) {
                Log.d("--- DashBoardFragment ---", call.toString())
            }
        })
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.dashboard_button_return -> {
                findNavController().navigate(R.id.action_navigation_dashboard_to_navigation_home)
            }
        }
    }
}