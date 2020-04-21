package dairo.aguas.feature.main.ui.resume

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dairo.aguas.data.model.transaction.Transactions
import dairo.aguas.feature.main.domain.GetTransactionLocal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ResumeViewModel(
    private val getTransactionLocal: GetTransactionLocal
) : ViewModel() {

    private val _transactionLocal = MutableLiveData<Transactions>()
    val transaction: LiveData<Transactions>
        get() = _transactionLocal

    fun getTransactionByInternalReference(internalReference: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getTransactionLocal.execute(internalReference).also {
                viewModelScope.launch(Dispatchers.Main) {
                    _transactionLocal.value = it
                }
            }
        }
    }
}
