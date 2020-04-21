package dairo.aguas.feature.main.ui.history.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dairo.aguas.data.model.transaction.Transactions
import dairo.aguas.feature.main.databinding.CardTransactionBinding

/**
 * Created by Dairo Aguas B on 21/04/2020.
 */
class TransactionAdapter(private val onListenerTransaction: OnListenerTransaction) :
    ListAdapter<Transactions, TransactionAdapter.TransactionViewHolder>(TransactionDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder =
        TransactionViewHolder.from(parent)

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) =
        holder.bind(getItem(position), onListenerTransaction)

    class TransactionViewHolder private constructor(private val binding: CardTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Transactions, onListenerTransaction: OnListenerTransaction) {
            binding.transaction = item
            binding.clickListener = onListenerTransaction
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): TransactionViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CardTransactionBinding.inflate(layoutInflater, parent, false)
                return TransactionViewHolder(binding)
            }
        }
    }
}

class TransactionDiffCallback : DiffUtil.ItemCallback<Transactions>() {
    override fun areItemsTheSame(oldItem: Transactions, newItem: Transactions) =
        oldItem.idAuto == newItem.idAuto

    override fun areContentsTheSame(oldItem: Transactions, newItem: Transactions) =
        oldItem == newItem

}