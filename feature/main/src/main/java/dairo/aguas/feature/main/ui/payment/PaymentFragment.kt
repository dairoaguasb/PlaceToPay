package dairo.aguas.feature.main.ui.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dairo.aguas.feature.main.R
import dairo.aguas.feature.main.databinding.FragmentPaymentBinding
import dairo.aguas.libraries.actions.Actions
import org.koin.android.viewmodel.ext.android.viewModel

class PaymentFragment : Fragment() {

    private val viewModel: PaymentViewModel by viewModel()
    private lateinit var binding: FragmentPaymentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        configureDataBinding(inflater)
        return binding.root
    }

    private fun configureDataBinding(inflater: LayoutInflater) {
        binding = FragmentPaymentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }
}
