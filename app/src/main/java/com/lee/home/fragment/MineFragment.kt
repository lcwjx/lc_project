package com.lee.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.framework.base.BaseFragment
import com.framework.wiget.CusTitleView
import com.lee.R
import com.lee.home.HomePageActivity
import com.lee.home.HomePageEvent
import kotlinx.android.synthetic.main.fragment_mine.view.*
import org.greenrobot.eventbus.EventBus
import android.widget.LinearLayout
import com.framework.utils.ConvertUtils
import com.just.agentweb.AgentWeb


/**
 * Created by lichen on 2018/6/6.
 */
class MineFragment : BaseFragment() {
    private lateinit var rootView: View
    private lateinit var mAgentWeb: AgentWeb


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater!!.inflate(R.layout.fragment_mine, null)
        initView()
        return rootView
    }

    private fun initView() {
        rootView.smarkLayout.isEnableLoadMore = false


        rootView.title.setOnTitleClickListener(object : CusTitleView.OnTitleClickListener {
            override fun onRightClick(view: View?) {
            }

            override fun onLeftClick(view: View?) {
                EventBus.getDefault().post(HomePageEvent(HomePageActivity().TAG_HOME))
            }

        })
        val layoutParams = LinearLayout.LayoutParams(-1, -1)
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(rootView.linearLayout as LinearLayout, layoutParams)
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go("https://rich.hopewoo.com/#/index")
//                .go("http://www.jd.com")
    }
}