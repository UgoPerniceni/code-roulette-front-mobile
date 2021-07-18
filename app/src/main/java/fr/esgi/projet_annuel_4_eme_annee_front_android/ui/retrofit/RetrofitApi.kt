package fr.esgi.projet_annuel_4_eme_annee_front_android.ui.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import fr.esgi.projet_annuel_4_eme_annee_front_android.ui.retrofit.service.AuthService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitApi {
    private const val BASE_URL: String = "http://34.253.194.75/api/"

    private val gson : Gson by lazy {
        GsonBuilder().setLenient().create()
    }

    private val httpClient : OkHttpClient by lazy {
        OkHttpClient.Builder().build()
    }

    private val retrofit : Retrofit by lazy {
        retrofit2.Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val apiAuthService : AuthService by lazy{
        retrofit.create(AuthService::class.java)
    }
}