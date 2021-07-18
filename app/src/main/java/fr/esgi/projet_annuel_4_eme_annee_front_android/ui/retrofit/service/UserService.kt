package fr.esgi.projet_annuel_4_eme_annee_front_android.ui.retrofit.service

import fr.esgi.projet_annuel_4_eme_annee_front_android.ui.model.User
import retrofit2.Call
import retrofit2.http.GET

interface UserService {
    @GET("user")
    fun getUsers(): Call<MutableList<User>>
}