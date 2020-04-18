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

class PaymentFragment : Fragment() {

    private lateinit var paymentViewModel: PaymentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        paymentViewModel =
            ViewModelProviders.of(this).get(PaymentViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_payment, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        paymentViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
