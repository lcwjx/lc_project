package com.lee.agentweb.wiget;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.framework.wiget.banner.SuperSwipeRefreshLayout;
import com.just.agentweb.IWebLayout;
import com.lee.R;


/**
 * Created by cenxiaozhong on 2017/7/1.
 * source code  https://github.com/Justson/AgentWeb
 */

public class WebLayout implements IWebLayout {

    private Activity mActivity;
    private final SuperSwipeRefreshLayout mTwinklingRefreshLayout;
    private WebView mWebView = null;

    public WebLayout(Activity activity) {
        this.mActivity = activity;
        mTwinklingRefreshLayout = (SuperSwipeRefreshLayout) LayoutInflater.from(activity).inflate(R.layout.fragment_twk_web, null);
        mWebView = (WebView) mTwinklingRefreshLayout.findViewById(R.id.webView);
    }

    @NonNull
    @Override
    public ViewGroup getLayout() {
        return mTwinklingRefreshLayout;
    }

    @Nullable
    @Override
    public WebView getWebView() {
        return mWebView;
    }


}
