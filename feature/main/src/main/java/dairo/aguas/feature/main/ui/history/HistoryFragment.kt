package dairo.aguas.feature.main.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dairo.aguas.data.model.transaction.Transactions
import dairo.aguas.feature.main.R
import dairo.aguas.feature.main.databinding.FragmentHistoryBinding
import dairo.aguas.feature.main.ui.history.adapter.OnListenerTransaction
import dairo.aguas.feature.main.ui.history.adapter.TransactionAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class HistoryFragment : Fragment(), OnListenerTransaction {

    private val viewModel: HistoryViewModel by viewModel()
    private lateinit var binding: FragmentHistoryBinding
    private lateinit var transactionAdapter: TransactionAdapter
    private val transactionsObserver = Observer<List<Transactions>> { handleTransactions(it) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        configureDataBinding(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureAdapter()
        startObserver()
    }

    private fun configureDataBinding(inflater: LayoutInflater) {
        binding = FragmentHistoryBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun configureAdapter() {
        transactionAdapter = TransactionAdapter(this)
        binding.rvTransactions.layoutManager = LinearLayoutManager(context)
        binding.rvTransactions.adapter = transactionAdapter
    }

    private fun startObserver() {
        viewModel.transactionsLiveData.observe(viewLifecycleOwner, transactionsObserver)
    }

    private fun handleTransactions(it: List<Transactions>) {
        transactionAdapter.submitList(it)
    }

    override fun onClickListener(transactions: Transactions) {
        Toast.makeText(context!!, transactions.message, Toast.LENGTH_LONG).show()
    }

    override fun onClickDelete(transactions: Transactions) {
        viewModel.deleteTransaction(transactions.idAuto)
    }

    override fun onClickRetry(transactions: Transactions) {
        TODO("Not yet implemented")
    }
}
