package io.github.zhaomenghuan.alibc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ali.auth.third.ui.context.CallbackContext;
import com.alibaba.baichuan.trade.biz.login.AlibcLogin;
import com.alibaba.baichuan.trade.biz.login.AlibcLoginCallback;

import org.json.JSONArray;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.DHInterface.StandardFeature;
import io.dcloud.common.util.JSUtil;


public class TaobaoLogin extends StandardFeature {
    public void onStart(Context pContext, Bundle pSavedInstanceState, String[] pRuntimeArgs) {

    }

    public AlibcLogin getLoginInstance() {
        return AlibcLogin.getInstance();
    }

    public String isLogin(IWebview pWebview, JSONArray array) {
        return JSUtil.wrapJsVar(getLoginInstance().isLogin()?"true":"false", true);
    }

    public void login(final IWebview pWebview, JSONArray array) {
        final String CallBackID = array.optString(0);

        getLoginInstance().showLogin(new AlibcLoginCallback() {
            @Override
            public void onSuccess(int i) {
                // Toast.makeText(pWebview.getActivity(), "登录成功 ", Toast.LENGTH_LONG).show();
                JSUtil.execCallback(pWebview, CallBackID, "登录成功", JSUtil.OK, false);
            }

            @Override
            public void onFailure(int code, String msg) {
                // Toast.makeText(pWebview.getActivity(), "登录失败", Toast.LENGTH_LONG).show();
                JSUtil.execCallback(pWebview, CallBackID, msg, JSUtil.ERROR, false);
            }
        });
    }

    /**
     * 退出登录
     */
    public void logout(final IWebview pWebview, JSONArray array) {
        final String CallBackID = array.optString(0);

        getLoginInstance().logout(new AlibcLoginCallback() {
            @Override
            public void onSuccess(int i) {
                // Toast.makeText(pWebview.getActivity(), "登出成功", Toast.LENGTH_LONG).show();
                JSUtil.execCallback(pWebview, CallBackID, "登出成功", JSUtil.OK, false);
            }

            @Override
            public void onFailure(int i, String msg) {
                // Toast.makeText(pWebview.getActivity(), "登出失败", Toast.LENGTH_LONG).show();
                JSUtil.execCallback(pWebview, CallBackID, msg, JSUtil.ERROR, false);
            }
        });
    }

    // 登录须重写onActivityResult方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        CallbackContext.onActivityResult(requestCode, resultCode, data);
    }
}
