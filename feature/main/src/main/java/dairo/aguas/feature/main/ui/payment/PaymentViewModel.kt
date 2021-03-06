package dairo.aguas.feature.main.ui.payment

import android.content.Context
import androidx.lifecycle.*
import dairo.aguas.common.utils.AssetsPropertyReader
import dairo.aguas.common.utils.Constants
import dairo.aguas.common.utils.GenerateAuth
import dairo.aguas.data.model.creditcard.CreditCard
import dairo.aguas.data.model.product.Product
import dairo.aguas.data.model.transaction.*
import dairo.aguas.data.model.user.User
import dairo.aguas.data.model.vo.Result
import dairo.aguas.feature.main.BuildConfig
import dairo.aguas.feature.main.domain.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PaymentViewModel(
    private val getProductLocal: GetProductLocal,
    private val getUserLocal: GetUserLocal,
    private val processTransaction: ProcessTransaction,
    private val setTransactionLocal: SetTransactionLocal,
    getCreditCardLocalFlow: GetCreditCardLocalFlow,
    private val context: Context
) : ViewModel() {

    private val _uiModel = MutableLiveData<PaymentUiModel>()
    val uiModel: LiveData<PaymentUiModel>
        get() = _uiModel

    private val _productLocal = MutableLiveData<Product>()
    val productLocal: LiveData<Product>
        get() = _productLocal

    val creditCard: LiveData<CreditCard?> = getCreditCardLocalFlow.execute().asLiveData()
    lateinit var user: User

    init {
        getProductLocal()
        getUserLocal()
    }

    fun getProductLocal() {
        viewModelScope.launch(Dispatchers.IO) {
            getProductLocal.execute().also {
                viewModelScope.launch(Dispatchers.Main) {
                    _productLocal.value = it
                }
            }
        }
    }

    private fun getUserLocal() {
        viewModelScope.launch(Dispatchers.IO) {
            getUserLocal.execute().also { user = it }
        }
    }

    fun processTransaction(creditCard: CreditCard, product: Product) {
        emitUiState(showProgress = true)
        val payment = Payment(product.nameProduct, Amount(product.priceProduct))
        val instrument = generateInstrument(creditCard)
        val payer = Payer(user.fullName, user.phone, user.email)

        val transactionBody = TransactionBody(generateAuth(), payment, instrument, payer)
        viewModelScope.launch(Dispatchers.IO) {
            processTransaction.execute(transactionBody).also {
                validateTransactionResult(it)
            }
        }
    }

    private fun validateTransactionResult(resultTransaction: Result<TransactionResponse>) {
        when (resultTransaction) {
            is Result.Failure -> {
                emitUiState(showMessageAlert = resultTransaction.exception.message!!)
            }
            is Result.Success -> {
                validateTransactionData(resultTransaction.data)
            }
        }
    }

    private fun validateTransactionData(transactionResponse: TransactionResponse) {
        emitUiState(showProgress = false)
        if (transactionResponse.status.status != Constants.TRANSACTION_FAILED) {
            setTransactionLocal(transactionResponse)
        } else {
            emitUiState(showMessageAlert = transactionResponse.status.message)
        }
    }

    private fun setTransactionLocal(transactionResponse: TransactionResponse) {
        viewModelScope.launch(Dispatchers.IO) {
            setTransactionLocal.execute(transactionResponse).also {
                emitUiState(
                    showDialogResume = true,
                    internalReference = transactionResponse.internalReference
                )
            }
        }
    }

    private fun generateInstrument(creditCard: CreditCard) =
        Instrument(
            Card(
                creditCard.numberCreditCard.replace("\\s".toRegex(), ""),
                creditCard.expirationCard.substring(0, 2),
                creditCard.expirationCard.substring(3, 5),
                creditCard.ccv
            )
        )

    private fun generateAuth(): Auth {
        val assetsPropertyReader = AssetsPropertyReader(context)
        val login = assetsPropertyReader.getProperties(BuildConfig.PROPERTIES_FILE)
            .getProperty(BuildConfig.LOGIN)
        val tranKey = assetsPropertyReader.getProperties(BuildConfig.PROPERTIES_FILE)
            .getProperty(BuildConfig.TRAN_KEY)

        GenerateAuth(login, tranKey).let {
            return Auth(it.login, it.tranKey, it.nonce, it.seed)
        }
    }

    private fun emitUiState(
        showProgress: Boolean = false,
        showMessageAlert: String = "",
        showDialogResume: Boolean = false,
        internalReference: Int = 0
    ) {
        viewModelScope.launch(Dispatchers.Main) {
            val uiModel =
                PaymentUiModel(showMessageAlert, showProgress, showDialogResume, internalReference)
            _uiModel.value = uiModel
        }
    }
}