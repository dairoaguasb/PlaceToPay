package dairo.aguas.feature.payment.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import dairo.aguas.feature.payment.R
import dairo.aguas.feature.payment.databinding.ActivityPaymentMethodBinding
import dairo.aguas.feature.payment.utils.ExpiredCardFormatWatcher
import dairo.aguas.feature.payment.utils.FourDigitCardFormatWatcher
import org.koin.android.viewmodel.ext.android.viewModel

class PaymentMethodActivity : AppCompatActivity() {

    private val viewModel: PaymentMethodViewModel by viewModel()
    private lateinit var binding: ActivityPaymentMethodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureDataBinding()
        configureToolbar()
        viewsTextChange()
    }

    private fun configureDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_payment_method)
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
}
