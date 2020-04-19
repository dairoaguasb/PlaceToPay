package dairo.aguas.feature.main.ui.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import dairo.aguas.data.model.creditcard.CreditCard
import dairo.aguas.feature.main.databinding.FragmentPaymentBinding
import dairo.aguas.libraries.actions.Actions
import org.koin.android.viewmodel.ext.android.viewModel

class PaymentFragment : Fragment() {

    private val viewModel: PaymentViewModel by viewModel()
    private lateinit var binding: FragmentPaymentBinding
    private val creditCardObserver = Observer<CreditCard?> { handleCreditCard(it) }

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
}
