package dairo.aguas.common.utils

import android.content.Context
import java.util.*

/**
 * Created by Dairo Aguas B on 20/04/2020.
 */
class AssetsPropertyReader(private val context: Context) {

    private val properties = Properties()

    fun getProperties(fileName: String): Properties {
        val assetManager = context.assets
        val inputStream = assetManager.open(fileName)
        properties.load(inputStream)
        return properties
    }
}