package dairo.aguas.feature.main.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dairo.aguas.common.utils.Constants
import dairo.aguas.data.model.transaction.Transactions
import dairo.aguas.data.model.user.User
import dairo.aguas.feature.main.domain.GetTransactionsLocal
import dairo.aguas.feature.main.domain.GetUserLocal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getUserLocal: GetUserLocal,
    private val getTransactionsLocal: GetTransactionsLocal
) : ViewModel() {

    private val _userLocal = MutableLiveData<User>()
    val userLocal: LiveData<User>
        get() = _userLocal

    private val _transactionsCount = MutableLiveData<TransactionsCount>()
    val transactionsCount: LiveData<TransactionsCount>
        get() = _transactionsCount

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getUserLocal.execute().also {
                viewModelScope.launch(Dispatchers.Main) {
                    _userLocal.value = it
                }
            }
        }
        getTransactionsLocal()
    }

    private fun getTransactionsLocal() {
        viewModelScope.launch(Dispatchers.IO) {
            getTransactionsLocal.execute().also {
                countTransactions(it)
            }
        }
    }

    private fun countTransactions(transactionList: List<Transactions>) {
        val approved = transactionList.filter { it.status == Constants.TRANSACTION_APPROVED }.size.toString()
        val rejected = transactionList.filter { it.status == Constants.TRANSACTION_REJECTED }.size.toString()
        val pending = transactionList.filter { it.status == Constants.TRANSACTION_PENDING }.size.toString()

        viewModelScope.launch(Dispatchers.Main) {
            _transactionsCount.value = TransactionsCount(approved, rejected, pending)
        }
    }
}