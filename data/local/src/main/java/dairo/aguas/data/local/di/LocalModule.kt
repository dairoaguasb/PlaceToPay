package dairo.aguas.data.local.di

import dairo.aguas.data.local.PlaceToPayDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by Dairo Aguas B on 18/04/2020.
 */
val localModule = module {
    single { PlaceToPayDatabase.buildDatabase(androidContext()) }
    factory { (get() as PlaceToPayDatabase).userDao() }
    factory { (get() as PlaceToPayDatabase).productDao() }
    factory { (get() as PlaceToPayDatabase).creditCardDao() }
}