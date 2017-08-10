package io.github.zhaomenghuan.alibc;

import android.app.Application;
import android.util.Log;

import com.alibaba.baichuan.android.trade.AlibcTradeSDK;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeInitCallback;

/**
 * 阿里百川电商
 * 项目名称：阿里巴巴电商SDK
 * 开发团队：阿里巴巴百川商业化团队
 * 阿里巴巴电商SDK答疑群号：1200072507
 * <p/>
 * 功能说明：全局application
 */
public class AliSdkApplication extends Application {
    private static String TAG = "App";
    public static AliSdkApplication application = null;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        //电商SDK初始化
        AlibcTradeSDK.asyncInit(this, new AlibcTradeInitCallback() {
            @Override
            public void onSuccess() {
                Log.i(TAG,"初始化成功");
            }

            @Override
            public void onFailure(int code, String msg) {
                Log.i(TAG,"初始化失败,错误码="+code+" / 错误消息="+msg);
            }
        });
    }
}