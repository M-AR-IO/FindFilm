package my.mobile.findfilm

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import my.mobile.findfilm.databinding.ActivityMainBinding
import my.mobile.findfilm.ui.login.LoginActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(applicationContext,LoginActivity::class.java)
        startActivity(intent)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)

//        setWindowFlag(this,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,false)
//        window.statusBarColor = resources.getColor(R.color.colorPrimary)

        setSupportActionBar(binding.toolbar)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_tv, R.id.navigation_favorit
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val username = getIntent().getStringExtra("username")?: "Human"

        binding.toolbar.title = "Hello, " + username
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.toolbar_home_menu,menu)
//        val cari: SearchView = MenuItemCompat.getActionView(menu?.findItem(R.id.cari)) as SearchView
//
//        val searchManager: SearchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
//        cari.setSearchableInfo(searchManager.getSearchableInfo(ComponentName(this,SearchActivity::class.java)))
//        cari.queryHint = resources.getString(R.string.label_cari)
//        return true
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId){
//            R.id.cari -> {
//
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }
//    private fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
//        val winParams: WindowManager.LayoutParams = activity.window.attributes
//        if (on) {
//            winParams.flags = winParams.flags or bits
//        } else {
//            winParams.flags = winParams.flags and bits.inv()
//        }
//        window.attributes = winParams
//    }
}