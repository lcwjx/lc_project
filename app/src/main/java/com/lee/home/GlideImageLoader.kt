package com.lee.home

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.framework.utils.Utils
import com.framework.wiget.banner.loader.ImageLoader

/**
 * Created by lichen on 2018/6/6.
 */
class GlideImageLoader : ImageLoader() {
    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
        //具体方法内容自己去选择，次方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择

        if (imageView != null) {
            Glide.with(Utils.getApp())
                    .load(path)
                    .into(imageView)
        }
    }
}