package dairo.aguas.feature.login.ui.main

import android.content.Context
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dairo.aguas.common.utils.Constants
import dairo.aguas.common.utils.getMD5
import dairo.aguas.data.model.user.User
import dairo.aguas.feature.login.R
import dairo.aguas.feature.login.domain.AuthUserLocal
import dairo.aguas.feature.login.domain.SetUserLocal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Dairo Aguas B on 17/04/2020.
 */
class LoginViewModel(
    private val setUserLocal: SetUserLocal,
    private val authUserLocal: AuthUserLocal,
    private val context: Context
) : ViewModel() {

    private val _uiModel = MutableLiveData<LoginUiModel>()
    val uiModel: LiveData<LoginUiModel>
        get() = _uiModel

    init {
        viewModelScope.launch(Dispatchers.IO) {
            setUserLocal.execute(User())
        }
    }

    fun validateField(email: String, password: String) {
        when {
            email.isEmpty() || Patterns.EMAIL_ADDRESS.matcher(email).matches().not() -> {
                emitUiState(showMessageAlert = context.getString(R.string.invalid_email))
            }
            password.length < Constants.MIN_LENGTH_PASS -> {
                emitUiState(showMessageAlert = context.getString(R.string.invalid_pass))
            }
            else -> {
                authUser(email, password)
            }
        }
    }

    private fun authUser(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            authUserLocal.execute(email, password.getMD5()).also {
                validateUser(it)
            }
        }
    }

    private fun validateUser(user: User?) {
        user?.let {
            emitUiState(openMainActivity = true)
        } ?: run {
            emitUiState(showMessageAlert = context.getString(R.string.invalid_user_pass))
        }
    }

    private fun emitUiState(showMessageAlert: String = "", openMainActivity: Boolean = false) {
        viewModelScope.launch(Dispatchers.Main) {
            val uiModel = LoginUiModel(showMessageAlert, openMainActivity)
            _uiModel.value = uiModel
        }
    }
}