package dairo.aguas.feature.payment.ui.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dairo.aguas.common.utils.Constants
import dairo.aguas.data.model.creditcard.CreditCard
import dairo.aguas.feature.payment.R
import dairo.aguas.feature.payment.domain.SetCreditCardLocal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Dairo Aguas B on 18/04/2020.
 */
class PaymentMethodViewModel(
    private val setCreditCardLocal: SetCreditCardLocal,
    private val context: Context
) : ViewModel() {

    private val _uiModel = MutableLiveData<PaymentMethodUiModel>()
    val uiModel: LiveData<PaymentMethodUiModel>
        get() = _uiModel

    fun validateField(name: String, number: String, expiration: String, ccv: String) {
        when {
            name.length < Constants.MIN_LENGTH_NAME -> {
                emitUiState(showMessageAlert = context.getString(R.string.invalid_name))
            }
            number.length < Constants.MIN_LENGTH_CARD -> {
                emitUiState(showMessageAlert = context.getString(R.string.invalid_card))
            }
            expiration.length < Constants.MIN_LENGTH_EXPIRATION -> {
                emitUiState(showMessageAlert = context.getString(R.string.invalid_expiration))
            }
            ccv.length < Constants.MIN_LENGTH_CVV -> {
                emitUiState(showMessageAlert = context.getString(R.string.invalid_cvv))
            }
            else -> {
                val creditCard = CreditCard(
                    nameCreditCard = name,
                    numberCreditCard = number,
                    expirationCard = expiration,
                    ccv = ccv
                )
                setCreditCard(creditCard)
            }
        }
    }

    private fun setCreditCard(creditCard: CreditCard) {
        viewModelScope.launch(Dispatchers.IO) {
            setCreditCardLocal.execute(creditCard).also {
                emitUiState(finishActivity = true)
            }
        }
    }

    private fun emitUiState(showMessageAlert: String = "", finishActivity: Boolean = false) {
        viewModelScope.launch(Dispatchers.Main) {
            val uiModel = PaymentMethodUiModel(showMessageAlert, finishActivity)
            _uiModel.value = uiModel
        }
    }
}