package fr.esgi.projet_annuel_4_eme_annee_front_android.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Login: ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is login Fragment"
    }
    val text: LiveData<String> = _text
}