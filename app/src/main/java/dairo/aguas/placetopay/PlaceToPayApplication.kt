package dairo.aguas.placetopay

import android.app.Application
import dairo.aguas.placetopay.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

/**
 * Created by Dairo Aguas B on 17/04/2020.
 */
class PlaceToPayApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        startKoin {
            androidLogger()
            androidContext(this@PlaceToPayApplication)
            modules(appComponent)
        }
    }
}