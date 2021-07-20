package fr.esgi.projet_annuel_4_eme_annee_front_android.ui.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.esgi.projet_annuel_4_eme_annee_front_android.ui.ViewHolder.GameViewHolder
import fr.esgi.projet_annuel_4_eme_annee_front_android.ui.model.Game

class GameAdapter(private val games:List<Game>) : RecyclerView.Adapter<GameViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return GameViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int)
    {
        val game: Game = games[position]
        game.position = position + 1

        holder.bind(game)

/*        holder.itemView.setOnClickListener { view ->
            val bundle = bundleOf(
                "position" to user.position,
                "userName" to user.userName,
                "elo" to user.elo,
                "gamesWon" to user.gamesWon,
                "gamesPlayed" to user.gamesPlayed,
            )

            view.findNavController().navigate(R.id.action_navigation_ranking_to_navigation_rank_user, bundle)
        }*/
    }

    override fun getItemCount(): Int = games.size
}