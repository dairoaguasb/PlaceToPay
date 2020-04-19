package dairo.aguas.feature.main.ui.payment

import androidx.lifecycle.*
import dairo.aguas.data.model.creditcard.CreditCard
import dairo.aguas.data.model.product.Product
import dairo.aguas.feature.main.domain.GetCreditCardLocalFlow
import dairo.aguas.feature.main.domain.GetProductLocal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PaymentViewModel(
    private val getProductLocal: GetProductLocal,
    private val getCreditCardLocalFlow: GetCreditCardLocalFlow
) : ViewModel() {

    private val _productLocal = MutableLiveData<Product>()
    val productLocal: LiveData<Product>
        get() = _productLocal

    val creditCard: LiveData<CreditCard?> = getCreditCardLocalFlow.execute().asLiveData()

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