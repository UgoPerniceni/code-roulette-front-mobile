package fr.esgi.projet_annuel_4_eme_annee_front_android.ui.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.util.*

class Game(
    @SerializedName("usersInGame") var usersInGame: List<UserInGame>?,
    @SerializedName("numberOfTurn") var numberOfTurn: Int?,
    @SerializedName("timer") var timer: Int?,
    @SerializedName("gameOver") var gameOver: Boolean?,
    @SerializedName("createdAt") var dateJSON: Array<String>?,

    var position: Int?,
    var createdAtStr: String?
)