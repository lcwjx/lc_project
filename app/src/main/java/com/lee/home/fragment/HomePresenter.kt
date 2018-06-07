package com.lee.home.fragment

import com.framework.base.BasePresenter

/**
 * Created by lichen on 2018/6/7.
 */

class HomePresenter<V : HomeContract.View> : BasePresenter<V>(), HomeContract.Presenter<V> {

    private val TITLE = arrayOf("Animation", "MultipleItem", "Header/Footer", "PullToRefresh")
    private val IMG = arrayOf("http://ww4.sinaimg.cn/large/006uZZy8jw1faic1xjab4j30ci08cjrv.jpg",
            "http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg",
            "http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg",
            "http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg")

    override fun getData() {
        val arrayList = ArrayList<String>()
        arrayList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic1xjab4j30ci08cjrv.jpg")
        arrayList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg")
        arrayList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg")
        arrayList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg")
        mvpView.showBanner(arrayList)

        val mDataList = ArrayList<HomeItem>()

        TITLE.indices.mapTo(mDataList) { HomeItem(IMG[it], TITLE[it]) }

        mvpView.showRecyclerView(mDataList)
    }

}
