package dairo.aguas.data.repository.di

import dairo.aguas.data.repository.product.ProductRepository
import dairo.aguas.data.repository.product.ProductRepositoryImpl
import dairo.aguas.data.repository.user.UserRepository
import dairo.aguas.data.repository.user.UserRepositoryImpl
import org.koin.dsl.module

/**
 * Created by Dairo Aguas B on 18/04/2020.
 */
val repositoryModule = module {
    factory { UserRepositoryImpl(get()) as UserRepository }
    factory { ProductRepositoryImpl(get()) as ProductRepository }
}