package dairo.aguas.feature.payment.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import dairo.aguas.feature.payment.R
import dairo.aguas.feature.payment.databinding.ActivityPaymentMethodBinding
import dairo.aguas.feature.payment.utils.ExpiredCardFormatWatcher
import dairo.aguas.feature.payment.utils.FourDigitCardFormatWatcher
import org.koin.android.viewmodel.ext.android.viewModel

class PaymentMethodActivity : AppCompatActivity() {

    private val viewModel: PaymentMethodViewModel by viewModel()
    private lateinit var binding: ActivityPaymentMethodBinding
    private val uiObserver = Observer<PaymentMethodUiModel> { handleUI(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureDataBinding()
        configureToolbar()
        viewsTextChange()
        startObserve()
    }

    private fun configureDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_payment_method)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun configureToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { finish() }
    }

    private fun viewsTextChange() {
        binding.etCardNumber.addTextChangedListener(FourDigitCardFormatWatcher())
        binding.etCardDate.addTextChangedListener(ExpiredCardFormatWatcher(binding.etCardDate))
    }

    private fun startObserve() {
        viewModel.uiModel.observe(this, uiObserver)
    }

    private fun handleUI(uiModel: PaymentMethodUiModel) {
        uiModel.apply {
            if (showMessageAlert.isNotEmpty())
                Toast.makeText(this@PaymentMethodActivity, showMessageAlert, Toast.LENGTH_LONG)
                    .show()

            if (finishActivity) finish()
        }
    }
}
