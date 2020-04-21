package dairo.aguas.feature.main.ui.resume

import android.app.Dialog
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import dairo.aguas.common.utils.Constants
import dairo.aguas.feature.main.R
import dairo.aguas.feature.main.databinding.ResumeFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class ResumeFragment : DialogFragment() {

    private val viewModel: ResumeViewModel by viewModel()
    private lateinit var binding: ResumeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        configureDataBinding(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getInfoArguments()
        configureToolbar()
        configureAnim()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.setCanceledOnTouchOutside(false)
        return dialog
    }

    override fun onStart() {
        super.onStart()
        val metrics = resources.displayMetrics
        dialog?.apply {
            window?.setLayout((metrics.widthPixels), LinearLayout.LayoutParams.WRAP_CONTENT)
        }
    }

    private fun configureDataBinding(inflater: LayoutInflater) {
        binding = ResumeFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun getInfoArguments() {
        val internalReference = arguments!!.getInt(Constants.INTERNAL_REFERENCE)
        viewModel.getTransactionByInternalReference(internalReference)
    }

    private fun configureToolbar() {
        binding.toolbar.title = getString(R.string.title_resume)
    }

    private fun configureAnim() {
        dialog?.apply {
            window?.attributes?.windowAnimations = R.style.DialogAnimation
        }
    }
}
