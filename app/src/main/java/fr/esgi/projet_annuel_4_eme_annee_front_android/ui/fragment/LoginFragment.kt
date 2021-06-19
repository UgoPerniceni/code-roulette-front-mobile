package fr.esgi.projet_annuel_4_eme_annee_front_android.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import fr.esgi.projet_annuel_4_eme_annee_front_android.databinding.FragmentLoginBinding
import fr.esgi.projet_annuel_4_eme_annee_front_android.ui.viewModel.Login

class LoginFragment: Fragment() {
    private lateinit var login: Login
    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        login =
            ViewModelProvider(this).get(Login::class.java)

        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}