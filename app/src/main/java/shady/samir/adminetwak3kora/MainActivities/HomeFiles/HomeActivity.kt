package shady.samir.adminetwak3kora.MainActivities.HomeFiles

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import shady.samir.adminetwak3kora.LogInSystem.AddAdminActivity
import shady.samir.adminetwak3kora.LogInSystem.AdminsActivity
import shady.samir.adminetwak3kora.LogInSystem.ChangePasswordActivity
import shady.samir.adminetwak3kora.MainActivities.MainActivity
import shady.samir.adminetwak3kora.R
import shady.samir.adminetwak3kora.SharedPreferencesData.SharedPreferencesData

class HomeActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        super.onPrepareOptionsMenu(menu)
        menu?.findItem(R.id.action_add)?.isVisible = sharedPreferencesData.isSuperAdmin()
        return true
    }
    val sharedPreferencesData = SharedPreferencesData(this)

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId
        when (id) {
            R.id.action_logout -> {
                sharedPreferencesData.signOut()
                    startActivity(
                    Intent(
                        this,
                        MainActivity::class.java
                    )
                )
            }

            R.id.action_repass -> {
                startActivity(
                    Intent(
                        this,
                        ChangePasswordActivity::class.java
                    )
                )
            }

            R.id.action_add -> {
                startActivity(
                    Intent(
                        this,
                        AdminsActivity::class.java
                    )
                )
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
