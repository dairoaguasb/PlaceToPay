package dairo.aguas.feature.main.di

import dairo.aguas.feature.main.domain.GetProductLocal
import dairo.aguas.feature.main.domain.GetUserLocal
import dairo.aguas.feature.main.ui.home.HomeViewModel
import dairo.aguas.feature.main.ui.payment.PaymentViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Dairo Aguas B on 18/04/2020.
 */
val mainModule = module {
    factory { GetUserLocal(get()) }
    factory { GetProductLocal(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { PaymentViewModel(get()) }
}