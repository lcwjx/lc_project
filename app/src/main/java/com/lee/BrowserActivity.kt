package com.lee

import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import com.alibaba.android.arouter.facade.annotation.Route
import com.framework.base.BaseActivity
import com.framework.base.BasePresenter
import com.framework.base.MvpView
import com.framework.utils.LogUtils
import com.framework.utils.StringUtils
import com.github.lzyzsd.jsbridge.BridgeHandler
import com.github.lzyzsd.jsbridge.BridgeWebView
import com.github.lzyzsd.jsbridge.BridgeWebViewClient
import com.github.lzyzsd.jsbridge.CallBackFunction
import com.just.agentweb.AgentWeb
import com.lee.constant.AppRouterConstants
import kotlinx.android.synthetic.main.activity_browser.*
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by lichen on 2018/6/12.
 */
@Route(path = AppRouterConstants.BROWSER_ACTIVITY)
class BrowserActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browser)
        initView()
    }

    private fun initView() {
        smarkLayout.isEnableLoadMore = false

        val bridgeWebView = BridgeWebView(this)
        val agentWebParent = AgentWeb.with(this)
                .setAgentWebParent(linearLayout, LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
                .useDefaultIndicator(-1, 2)//
                .setWebViewClient(BridgeWebViewClient(bridgeWebView))
                .setWebView(bridgeWebView)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .createAgentWeb()//
                .ready()//
                .go("https://rich.hopewoo.com/#/index")

        bridgeWebView.registerHandler("jsCallNative", object : BridgeHandler {

            private var obj: Any? = null

            override fun handler(data: String, function: CallBackFunction) {

                if (StringUtils.isEmpty(data)) {
                    function.onCallBack("参数为空")
                    return
                }

                var paramsObj: JSONObject? = null
                try {
                    paramsObj = JSONObject(data)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }

                val methodName = paramsObj!!.optString("methodName")
                val param = paramsObj.optString("params")

                LogUtils.i("methodName:" + methodName)

                val callBackObj = JSONObject()

//                if (!StringUtil.isEmpty(methodName)) {
//                    if (StringUtil.isEmpty(param)) {
//                        obj = ReflectManager.invokeMethod(mContext, "js" + methodName, null, function)
//                    } else {
//                        obj = ReflectManager.invokeMethod(mContext, "js" + methodName, param, function)
//                    }
//
//                    try {
//                        callBackObj.put("callbackName", methodName)
//                        callBackObj.put("parameter", obj)
//                    } catch (e: JSONException) {
//                        e.printStackTrace()
//                    }
//
//                } else {
//                    function.onCallBack("参数为空")
//                }
                if (obj != null) {
                    LogUtils.e(callBackObj.toString())
                    function.onCallBack(callBackObj.toString())
                }
            }
        })
    }


}