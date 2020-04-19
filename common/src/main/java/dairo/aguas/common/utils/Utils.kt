package dairo.aguas.common.utils

import android.app.Activity
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

fun String.getMD5(): String {
    // Create MD5 Hash
    val digest = java.security.MessageDigest.getInstance("MD5")
    digest.update(this.toByteArray())
    val messageDigest = digest.digest()

    // Create Hex String
    val hexString = StringBuilder()
    for (aMessageDigest in messageDigest) {
        var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
        while (h.length < 2)
            h = "0$h"
        hexString.append(h)
    }
    return hexString.toString()
}