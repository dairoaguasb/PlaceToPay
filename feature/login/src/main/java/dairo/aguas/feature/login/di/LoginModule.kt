package dairo.aguas.feature.login.di

import dairo.aguas.feature.login.domain.AuthUserLocal
import dairo.aguas.feature.login.domain.SetUserLocal
import dairo.aguas.feature.login.ui.main.LoginViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Dairo Aguas B on 17/04/2020.
 */
val loginModule = module {
    factory { SetUserLocal(get()) }
    factory { AuthUserLocal(get()) }
    viewModel { LoginViewModel(get(), get(), androidContext()) }
}