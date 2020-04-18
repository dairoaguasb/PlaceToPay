package dairo.aguas.placetopay.di

import dairo.aguas.data.local.di.localModule
import dairo.aguas.data.repository.di.repositoryModule
import dairo.aguas.feature.login.di.loginModule

/**
 * Created by Dairo Aguas B on 17/04/2020.
 */
val appComponent = listOf(
    loginModule,
    localModule,
    repositoryModule
)