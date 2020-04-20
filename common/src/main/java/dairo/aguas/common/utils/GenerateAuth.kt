package dairo.aguas.common.utils

import android.util.Base64
import java.math.BigInteger
import java.security.MessageDigest
import java.security.SecureRandom
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Dairo Aguas B on 20/04/2020.
 */
data class GenerateAuth(
    var login: String,
    var tranKey: String,
    var seed: String = SimpleDateFormat(Constants.SDF, Locale.getDefault()).format(Date()),
    var nonce: String = BigInteger(130, SecureRandom()).toString()
) {
    init {
        generate()
    }

    private fun generate() {

        tranKey = generateBase64(generateSHA256(nonce + seed + tranKey))
        tranKey = tranKey.substring(0, tranKey.length - 1)
        nonce = generateBase64(nonce.toByteArray())
    }

    private fun generateSHA256(input: String): ByteArray {
        val mDigest = MessageDigest.getInstance("SHA-256")
        return mDigest.digest(input.toByteArray())
    }

    private fun generateBase64(input: ByteArray) =
        Base64.encodeToString(input, Base64.DEFAULT)
}