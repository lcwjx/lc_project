package com.lee.home

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.framework.base.BaseActivity
import com.framework.base.BasePresenter
import com.framework.base.MvpView
import com.lee.R
import com.lee.constant.AppRouterConstants
import com.lee.home.fragment.HomeFragment
import com.lee.home.fragment.MineFragment
import kotlinx.android.synthetic.main.activity_home_page.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by lichen on 2018/6/5.
 */
@Route(path = AppRouterConstants.HOME_PAGE_ACTIVITY)
class HomePageActivity : BaseActivity() {

    val TAG_HOME = "tag_home"
    val TAG_MINE = "tag_mine"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        EventBus.getDefault().register(this)
        initView()

    }

    private fun initView() {
        tabHost.addTab(TAG_HOME, HomeFragment().javaClass, null, R.id.home)
        tabHost.addTab(TAG_MINE, MineFragment().javaClass, null, R.id.mine)
        tabHost.setUp(this, supportFragmentManager, R.id.tabHostIndex, R.id.tabHostView)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: HomePageEvent) {
        changeTab(event.tabTag!!)
    }

    private fun changeTab(tag: String) = when (tag) {
        TAG_HOME -> tabHost.setCurrentTabByTag(TAG_HOME)
        TAG_MINE -> tabHost.setCurrentTabByTag(TAG_MINE)
        else -> tabHost.setCurrentTabByTag(TAG_HOME)


    }
}