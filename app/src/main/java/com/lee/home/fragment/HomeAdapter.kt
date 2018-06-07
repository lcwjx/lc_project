package com.lee.home.fragment

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.framework.utils.Utils
import com.lee.R

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
class HomeAdapter(layoutResId: Int, data: List<HomeItem>?) : BaseQuickAdapter<HomeItem, BaseViewHolder>(
        layoutResId,
        data
) {

    override fun convert(helper: BaseViewHolder, item: HomeItem) {
        helper.setText(R.id.text, item.title)


        Glide.with(Utils.getApp())
                .load(item.imageResource)
                .into(helper.getView(R.id.icon))

    }
}
