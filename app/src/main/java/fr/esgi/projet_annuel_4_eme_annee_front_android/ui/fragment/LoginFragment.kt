package fr.esgi.projet_annuel_4_eme_annee_front_android.ui.fragment

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import fr.esgi.projet_annuel_4_eme_annee_front_android.R
import fr.esgi.projet_annuel_4_eme_annee_front_android.ui.Preferences.AppPreferences
import fr.esgi.projet_annuel_4_eme_annee_front_android.ui.model.Login
import fr.esgi.projet_annuel_4_eme_annee_front_android.ui.retrofit.RetrofitApi
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment: Fragment(), View.OnClickListener {

    private var emailEditText: EditText? = null
    private var passwordEditText: EditText? = null

    private var loaderSpinner: ProgressBar? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewByIds(view)

        view.findViewById<Button>(R.id.login).setOnClickListener(this)
    }

    private fun setViewByIds(view: View){
        emailEditText = view.findViewById(R.id.email)
        passwordEditText = view.findViewById(R.id.password)

        loaderSpinner = view.findViewById(R.id.loader)
    }


    override fun onClick(view: View?) {
        when(view?.id){
            R.id.login -> {
                login()
            }
        }
    }

    private fun login() {
        loaderVisible()

        if(formIsValid()) {

            var email = ""
            var password = ""

            emailEditText?.let {
                email = it.text.toString()
            }

            passwordEditText?.let {
                password = it.text.toString()
            }

            val login = Login(email, password)
            Log.d("retrofit-login", login.toString())

            RetrofitApi.apiAuthService.login(Login("azerty@azerty.fr", "azerty")).enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>, ) {
                    if(response.isSuccessful) {
                        Log.d("--- LoginFragment ---", response.toString())
                        Log.d("--- LoginFragment ---", response.raw().toString())

                        val token = response.headers().get("Authorization")

                        Log.d("--- LoginFragment ---", token)

                        AppPreferences.token = token

                        findNavController().navigate(R.id.action_navigation_login_to_navigation_home)
                    } else {
                        Toast.makeText(context, "Error Occurred: ${response.message()}", Toast.LENGTH_LONG).show()
                    }
                    loaderGone()
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.d("--- LoginFragment ---", call.toString())
                    loaderGone()
                }
            })
        }

        loaderGone()
    }

    private fun formIsValid(): Boolean {
        var validateForm = true

        emailEditText?.let {
            if(TextUtils.isEmpty(it.text.toString())){
                it.error = "Required"
                validateForm = false
            }
        }

        passwordEditText?.let {
            if(TextUtils.isEmpty(it.text.toString())){
                it.error = "Required"
                validateForm = false
            }
        }

        return validateForm
    }

    fun loaderVisible() {
        loaderSpinner?.visibility = View.VISIBLE
    }

    fun loaderGone() {
        loaderSpinner?.visibility = View.GONE
    }
}