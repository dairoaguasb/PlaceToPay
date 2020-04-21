package dairo.aguas.feature.main.ui.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import dairo.aguas.common.utils.Constants
import dairo.aguas.data.model.creditcard.CreditCard
import dairo.aguas.feature.main.R
import dairo.aguas.feature.main.databinding.FragmentPaymentBinding
import dairo.aguas.feature.main.ui.resume.ResumeFragment
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
            else enableViews()

            if (showMessageAlert.isNotEmpty())
                Toast.makeText(context, showMessageAlert, Toast.LENGTH_LONG).show()

            if (showDialogResume) {
                showDialogResume = false
                showResumeDialog(internalReference)
            }
        }
    }

    private fun disableViews() {
        binding.cvCard.isEnabled = false
        binding.btAdd.isEnabled = false
        binding.clCard.setBackgroundColor(ContextCompat.getColor(context!!, R.color.dark_gray))
    }

    private fun enableViews() {
        binding.cvCard.isEnabled = true
        binding.btAdd.isEnabled = true
        binding.clCard.background =
            ContextCompat.getDrawable(context!!, R.drawable.background_gradient)
        generateNewProduct()
    }

    private fun generateNewProduct() {
        viewModel.getProductLocal()
        Toast.makeText(context!!, getString(R.string.new_product), Toast.LENGTH_SHORT).show()
    }

    private fun showResumeDialog(internalReference: Int) {
        val resumeFragment = ResumeFragment()
        val args = Bundle()
        args.putInt(Constants.INTERNAL_REFERENCE, internalReference)
        resumeFragment.arguments = args
        resumeFragment.show(activity!!.supportFragmentManager, "dialog")
    }
}
