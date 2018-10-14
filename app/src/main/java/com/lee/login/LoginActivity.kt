package com.lee.login

import android.os.Bundle
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.framework.base.BaseActivity
import com.framework.http.model.BaseResponse
import com.framework.http.utils.ParamsUtils
import com.framework.listener.RxView
import com.framework.router.RouterConstants
import com.framework.utils.DeviceUtils
import com.framework.utils.LogUtils
import com.framework.utils.NetworkUtils
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
        RxView.setOnClick(button, {
            val params = HashMap<String, Any>()
            params.put("loginName", "13585269669")
            params.put("registerSource", "102")
            params.put("memberId", "1201711230915527481600590082")
            params.put("ipAdd", NetworkUtils.getIPAddress(true))
            params.put("loginPwd", "9a84ee41aa72de59c63006aad670bcce")
            params.put("terminalId", DeviceUtils.getAndroidID())
            mPresenter.login(ParamsUtils.packParams(params, "member-login", "memberLogin"), this@LoginActivity.bindToLifecycle<BaseResponse<MemberLoginResponse>>())
        })
        RxView.setOnClick(finger, {
            val manager = FingerprintManagerCompat.from(this)
            manager.authenticate(null, 0, null, MyCallBack(), null)
        })
    }


    class MyCallBack : FingerprintManagerCompat.AuthenticationCallback() {
        private val TAG = "MyCallBack"

        // 当出现错误的时候回调此函数，比如多次尝试都失败了的时候，errString是错误信息
        override fun onAuthenticationError(errMsgId: Int, errString: CharSequence?) {
            LogUtils.d(TAG, "onAuthenticationError: $errString")
        }

        // 当指纹验证失败的时候会回调此函数，失败之后允许多次尝试，失败次数过多会停止响应一段时间然后再停止sensor的工作
        override fun onAuthenticationFailed() {
            LogUtils.d(TAG, "onAuthenticationFailed: " + "验证失败");
            //  Toast.makeText(getApplicationContext(), "指纹错误", Toast.LENGTH_LONG).show();
        }

        override fun onAuthenticationHelp(helpMsgId: Int, helpString: CharSequence?) {
            LogUtils.d(TAG, "onAuthenticationHelp: $helpString");
        }

        // 当验证的指纹成功时会回调此函数，然后不再监听指纹sensor
        override fun onAuthenticationSucceeded(result: FingerprintManagerCompat.AuthenticationResult?) {

            //Toast.makeText(getApplicationContext(), "识别成功", Toast.LENGTH_LONG).show();
            LogUtils.d(TAG, "onAuthenticationSucceeded: " + "验证成功")
        }
    }
}