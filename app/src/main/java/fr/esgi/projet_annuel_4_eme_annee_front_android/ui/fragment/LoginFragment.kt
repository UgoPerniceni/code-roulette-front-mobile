package fr.esgi.projet_annuel_4_eme_annee_front_android.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import fr.esgi.projet_annuel_4_eme_annee_front_android.R
import fr.esgi.projet_annuel_4_eme_annee_front_android.ui.model.Login
import fr.esgi.projet_annuel_4_eme_annee_front_android.ui.retrofit.service.AuthService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginFragment: Fragment(), View.OnClickListener {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.login).setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.login -> {
                login("azerty@azerty.fr", "azerty")
            }
        }
    }

    private fun login(email: String, password: String) {
        val login = Login(email, password);

        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(AuthService::class.java)

        val call = service.login(login).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>,
            ) {
                Log.d("retrofit-success", response.toString())
                Log.d("retrofit-success", response.raw().toString())
                Log.d("retrofit-success", response.headers().get("Authorization"))
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("retrofit-failure", call.toString())
            }
        })
    }

    companion object {
        var BaseUrl = "http://34.253.194.75/api/"
    }
}