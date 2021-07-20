package fr.esgi.projet_annuel_4_eme_annee_front_android.ui.model

import com.google.gson.annotations.SerializedName
import java.util.*

class Game(
    var position: Int?,

    @SerializedName("usersInGame") var usersInGame: List<UserInGame>?,
    @SerializedName("numberOfTurn") var numberOfTurn: Int?,
    @SerializedName("timer") var timer: Int?,
    @SerializedName("gameOver") var gameOver: Boolean?,

    @SerializedName("createdAt") var createdAt: Date?
)