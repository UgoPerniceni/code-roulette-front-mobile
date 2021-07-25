package fr.esgi.projet_annuel_4_eme_annee_front_android

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import fr.esgi.projet_annuel_4_eme_annee_front_android.databinding.ActivityMainBinding
import fr.esgi.projet_annuel_4_eme_annee_front_android.ui.Preferences.AppPreferences

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navView: BottomNavigationView

    private var loader: ProgressBar? = null
    private var fadeScreen: View? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppPreferences.setup(applicationContext)

        navView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.navigation_home, R.id.navigation_ranking, R.id.navigation_setting))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        loader = findViewById(R.id.loader_main_activity)
        fadeScreen = findViewById(R.id.fadeScreen_main_activity)

    }

    fun navViewVisible(){
        navView.visibility = View.VISIBLE
    }

    fun navViewGone(){
        navView.visibility = View.GONE
    }

    fun showLoader() {
        fadeScreen?.isVisible = true
        loader?.isVisible = true
    }

    fun hideLoader() {
        fadeScreen?.isVisible = false
        loader?.isVisible = false
    }

}