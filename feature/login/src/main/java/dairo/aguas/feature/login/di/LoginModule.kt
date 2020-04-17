package dairo.aguas.feature.login.di

import dairo.aguas.feature.login.ui.main.LoginViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Dairo Aguas B on 17/04/2020.
 */
val loginModule = module {
    viewModel { LoginViewModel() }
}