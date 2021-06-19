package fr.esgi.projet_annuel_4_eme_annee_front_android.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import fr.esgi.projet_annuel_4_eme_annee_front_android.databinding.FragmentNotificationsBinding
import fr.esgi.projet_annuel_4_eme_annee_front_android.ui.viewModel.Notifications

class NotificationsFragment : Fragment() {

    private lateinit var notifications: Notifications
    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        notifications =
                ViewModelProvider(this).get(Notifications::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        notifications.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}