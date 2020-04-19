package dairo.aguas.feature.login.ui.main

import android.content.Context
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dairo.aguas.common.utils.Constants
import dairo.aguas.common.utils.getMD5
import dairo.aguas.data.model.product.Product
import dairo.aguas.data.model.user.User
import dairo.aguas.feature.login.R
import dairo.aguas.feature.login.domain.AuthUserLocal
import dairo.aguas.feature.login.domain.SetProductListLocal
import dairo.aguas.feature.login.domain.SetUserLocal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Dairo Aguas B on 17/04/2020.
 */
class LoginViewModel(
    private val setUserLocal: SetUserLocal,
    private val setProductListLocal: SetProductListLocal,
    private val authUserLocal: AuthUserLocal,
    private val context: Context
) : ViewModel() {

    private val _uiModel = MutableLiveData<LoginUiModel>()
    val uiModel: LiveData<LoginUiModel>
        get() = _uiModel

    init {
        viewModelScope.launch(Dispatchers.IO) {
            setUserLocal.execute(User())
            setProductListLocal.execute(generateProductList())
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

    private fun generateProductList(): List<Product> {
        val productList = mutableListOf<Product>()
        productList.add(
            Product(
                nameProduct = "Portátil Asus Vivobook",
                priceProduct = 1950000,
                imageProduct = "https://http2.mlstatic.com/portatil-asus-vivobook-x512f-intel-core-i5-ram-12gb-D_NQ_NP_828401-MCO41309903999_042020-F.webp"
            )
        )
        productList.add(
            Product(
                nameProduct = "Motorola Moto G8",
                priceProduct = 758900,
                imageProduct = "https://http2.mlstatic.com/motorola-moto-g8-power-4g-D_NQ_NP_918804-MCO41311028287_042020-F.webp"
            )
        )
        productList.add(
            Product(
                nameProduct = "Reloj Huawei Gt B19",
                priceProduct = 539900,
                imageProduct = "https://http2.mlstatic.com/reloj-huawei-gt-b19-42mm-pulgadas-negro-D_NQ_NP_942202-MCO40176366672_122019-F.webp"
            )
        )
        productList.add(
            Product(
                nameProduct = "Multifuncional Hp",
                priceProduct = 129900,
                imageProduct = "https://http2.mlstatic.com/multifuncional-hp-2675-D_NQ_NP_937196-MCO40245501736_122019-F.webp"
            )
        )
        productList.add(
            Product(
                nameProduct = "DD Externo Toshiba",
                priceProduct = 288000,
                imageProduct = "https://http2.mlstatic.com/disco-duro-externo-toshiba-2tb-teras-canvio-modelo-2019-new-D_NQ_NP_750696-MCO31476323277_072019-F.webp"
            )
        )
        productList.add(
            Product(
                nameProduct = "Monitor Samsung Curvo",
                priceProduct = 697900,
                imageProduct = "https://http2.mlstatic.com/monitor-samsung-curvo-27-lcd-sams-lc27f390fhlxzl-D_NQ_NP_756339-MCO31490584740_072019-F.webp"
            )
        )
        productList.add(
            Product(
                nameProduct = "Xiaomi Redmi Airdots",
                priceProduct = 92600,
                imageProduct = "https://http2.mlstatic.com/xiaomi-redmi-airdots-manos-libres-bluetooth-originales-D_NQ_NP_993340-MCO31410298640_072019-F.webp"
            )
        )
        productList.add(
            Product(
                nameProduct = "Mouse Acer Nitro",
                priceProduct = 186250,
                imageProduct = "https://http2.mlstatic.com/mouse-acer-nitro-nmw810-optico-4000-dpi-led-20g-D_NQ_NP_938406-MCO32557311364_102019-F.webp"
            )
        )
        productList.add(
            Product(
                nameProduct = "Xiaomi Mi Smart Band 4",
                priceProduct = 124950,
                imageProduct = "https://http2.mlstatic.com/xiaomi-mi-smart-band-4-version-global-original-D_NQ_NP_995646-MCO31354501128_072019-F.webp"
            )
        )
        productList.add(
            Product(
                nameProduct = "SSD Kingston 480gb",
                priceProduct = 278000,
                imageProduct = "https://http2.mlstatic.com/disco-duro-solido-ssd-kingston-480gb-pc-o-portatil-slim-a400-D_NQ_NP_656718-MCO31120877491_062019-F.webp"
            )
        )

        return productList
    }
}