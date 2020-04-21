package dairo.aguas.data.repository.product

import dairo.aguas.data.local.dao.ProductDao
import dairo.aguas.data.model.product.Product

/**
 * Created by Dairo Aguas B on 19/04/2020.
 */
class ProductRepositoryImpl(private val productDao: ProductDao) : ProductRepository {

    override suspend fun insertAll(productList: List<Product>) {
        productDao.insertAll(productList)
    }

    override suspend fun getProductList() =
        productDao.getListProduct()

    override suspend fun getProductRandom() =
        productDao.getProductRandom()

    override suspend fun getProductByName(nameProduct: String) =
        productDao.getProductByName(nameProduct)
}