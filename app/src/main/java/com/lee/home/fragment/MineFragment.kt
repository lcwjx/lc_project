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

/**
 * Created by lichen on 2018/6/6.
 */
class MineFragment : BaseFragment() {
    private lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater!!.inflate(R.layout.fragment_mine, null)
        initView()
        return rootView
    }

    private fun initView() {
        rootView.title.setOnTitleClickListener(object : CusTitleView.OnTitleClickListener {
            override fun onRightClick(view: View?) {
            }

            override fun onLeftClick(view: View?) {
                EventBus.getDefault().post(HomePageEvent(HomePageActivity().TAG_HOME))
            }

        })
    }
}