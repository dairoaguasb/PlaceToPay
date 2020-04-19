package dairo.aguas.data.repository.product

import dairo.aguas.data.model.product.Product

/**
 * Created by Dairo Aguas B on 19/04/2020.
 */
interface ProductRepository {

    suspend fun insertAll(productList: List<Product>)

    suspend fun getProductList(): List<Product>

    suspend fun getProductRandom(): Product
}