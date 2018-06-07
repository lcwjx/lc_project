package com.lee

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.framework.base.BaseActivity
import com.framework.router.RouterUtils
import com.framework.utils.RxTimerUtil
import com.lee.constant.AppRouterConstants

/**
 * Created by lichen on 2018/6/5.
 */
@Route(path = AppRouterConstants.LAUNCH_ACTIVITY)
class LaunchActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        RxTimerUtil.timer(2000) {
            RouterUtils.navigation(AppRouterConstants.HOME_PAGE_ACTIVITY)
            finish()
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(com.framework.R.anim.cmn_slide_right_in, com.framework.R.anim.cmn_slide_left_out)
    }


}