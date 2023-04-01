package tour.donnees.tvmaze

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tour.donnees.catalog.databinding.ActivityCatalogBinding
import tour.donnees.catalog.view.CatalogActivity
import tour.donnees.tvmaze.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.text.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    CatalogActivity::class.java
                )
            )
        }
    }
}