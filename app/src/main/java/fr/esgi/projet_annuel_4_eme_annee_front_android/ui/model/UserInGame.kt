package fr.esgi.projet_annuel_4_eme_annee_front_android.ui.model

import com.google.gson.annotations.SerializedName

class UserInGame(
    @SerializedName("user") var user: User?,
    @SerializedName("turn") var turn: Int?,
    @SerializedName("current") var current: Boolean?,
    @SerializedName("won") var won: Boolean?
)