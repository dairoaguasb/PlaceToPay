package dairo.aguas.common.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.text.DecimalFormat
import java.text.SimpleDateFormat

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

    @SuppressLint("SimpleDateFormat")
    fun formatDate(date: String): String {
        val sdfInput = SimpleDateFormat(Constants.SDF)
        val sdfOutput = SimpleDateFormat(Constants.SDF_LOCAL)
        return sdfOutput.format(sdfInput.parse(date)!!)
    }

    fun formatTransaction(status: String) =
        when (status) {
            Constants.TRANSACTION_APPROVED -> Constants.APPROVED
            Constants.TRANSACTION_REJECTED -> Constants.REJECTED
            Constants.TRANSACTION_PENDING -> Constants.PENDING
            Constants.TRANSACTION_MANUAL -> Constants.MANUAL
            Constants.TRANSACTION_REFUNDED -> Constants.REFUNDED
            else -> Constants.FAILED
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