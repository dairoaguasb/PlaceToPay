package dairo.aguas.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dairo.aguas.data.model.product.Product

/**
 * Created by Dairo Aguas B on 19/04/2020.
 */
@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(productList: List<Product>)

    @Query("SELECT * FROM product")
    suspend fun getListProduct(): List<Product>

    @Query("SELECT * FROM product ORDER BY RANDOM() LIMIT 1")
    suspend fun getProductRandom(): Product

    @Query("SELECT * FROM product WHERE nameProduct = :nameProduct")
    suspend fun getProductByName(nameProduct: String) : Product
}