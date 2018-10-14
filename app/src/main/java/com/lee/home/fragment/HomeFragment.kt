package com.lee.home.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.framework.base.BaseFragment
import com.framework.router.RouterConstants
import com.framework.router.RouterUtils
import com.framework.utils.ToastUtils
import com.framework.utils.Utils
import com.framework.wiget.banner.BannerConfig
import com.framework.wiget.banner.listener.OnBannerListener
import com.lee.R
import com.lee.constant.AppRouterConstants
import com.lee.home.GlideImageLoader
import kotlinx.android.synthetic.main.fragment_home.view.*

/**
 * Created by lichen on 2018/6/6.
 */
class HomeFragment : BaseFragment(), HomeContract.View, OnBannerListener {


    override fun showRecyclerView(data: ArrayList<HomeItem>) {
        val homeAdapter = HomeAdapter(R.layout.home_item_view, data)
        homeAdapter.openLoadAnimation()
        homeAdapter.setOnItemClickListener { _, _, position ->
            ToastUtils.showCusToast(Utils.getApp(), "" + position)
        }

        rootView.recycleListView.adapter = homeAdapter
    }

    override fun showBanner(data: ArrayList<String>) {
        rootView.banner
                .setImages(data)
                .setBannerTitles(data)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE)
                .setImageLoader(GlideImageLoader())
                .setOnBannerListener(this)
                .start()
    }


    override fun onBannerClick(position: Int) {
        ToastUtils.showCusToast(Utils.getApp(), "" + position)
        when (position) {
            0 -> RouterUtils.navigation(AppRouterConstants.MAIN_ACTIVITY)
            1 -> RouterUtils.navigation(AppRouterConstants.BROWSER_ACTIVITY)
            2 -> RouterUtils.navigation(RouterConstants.LOGIN_ACTIVITY)
        }
    }

    private lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater!!.inflate(R.layout.fragment_home, null)
        val homePresenter = HomePresenter<HomeContract.View>()
        homePresenter.onAttach(this)
        initView(homePresenter)
        return rootView
    }

    private fun initView(homePresenter: HomePresenter<HomeContract.View>) {
        rootView.recycleListView.isNestedScrollingEnabled = false
        rootView.recycleListView.layoutManager = GridLayoutManager(Utils.getApp(), 2)
        homePresenter.getData()
    }
}