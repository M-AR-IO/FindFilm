package my.mobile.findfilm

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import my.mobile.findfilm.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    private fun handleIntent(intent: Intent) {
        if (intent.action == Intent.ACTION_SEARCH){
            val query = intent.getStringExtra(SearchManager.QUERY)
            binding.tv.text = query
        }
    }
}