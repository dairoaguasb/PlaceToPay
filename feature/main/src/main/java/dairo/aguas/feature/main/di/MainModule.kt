package dairo.aguas.feature.main.di

import dairo.aguas.feature.main.domain.*
import dairo.aguas.feature.main.ui.history.HistoryViewModel
import dairo.aguas.feature.main.ui.home.HomeViewModel
import dairo.aguas.feature.main.ui.payment.PaymentViewModel
import dairo.aguas.feature.main.ui.resume.ResumeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Dairo Aguas B on 18/04/2020.
 */
val mainModule = module {
    factory { GetUserLocal(get()) }
    factory { GetProductLocal(get()) }
    factory { GetCreditCardLocalFlow(get()) }
    factory { ProcessTransaction(get()) }
    factory { SetTransactionLocal(get(), get()) }
    factory { GetTransactionLocal(get()) }
    factory { GetTransactionsLocalFlow(get()) }
    factory { DeleteTransactionLocal(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { PaymentViewModel(get(), get(), get(), get(), get(), androidContext()) }
    viewModel { ResumeViewModel(get()) }
    viewModel { HistoryViewModel(get(), get()) }
}