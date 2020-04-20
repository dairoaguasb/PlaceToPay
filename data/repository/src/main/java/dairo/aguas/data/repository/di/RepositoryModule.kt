package dairo.aguas.data.repository.di

import dairo.aguas.data.repository.creditcard.CreditCardRepository
import dairo.aguas.data.repository.creditcard.CreditCardRepositoryImpl
import dairo.aguas.data.repository.product.ProductRepository
import dairo.aguas.data.repository.product.ProductRepositoryImpl
import dairo.aguas.data.repository.transaction.TransactionRepository
import dairo.aguas.data.repository.transaction.TransactionRepositoryImpl
import dairo.aguas.data.repository.user.UserRepository
import dairo.aguas.data.repository.user.UserRepositoryImpl
import org.koin.dsl.module

/**
 * Created by Dairo Aguas B on 18/04/2020.
 */
val repositoryModule = module {
    factory { UserRepositoryImpl(get()) as UserRepository }
    factory { ProductRepositoryImpl(get()) as ProductRepository }
    factory { CreditCardRepositoryImpl(get()) as CreditCardRepository }
    factory { TransactionRepositoryImpl(get()) as TransactionRepository }
}