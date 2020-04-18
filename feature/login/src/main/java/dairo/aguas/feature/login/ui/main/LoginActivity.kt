package dairo.aguas.feature.login.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import dairo.aguas.feature.login.R
import dairo.aguas.feature.login.databinding.ActivityLoginBinding
import dairo.aguas.libraries.actions.Actions
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModel()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureDataBinding()
        configureToolbar()

        binding.btLogin.setOnClickListener {
            startActivity(Actions.openMainActivity(this))
        }
    }

    private fun configureDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun configureToolbar() {
        setSupportActionBar(binding.toolbar)
    }
}
