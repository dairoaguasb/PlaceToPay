package dairo.aguas.feature.main.ui.history.adapter

import dairo.aguas.data.model.transaction.Transactions

/**
 * Created by Dairo Aguas B on 21/04/2020.
 */
interface OnListenerTransaction {

    fun onClickListener(transactions: Transactions)

    fun onClickDelete(transactions: Transactions)

    fun onClickRetry(transactions: Transactions)
}