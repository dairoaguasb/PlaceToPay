package dairo.aguas.feature.payment.di

import dairo.aguas.feature.payment.domain.SetCreditCardLocal
import dairo.aguas.feature.payment.ui.main.PaymentMethodViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Dairo Aguas B on 19/04/2020.
 */
val paymentModule = module {
    factory { SetCreditCardLocal(get()) }
    viewModel { PaymentMethodViewModel(get(), androidContext()) }
}