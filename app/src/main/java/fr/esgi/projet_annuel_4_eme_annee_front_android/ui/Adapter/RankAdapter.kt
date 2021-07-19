package fr.esgi.projet_annuel_4_eme_annee_front_android.ui.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import fr.esgi.projet_annuel_4_eme_annee_front_android.R
import fr.esgi.projet_annuel_4_eme_annee_front_android.ui.ViewHolder.RankViewHolder
import fr.esgi.projet_annuel_4_eme_annee_front_android.ui.model.User

class RankAdapter(private val users:List<User>) : RecyclerView.Adapter<RankViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankViewHolder{
        val inflater = LayoutInflater.from(parent.context)
        return RankViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: RankViewHolder, position: Int)
    {
        val user: User = users[position]
        user.position = position + 1

        holder.bind(user)

        holder.itemView.setOnClickListener { view ->
            val bundle = bundleOf(
                "position" to user.position,
                "userName" to user.userName,
                "elo" to user.elo,
                "gamesWon" to user.gamesWon,
                "gamesPlayed" to user.gamesPlayed,
            )

            view.findNavController().navigate(R.id.action_navigation_ranking_to_navigation_rank_user, bundle)
        }
    }

    override fun getItemCount(): Int = users.size
}
