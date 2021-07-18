package fr.esgi.projet_annuel_4_eme_annee_front_android.ui.Preferences

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.core.content.edit

object AppPreferences {
    private var sharedPreferences: SharedPreferences? = null

    fun setup(context: Context) {
        sharedPreferences = context.getSharedPreferences("CodeRoulette.sharedPrefs", MODE_PRIVATE)
    }

    var token: String?
        get() = Key.TOKEN.getString()
        set(value) = Key.TOKEN.setString(value)

    private enum class Key {
        TOKEN;

        fun getString(): String? = if (sharedPreferences!!.contains(name)) sharedPreferences!!.getString(name, "") else null
        fun setString(value: String?) = value?.let { sharedPreferences!!.edit { putString(name, value) } } ?: remove()

        fun remove() = sharedPreferences!!.edit { remove(name) }
    }
}