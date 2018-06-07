package com.lee

import com.alibaba.android.arouter.launcher.ARouter
import com.framework.base.BaseApplication
import com.framework.constant.BaseUrlEnum
import com.framework.prefs.CmnSpHelper
import com.framework.utils.App

/**
 * Created by lichen on 2018/6/5.
 */
 class LCApplication : BaseApplication(){

    override fun onCreate() {
        super.onCreate()
        ARouter.openLog()  // 开启日志
        ARouter.printStackTrace() // 打印日志的时候打印线程堆栈
        ARouter.openDebug()
        ARouter.init(this)
        initEnvironmentParams()

    }

    private fun initEnvironmentParams() {

        CmnSpHelper.getInstance(this).initBaseUrlType(BuildConfigs.BASE_URL)
        App.setUrlType(CmnSpHelper.getInstance(this).baseUrlType)
        val info = BaseUrlEnum.getEnvironmentParamsInfo(CmnSpHelper.getInstance(this).baseUrlType)
        CmnSpHelper.getInstance(this).initBaseUrl(info.baseApiUrl)
    }
}