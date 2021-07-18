package fr.esgi.projet_annuel_4_eme_annee_front_android.ui.retrofit.interceptor

import android.util.Log
import fr.esgi.projet_annuel_4_eme_annee_front_android.ui.Preferences.AppPreferences
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {

        Log.d("--- HeaderInterceptor ---", "interceptor")

        val request = chain.request()

        if (request.header("No-Authentication") != null) {
            Log.d("--- HeaderInterceptor ---", "token no auth")
            return chain.proceed(request)
        }

        val token: String? = AppPreferences.token

        if (token == null && token == "") {
            Log.d("--- HeaderInterceptor ---", "token null")
            return chain.proceed(request)
        }

        Log.d("--- HeaderInterceptor ---", "add header Authorization : $token")

        proceed(
            request()
                .newBuilder()
                .addHeader("Authorization", token)
                .addHeader("Content-Type", "application/json")
                .build()
        )
    }
}