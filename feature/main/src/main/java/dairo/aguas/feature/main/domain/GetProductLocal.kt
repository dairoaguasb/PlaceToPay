package dairo.aguas.feature.main.domain

import dairo.aguas.data.repository.product.ProductRepository

/**
 * Created by Dairo Aguas B on 19/04/2020.
 */
class GetProductLocal(private val productRepository: ProductRepository) {

    suspend fun execute() =
        productRepository.getProductRandom()
}