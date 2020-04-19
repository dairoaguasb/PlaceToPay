package dairo.aguas.feature.main.ui.payment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dairo.aguas.data.model.product.Product
import dairo.aguas.feature.main.domain.GetProductLocal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PaymentViewModel(
    private val getProductLocal: GetProductLocal
) : ViewModel() {

    private val _productLocal = MutableLiveData<Product>()
    val productLocal: LiveData<Product>
        get() = _productLocal

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getProductLocal.execute().also {
                viewModelScope.launch(Dispatchers.Main) {
                    _productLocal.value = it
                }
            }
        }
    }
}