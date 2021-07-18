package fr.esgi.projet_annuel_4_eme_annee_front_android.ui.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
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

/*        holder.itemView.setOnClickListener { view ->
            val bundle = bundleOf(
                "userName" to user.userName,
                "elo" to user.elo
            )
            // view.findNavController().navigate(R.id.action_HomeFragment_to_RecipeFragment, bundle)
        }*/
    }

    override fun getItemCount(): Int = users.size
}
