package com.lee.login


import com.framework.base.MvpPresenter
import com.framework.base.MvpView
import com.framework.http.model.BaseResponse
import com.trello.rxlifecycle2.LifecycleTransformer

import okhttp3.RequestBody

/**
 * Created by mars.yu on 2017/11/15.
 *
 * @param <V> the type parameter≤≤
</V> */


interface LoginContract {

    interface View : MvpView {

        /**
         * 保存token
         */
        fun setAccessToken(accessToken: String)
    }


    interface Presenter<V : View> : MvpPresenter<V> {
        /**
         * 登录请求
         *
         * @param filedMap the filed map
         * @param lifecycleTransformer the lifecycle transformer
         */
        fun login(filedMap: Map<String, String>, lifecycleTransformer: LifecycleTransformer<BaseResponse<MemberLoginResponse>>)

        /**
         * 发送验证码.
         *
         * @param filedMap the filed map
         * @param lifecycleTransformer the lifecycle transformer
         */
        fun sendSms(filedMap: RequestBody, lifecycleTransformer: LifecycleTransformer<BaseResponse<String>>)

    }

}


