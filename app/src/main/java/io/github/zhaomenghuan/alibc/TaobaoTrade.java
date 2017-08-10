package io.github.zhaomenghuan.alibc;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.alibaba.baichuan.android.trade.AlibcTrade;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeCallback;
import com.alibaba.baichuan.android.trade.model.AlibcShowParams;
import com.alibaba.baichuan.android.trade.model.OpenType;
import com.alibaba.baichuan.android.trade.page.AlibcPage;
import com.alibaba.baichuan.trade.biz.context.AlibcResultType;
import com.alibaba.baichuan.trade.biz.context.AlibcTradeResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.DHInterface.StandardFeature;
import io.dcloud.common.util.JSUtil;


public class TaobaoTrade extends StandardFeature {
    public void onStart(Context pContext, Bundle pSavedInstanceState, String[] pRuntimeArgs) {

    }

    /**
     * 打开指定链接
     * @param pWebview
     * @param array
     */
    public void openWindow(final IWebview pWebview, JSONArray array) {
        final Activity activity = pWebview.getActivity();
        final String CallBackID = array.optString(0);
        String url = array.optString(1);

        if(TextUtils.isEmpty(url)) {
            Toast.makeText(activity, "URL为空", Toast.LENGTH_SHORT).show();
            return;
        };

        AlibcShowParams alibcShowParams = new AlibcShowParams(OpenType.H5, false);
        AlibcTrade.show(activity, new AlibcPage(url), alibcShowParams, null, null , new AlibcTradeCallback() {
            @Override
            public void onTradeSuccess(AlibcTradeResult tradeResult) {
                //当addCartPage加购成功和其他page支付成功的时候会回调
                if(tradeResult.resultType.equals(AlibcResultType.TYPECART)){
                    // 加购成功
                    // Toast.makeText(context, "加购成功", Toast.LENGTH_SHORT).show();
                    // JSUtil.execCallback(pWebview, CallBackID, "登出成功", JSUtil.OK, false);
                }else if (tradeResult.resultType.equals(AlibcResultType.TYPEPAY)){
                    // 支付成功
                    // Toast.makeText(activity, "支付成功,成功订单号为"+tradeResult.payResult.paySuccessOrders, Toast.LENGTH_SHORT).show();
                    JSUtil.execCallback(pWebview, CallBackID, tradeResult.payResult.paySuccessOrders.toString(), JSUtil.OK, false);
                }
            }

            @Override
            public void onFailure(int errCode, String errMsg) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.putOpt("errCode", errCode);
                    jsonObject.putOpt("errMsg", errMsg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // Toast.makeText(activity, "电商SDK出错,错误码="+errCode+" / 错误消息="+errMsg, Toast.LENGTH_SHORT).show();
                JSUtil.execCallback(pWebview, CallBackID, jsonObject, JSUtil.ERROR, false);
            }
        });
    }

    /**
     * 打开淘宝详情
     * @param pWebview
     * @param array
     */
    public void openDetail(final IWebview pWebview, JSONArray array) {
        openWindow(pWebview, array);
    }
}