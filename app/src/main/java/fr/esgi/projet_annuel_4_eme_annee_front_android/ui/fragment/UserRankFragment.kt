package fr.esgi.projet_annuel_4_eme_annee_front_android.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import fr.esgi.projet_annuel_4_eme_annee_front_android.R
import org.eazegraph.lib.charts.PieChart
import org.eazegraph.lib.models.PieModel


class UserRankFragment : Fragment(), View.OnClickListener {

    private var textViewPosition: TextView? = null
    private var textViewUsername: TextView? = null
    private var textViewGamesPlayed: TextView? = null
    private var textViewGamesWon: TextView? = null
    private var textViewGamesLost: TextView? = null
    private var textViewWinRate: TextView? = null

    private var pieChart: PieChart? = null

    private var gamesWon: Int? = 0
    private var gamesLost: Int? = 0
    private var gamesPlayed: Int? = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_user_rank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pieChart = view.findViewById(R.id.piechart);

        textViewPosition = view.findViewById(R.id.user_rank_fragment_position)
        textViewUsername = view.findViewById(R.id.user_rank_fragment_username)
        textViewGamesPlayed = view.findViewById(R.id.user_rank_fragment_gamesPlayed)
        textViewGamesWon = view.findViewById(R.id.user_rank_fragment_gamesWon)
        textViewGamesLost = view.findViewById(R.id.user_rank_fragment_gamesLost)

        textViewWinRate = view.findViewById(R.id.user_rank_fragment_win_rate)

        view.findViewById<Button>(R.id.user_rank_fragment_return).setOnClickListener(this)

        textViewPosition?.apply {
            this.text = arguments?.getInt("position").toString()
        }
        textViewUsername?.apply {
            this.text = arguments?.getString("userName")
        }
        textViewGamesPlayed?.apply {
            gamesPlayed = arguments?.getInt("gamesPlayed")
            this.text = gamesPlayed.toString()
        }
        textViewGamesWon?.apply {
            gamesWon = arguments?.getInt("gamesWon")
            this.text = gamesWon.toString()
        }
        textViewGamesLost?.apply {
            gamesLost = gamesWon?.let { gamesPlayed?.minus(it) }
            this.text = gamesLost.toString()
        }

        textViewWinRate?.apply {
            var wr = "0";

            try {
                wr = (gamesPlayed?.let { gamesWon?.div(it) })?.times(100).toString()
                this.text = "$wr %"
            } catch (e : ArithmeticException){}
            finally {
                this.text = "$wr %"
            }
        }

        setData()
    }

    private fun setData() {
        // Set the data and color to the pie chart

        gamesWon?.let {
            pieChart?.addPieSlice(PieModel("Win", it.toFloat(), Color.parseColor("#EF5350")))
        }

        gamesPlayed?.let {
            pieChart?.addPieSlice(PieModel("Loose", it.toFloat(), Color.parseColor("#66BB6A")))
        }

        pieChart?.startAnimation()
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.user_rank_fragment_return -> {
                findNavController().navigate(R.id.action_navigation_rank_user_to_navigation_ranking)
            }
        }
    }
}