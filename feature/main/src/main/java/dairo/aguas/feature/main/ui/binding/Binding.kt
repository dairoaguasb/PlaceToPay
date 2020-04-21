package dairo.aguas.feature.main.ui.binding

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dairo.aguas.common.utils.Constants
import dairo.aguas.common.utils.Utils
import dairo.aguas.feature.main.R
import java.text.SimpleDateFormat

/**
 * Created by Dairo Aguas B on 18/04/2020.
 */

@BindingAdapter("loadImage")
fun ImageView.loadImage(urlImage: String?) {
    urlImage?.let {
        Glide.with(this.context)
            .load(it)
            .apply(
                RequestOptions().placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(this)
    }
}

@BindingAdapter("formatMoney")
fun TextView.formatMoney(value: Int) {
    this.text = Utils.formatMoney(value.toString())
}

@BindingAdapter("formatDate")
fun TextView.formatDate(date: String?) {
    date?.let {
        this.text = Utils.formatDate(date)
    }
}

@BindingAdapter("formatTransaction")
fun TextView.formatTransaction(status: String?) {
    status?.let {
        this.text = Utils.formatTransaction(it)
    }
}