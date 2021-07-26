package fr.esgi.projet_annuel_4_eme_annee_front_android.ui.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import fr.esgi.projet_annuel_4_eme_annee_front_android.ui.retrofit.interceptor.HeaderInterceptor
import fr.esgi.projet_annuel_4_eme_annee_front_android.ui.retrofit.service.AuthService
import fr.esgi.projet_annuel_4_eme_annee_front_android.ui.retrofit.service.GameService
import fr.esgi.projet_annuel_4_eme_annee_front_android.ui.retrofit.service.UserService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitApi {
    private const val BASE_URL: String = "http://34.248.4.98/api/"

    private val gson : Gson by lazy {
        GsonBuilder().setLenient().create()
    }

    private val httpClient : OkHttpClient by lazy {
        val headerInterceptor = HeaderInterceptor()

        OkHttpClient
            .Builder()
            .addInterceptor(headerInterceptor)
            .build()
    }

    private val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val apiAuthService : AuthService by lazy{
        retrofit.create(AuthService::class.java)
    }

    val apiUserService : UserService by lazy{
        retrofit.create(UserService::class.java)
    }

    val apiGameService : GameService by lazy{
        retrofit.create(GameService::class.java)
    }
}