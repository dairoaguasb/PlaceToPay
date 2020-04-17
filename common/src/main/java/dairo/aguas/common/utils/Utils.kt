package dairo.aguas.common.utils

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.text.DecimalFormat

object Utils {

    fun hideKeyBoardFragment(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = activity.currentFocus
        if (view == null) view = View(activity)
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun formatMoney(value: String): String {
        val decimalFormat = DecimalFormat("#,###")
        return "$" + decimalFormat.format(value.toDouble()).replace(",", ".")
    }
}