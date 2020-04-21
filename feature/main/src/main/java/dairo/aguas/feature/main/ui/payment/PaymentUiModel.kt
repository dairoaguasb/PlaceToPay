package dairo.aguas.feature.main.ui.payment

import android.view.View

/**
 * Created by Dairo Aguas B on 18/04/2020.
 */
data class PaymentUiModel(
    val showMessageAlert: String,
    val showProgress: Boolean,
    var showDialogResume: Boolean,
    val internalReference: Int
) {
    fun toggleVisibility(showProgress: Boolean) =
        when (showProgress) {
            true -> View.VISIBLE
            false -> View.INVISIBLE
        }
}