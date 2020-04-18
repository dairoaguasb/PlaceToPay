package dairo.aguas.feature.main.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dairo.aguas.common.utils.Constants
import dairo.aguas.data.model.user.User
import dairo.aguas.feature.main.domain.GetUserLocal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getUserLocal: GetUserLocal
) : ViewModel() {

    private val _userLocal = MutableLiveData<User>()
    val userLocal: LiveData<User>
        get() = _userLocal


    init {
        viewModelScope.launch(Dispatchers.IO) {
            getUserLocal.execute().also {
                viewModelScope.launch(Dispatchers.Main) {
                    _userLocal.value = it
                }
            }
        }
    }
}