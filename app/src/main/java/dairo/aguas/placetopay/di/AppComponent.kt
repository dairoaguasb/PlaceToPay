package dairo.aguas.placetopay.di

import dairo.aguas.common.utils.Constants
import dairo.aguas.data.local.di.localModule
import dairo.aguas.data.remote.di.createRemoteModule
import dairo.aguas.data.repository.di.repositoryModule
import dairo.aguas.feature.login.di.loginModule
import dairo.aguas.feature.main.di.mainModule
import dairo.aguas.feature.payment.di.paymentModule
import dairo.aguas.placetopay.BuildConfig

/**
 * Created by Dairo Aguas B on 17/04/2020.
 */
val appComponent = listOf(
    createRemoteModule(baseUrl = Constants.BASE_URL, debugMode = BuildConfig.DEBUG),
    loginModule,
    localModule,
    repositoryModule,
    mainModule,
    paymentModule
)