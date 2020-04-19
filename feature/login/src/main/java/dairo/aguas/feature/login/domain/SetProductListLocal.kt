package dairo.aguas.feature.login.domain

import dairo.aguas.data.model.product.Product
import dairo.aguas.data.repository.product.ProductRepository

/**
 * Created by Dairo Aguas B on 19/04/2020.
 */
class SetProductListLocal(private val productRepository: ProductRepository) {

    suspend fun execute(productList: List<Product>) {
        if (productRepository.getProductList().isEmpty()) {
            productRepository.insertAll(productList)
        }
    }
}