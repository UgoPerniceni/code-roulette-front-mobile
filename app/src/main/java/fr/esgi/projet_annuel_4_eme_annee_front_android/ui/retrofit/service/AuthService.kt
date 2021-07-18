package fr.esgi.projet_annuel_4_eme_annee_front_android.ui.retrofit.service

import fr.esgi.projet_annuel_4_eme_annee_front_android.ui.model.Login
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface AuthService {
    @Headers("Content-Type: application/json")
    @POST("auth/login")
    fun login(@Body body: Login): Call<ResponseBody>
}