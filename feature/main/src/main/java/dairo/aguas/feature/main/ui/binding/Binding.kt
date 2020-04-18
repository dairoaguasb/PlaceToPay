package dairo.aguas.feature.main.ui.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dairo.aguas.feature.main.R

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