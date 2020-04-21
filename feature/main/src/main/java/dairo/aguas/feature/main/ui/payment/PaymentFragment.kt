package dairo.aguas.feature.main.ui.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import dairo.aguas.data.model.creditcard.CreditCard
import dairo.aguas.feature.main.R
import dairo.aguas.feature.main.databinding.FragmentPaymentBinding
import dairo.aguas.libraries.actions.Actions
import org.koin.android.viewmodel.ext.android.viewModel

class PaymentFragment : Fragment() {

    private val viewModel: PaymentViewModel by viewModel()
    private lateinit var binding: FragmentPaymentBinding
    private val creditCardObserver = Observer<CreditCard?> { handleCreditCard(it) }
    private val uiModel = Observer<PaymentUiModel> { handleUI(it) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        configureDataBinding(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        eventViews()
        startObserver()
    }

    private fun configureDataBinding(inflater: LayoutInflater) {
        binding = FragmentPaymentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun eventViews() {
        binding.btAdd.setOnClickListener {
            startActivity(Actions.openPaymentActivity(context!!))
        }
    }

    private fun startObserver() {
        viewModel.creditCard.observe(viewLifecycleOwner, creditCardObserver)
        viewModel.uiModel.observe(viewLifecycleOwner, uiModel)
    }

    private fun handleCreditCard(creditCard: CreditCard?) {
        creditCard?.apply {
            binding.cvCard.visibility = View.VISIBLE
            binding.tvCardNumber.text = numberCreditCard
            binding.tvCardExpiration.text = expirationCard
            binding.tvCardName.text = nameCreditCard
            binding.tvCardCVV.text = ccv
        }
    }

    private fun handleUI(uiModel: PaymentUiModel) {
        uiModel.apply {
            binding.pbLoading.visibility = uiModel.toggleVisibility(showProgress)
            if (showProgress) disableViews()

            if (showMessageAlert.isNotEmpty())
                Toast.makeText(context, showMessageAlert, Toast.LENGTH_LONG).show()
        }
    }

    private fun disableViews() {
        binding.cvCard.isEnabled = false
        binding.clCard.setBackgroundColor(ContextCompat.getColor(context!!, R.color.dark_gray))
        binding.btAdd.isEnabled = false
    }
}
