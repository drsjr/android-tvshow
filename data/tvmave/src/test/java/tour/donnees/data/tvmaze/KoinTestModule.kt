package tour.donnees.data.tvmaze

import org.junit.Test
import org.koin.test.check.checkModules
import tour.donnees.data.tvmaze.di.TvShowDataModule
import tour.donnees.data.tvmaze.network.NetworkModule

class KoinTestModule {
    @Test
    fun `Check with has all modules in Data Layer`() {
       checkModules {
           modules(NetworkModule, TvShowDataModule)
       }
    }
}