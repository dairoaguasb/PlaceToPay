package dairo.aguas.feature.main.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dairo.aguas.data.model.transaction.Transactions
import dairo.aguas.feature.main.domain.GetTransactionsLocalFlow

class HistoryViewModel(getTransactionsLocalFlow: GetTransactionsLocalFlow) : ViewModel() {

    val transactionsLiveData: LiveData<List<Transactions>> =
        getTransactionsLocalFlow.execute().asLiveData()
}