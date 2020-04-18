package dairo.aguas.feature.payment.utils

import android.text.Editable
import android.text.TextWatcher

/**
 * Created by Dairo Aguas B on 18/04/2020.
 */
class FourDigitCardFormatWatcher : TextWatcher {

    override fun afterTextChanged(s: Editable?) {
        if (s == null || s.isEmpty()) return

        s.forEachIndexed { index, c ->
            val spaceIndex = index == 4 || index == 9 || index == 14
            when {
                !spaceIndex && !c.isDigit() -> s.delete(index, index + 1)
                spaceIndex && !c.isWhitespace() -> s.insert(index, " ")
            }
        }

        if (s.last().isWhitespace())
            s.delete(s.length - 1, s.length)
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
}