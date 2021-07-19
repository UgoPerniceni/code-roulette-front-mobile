package fr.esgi.projet_annuel_4_eme_annee_front_android.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import fr.esgi.projet_annuel_4_eme_annee_front_android.R

class SettingFragment : Fragment(), View.OnClickListener {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.fragment_setting_button).setOnClickListener(this)
    }

    private fun logout() {
        findNavController().navigate(R.id.action_navigation_setting_to_navigation_login)
    }

    override fun onClick(v: View?) {
        when(view?.id){
            R.id.fragment_setting_button -> {
                logout()
            }
        }
    }
}