package fr.esgi.projet_annuel_4_eme_annee_front_android.ui.ViewHolder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.esgi.projet_annuel_4_eme_annee_front_android.R
import fr.esgi.projet_annuel_4_eme_annee_front_android.ui.model.User

class RankViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.rank_item, parent, false))
{
    private var position: TextView? = null
    private var userName: TextView? = null
    private var elo: TextView? = null

    init {
        position = itemView.findViewById(R.id.rank_number)
        userName = itemView.findViewById(R.id.rank_userName)
        elo = itemView.findViewById(R.id.rank_elo)
    }

    fun bind(user: User) {
        position?.text = user.position.toString()
        userName?.text = user.userName
        elo?.text = user.elo.toString()
    }
}
