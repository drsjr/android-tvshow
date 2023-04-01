package tour.donnees.tvmaze

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import tour.donnees.catalog.di.CatalogFeatureModule

class TvMazeApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@TvMazeApplication)
            modules(
                listOf(
                    CatalogFeatureModule
                )
            )
        }
    }
}