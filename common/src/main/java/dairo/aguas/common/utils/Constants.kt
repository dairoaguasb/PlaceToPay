package dairo.aguas.common.utils

object Constants {

    const val BASE_URL = "https://dev.placetopay.com/rest/"

    const val actionLogin = "dairo.aguas.login.open"
    const val actionMain = "dairo.aguas.main.open"
    const val actionPayment = "dairo.aguas.payment.open"

    const val MIN_LENGTH_PASS = 4
    const val MIN_LENGTH_NAME = 8
    const val MIN_LENGTH_CARD = 17
    const val MIN_LENGTH_EXPIRATION = 5
    const val MIN_LENGTH_CVV = 3

    const val urlImage =
        "https://firebasestorage.googleapis.com/v0/b/laplazita-af123.appspot.com/o/user.jpg?alt=media&token=af508f13-1042-4287-a863-d4c989a10309"

    const val SDF = "yyyy-MM-dd'T'HH:mm:ssX':00'"
    const val SDF_LOCAL = "yyyy-MM-dd HH:mm:ss"

    const val TRANSACTION_FAILED = "FAILED"
    const val TRANSACTION_APPROVED = "APPROVED"
    const val TRANSACTION_REJECTED = "REJECTED"
    const val TRANSACTION_PENDING = "PENDING"
    const val TRANSACTION_MANUAL = "MANUAL"
    const val TRANSACTION_REFUNDED = "REFUNDED"

    const val FAILED = "Fallida"
    const val APPROVED = "Aprobada"
    const val REJECTED = "Rechazada"
    const val PENDING = "Pendiente"
    const val MANUAL = "Manual"
    const val REFUNDED = "Reintegrada"

    const val INTERNAL_REFERENCE = "internalReference"
}