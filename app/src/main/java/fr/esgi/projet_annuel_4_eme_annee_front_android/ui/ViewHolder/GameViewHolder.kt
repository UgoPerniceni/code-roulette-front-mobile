package fr.esgi.projet_annuel_4_eme_annee_front_android.ui.ViewHolder

import android.app.Activity
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import fr.esgi.projet_annuel_4_eme_annee_front_android.R
import fr.esgi.projet_annuel_4_eme_annee_front_android.ui.Preferences.AppPreferences
import fr.esgi.projet_annuel_4_eme_annee_front_android.ui.model.Game
import fr.esgi.projet_annuel_4_eme_annee_front_android.ui.model.UserInGame


class GameViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.game_item, parent, false)) {
    // private var position: TextView? = null
    private var status: TextView? = null
    private var turn: TextView? = null
    private var createdAt: TextView? = null

    private var usersSpinner: Spinner? = null

    init {
        // position = itemView.findViewById(R.id.game_number)
        status = itemView.findViewById(R.id.game_status)
        turn = itemView.findViewById(R.id.game_turn)
        createdAt = itemView.findViewById(R.id.game_created_at)

        usersSpinner = itemView.findViewById(R.id.game_users_spinner)
    }

    fun bind(game: Game) {
        turn?.text = game.timer.toString() + "s / " + game.numberOfTurn.toString() + " turn(s)"

        if(game.gameOver == true) {
            var won = false;

            game.usersInGame?.forEach { userIg ->
                if(userIg.user?.email == AppPreferences.email && userIg.won == true) {
                    won = true;
                }
            }

            if(won) {
                status?.text = "Won"

                itemView.setBackgroundResource(R.color.Win)
            } else {
                status?.text = "Lost"

                itemView.setBackgroundResource(R.color.Loose)
            }
        } else {
            status?.text = "In progress"

            itemView.setBackgroundResource(R.color.Progress)
        }

        game.createdAtStr?.let {  createdAt?.text = "Created at : ".plus(it) }

        game.usersInGame?.let {
            val usernames = mutableListOf<String>()

            for (userIg: UserInGame in it) {
                userIg.user?.userName?.let { it1 -> usernames.add(it1) }

                val adapter = ArrayAdapter(this.itemView.context as Activity, android.R.layout.simple_spinner_item, usernames)
                usersSpinner?.adapter = adapter
            }
        }

        // turn?.text = game.position.toString()
    }
}