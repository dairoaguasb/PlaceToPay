package dairo.aguas.feature.payment.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

/**
 * Created by Dairo Aguas B on 18/04/2020.
 */
class ExpiredCardFormatWatcher(private val editText: EditText) : TextWatcher {

    override fun afterTextChanged(editable: Editable) {

    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, start: Int, removed: Int, added: Int) {
        if (start == 1 && start + added == 2 && p0?.contains('/') == false) {
            val format = "$p0/"
            editText.setText(format)
            editText.setSelection(format.length)
        } else if (start == 3 && start - removed == 2 && p0?.contains('/') == true) {
            editText.setText(p0.toString().replace("/", ""))
            editText.setSelection(2)
        }
    }
}