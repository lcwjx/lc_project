package com.lee.home.fragment

import com.framework.base.MvpPresenter
import com.framework.base.MvpView

/**
 * Created by lichen on 2018/6/7.
 */
interface HomeContract {

    interface View : MvpView {

        fun showBanner(data: ArrayList<String>)

        fun showRecyclerView(data: ArrayList<HomeItem>)

    }

    interface Presenter<V : View> : MvpPresenter<V> {

        fun getData()

    }
}