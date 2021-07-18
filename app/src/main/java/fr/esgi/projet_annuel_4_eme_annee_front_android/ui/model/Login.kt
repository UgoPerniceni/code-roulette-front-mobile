package fr.esgi.projet_annuel_4_eme_annee_front_android.ui.model

import com.google.gson.annotations.SerializedName

class Login(
    @SerializedName("email") var email: String?,
    @SerializedName("password") var password: String?
)

class LoginResponse {}
