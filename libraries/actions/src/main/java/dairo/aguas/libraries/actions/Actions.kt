package dairo.aguas.libraries.actions

import android.content.Context
import android.content.Intent
import dairo.aguas.common.utils.Constants

object Actions {

    fun openLoginIntent(context: Context) =
        internalIntent(context, Constants.actionLogin)

    fun openMainActivity(context: Context) =
        internalIntent(context, Constants.actionMain)

    fun openPaymentActivity(context: Context) =
        internalIntent(context, Constants.actionPayment)

    private fun internalIntent(context: Context, action: String) =
        Intent(action).setPackage(context.packageName)
}