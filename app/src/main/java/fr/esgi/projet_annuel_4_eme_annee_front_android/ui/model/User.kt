package fr.esgi.projet_annuel_4_eme_annee_front_android.ui.model

import com.google.gson.annotations.SerializedName

class User(
    var position: Int?,

    @SerializedName("id") var id: String?,
    @SerializedName("email") var email: String?,
    @SerializedName("userName") var userName: String?,
    @SerializedName("firstName") var firstName: String?,
    @SerializedName("lastName") var lastName: String?,
    @SerializedName("elo") var elo: Int?,
    @SerializedName("gamesPlayed") var gamesPlayed: Int?,
    @SerializedName("gamesWon") var gamesWon: Int?
)

data class UserResponse (
    @SerializedName("userName") var userName: String? = null
)