package com.lee.login

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.framework.base.BaseActivity
import com.framework.http.model.BaseResponse
import com.framework.http.utils.ParamsUtils
import com.framework.listener.RxClickListener
import com.framework.listener.RxView
import com.framework.router.RouterConstants
import com.framework.utils.LogUtils
import com.framework.utils.ToastUtils
import com.lee.R
import kotlinx.android.synthetic.main.activity_login.*
import java.util.HashMap

/**
 * Created by lichen on 2018/6/5.
 */
@Route(path = RouterConstants.LOGIN_ACTIVITY)
class LoginActivity : BaseActivity(), LoginContract.View {


    override fun setAccessToken(accessToken: String) {
        ToastUtils.showCusToast(this, accessToken)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val mPresenter = LoginPresenter<LoginContract.View>()
        mPresenter.onAttach(this)
        RxView.setOnClick(button, RxClickListener {

            val params = HashMap<String, Any>()
            params.put("loginName", "13585269669")
            params.put("registerSource", "102")
            params.put("memberId", "1201711230915527481600590082")
            params.put("ipAdd", "10.55.65.27")
            params.put("loginPwd", "9a84ee41aa72de59c63006aad670bcce")
            params.put("terminalId", "dfdsfdsfsfd")
            LogUtils.i("ssssssss")

            mPresenter.login(ParamsUtils.getRequestBody(params, "member-login", "memberLogin"), this@LoginActivity.bindToLifecycle<BaseResponse<MemberLoginResponse>>())
        })
    }


}