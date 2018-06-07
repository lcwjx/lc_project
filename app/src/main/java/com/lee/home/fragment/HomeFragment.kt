package com.lee.home.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.framework.base.BaseFragment
import com.framework.utils.ToastUtils
import com.framework.utils.Utils
import com.framework.wiget.banner.BannerConfig
import com.framework.wiget.banner.listener.OnBannerListener
import com.lee.R
import com.lee.home.GlideImageLoader
import kotlinx.android.synthetic.main.fragment_home.view.*

/**
 * Created by lichen on 2018/6/6.
 */
class HomeFragment : BaseFragment(), OnBannerListener {
    private val TITLE = arrayOf("Animation", "MultipleItem", "Header/Footer", "PullToRefresh")
    private val IMG = arrayOf("http://ww4.sinaimg.cn/large/006uZZy8jw1faic1xjab4j30ci08cjrv.jpg",
            "http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg",
            "http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg",
            "http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg")
    val mDataList = ArrayList<HomeItem>()
    override fun onBannerClick(position: Int) {
        ToastUtils.showCusToast(Utils.getApp(), "" + position)
    }

    private lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater!!.inflate(R.layout.fragment_home, null)
        initView()
        initData()
        initAdapter()
        return rootView

    }

    private fun initView() {
        rootView.recycleListView.layoutManager = GridLayoutManager(Utils.getApp(), 1)

        val arrayList = ArrayList<String>()
        arrayList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic1xjab4j30ci08cjrv.jpg")
        arrayList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg")
        arrayList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg")
        arrayList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg")
        rootView.banner
                .setImages(arrayList)
                .setBannerTitles(arrayList)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE)
                .setImageLoader(GlideImageLoader())
                .setOnBannerListener(this)
                .start()
    }

    private fun initAdapter() {
        val homeAdapter = HomeAdapter(R.layout.home_item_view, mDataList)
        homeAdapter.openLoadAnimation()
        homeAdapter.setOnItemClickListener { _, _, position ->
            ToastUtils.showCusToast(Utils.getApp(), "" + position)
        }

        rootView.recycleListView.adapter = homeAdapter
    }

    private fun initData() {
        for (i in TITLE.indices) {
            val item = HomeItem(IMG[i], TITLE[i])

            mDataList.add(item)
        }
    }
}