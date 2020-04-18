package dairo.aguas.feature.login.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import dairo.aguas.feature.login.R
import dairo.aguas.feature.login.databinding.ActivityLoginBinding
import dairo.aguas.libraries.actions.Actions
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModel()
    private lateinit var binding: ActivityLoginBinding
    private val uiObserver = Observer<LoginUiModel> { handleUI(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureDataBinding()
        configureToolbar()
        startObserve()
    }

    private fun configureDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun configureToolbar() {
        setSupportActionBar(binding.toolbar)
    }

    private fun startObserve() {
        viewModel.uiModel.observe(this, uiObserver)
    }

    private fun handleUI(loginUiModel: LoginUiModel) {
        loginUiModel.apply {
            if (showMessageAlert.isNotEmpty())
                Toast.makeText(this@LoginActivity, showMessageAlert, Toast.LENGTH_LONG).show()

            if (openMainActivity) {
                startActivity(Actions.openMainActivity(this@LoginActivity))
                finish()
            }
        }
    }
}
