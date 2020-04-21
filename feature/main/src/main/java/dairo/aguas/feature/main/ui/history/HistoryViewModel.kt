package dairo.aguas.feature.main.ui.history

import androidx.lifecycle.*
import dairo.aguas.data.model.transaction.Transactions
import dairo.aguas.feature.main.domain.DeleteTransactionLocal
import dairo.aguas.feature.main.domain.GetTransactionsLocalFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryViewModel(
    getTransactionsLocalFlow: GetTransactionsLocalFlow,
    private val deleteTransactionLocal: DeleteTransactionLocal
) : ViewModel() {

    val transactionsLiveData: LiveData<List<Transactions>> =
        getTransactionsLocalFlow.execute().asLiveData()

    fun deleteTransaction(idAuto: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteTransactionLocal.execute(idAuto)
        }
    }
}