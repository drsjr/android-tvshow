package tour.donnees.catalog.view

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.MenuItemCompat
import org.koin.androidx.viewmodel.ext.android.viewModel
import tour.donnees.catalog.R
import tour.donnees.catalog.databinding.ActivityCatalogBinding
import tour.donnees.catalog.viewmodel.CatalogViewModel

class CatalogActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCatalogBinding
    private val viewModel by viewModel<CatalogViewModel>()

    private var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatalogBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val searchItem: MenuItem? = menu?.findItem(R.id.action_search)
        searchItem?.apply {
            searchView = this.actionView as SearchView
            searchView?.apply {
                setOnCloseListener(onCloseListener())

                setOnQueryTextListener(onQueryTextListener())

                setSearchableInfo((getSystemService(Context.SEARCH_SERVICE) as SearchManager)
                    .getSearchableInfo(componentName))

                findViewById<EditText>(androidx.appcompat.R.id.search_src_text).apply {
                    hint = getString(R.string.item_menu_search)
                }
            }
        }

        return true
    }

    private fun onCloseListener(): SearchView.OnCloseListener {
        return SearchView.OnCloseListener {
            viewModel.loadMoreTvShow()
            searchView?.onActionViewCollapsed()
            true
        }
    }

    private fun onQueryTextListener(): SearchView.OnQueryTextListener {
        return object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.getTvShowBySearch(newText)
                return false
            }
        }
    }

    fun isIconified() = searchView?.isIconified ?: false

}

