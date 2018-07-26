package com.zhaoguanche.tankerApp.inhouse;

import android.app.Application;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

/**
 * Created by wangfei on 2018/1/23.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //设置LOG开关，默认为false
        UMConfigure.setLogEnabled(true);
        //初始化组件化基础库, 统计SDK/推送SDK/分享SDK都必须调用此初始化接口
        UMConfigure.init(this, "5b04f863a40fa34ab9000253", "share", UMConfigure.DEVICE_TYPE_PHONE,"");
    }

    {
        //微信
        //PlatformConfig.setWeixin("wx51b2f44500d354b2", "2d04642e6f159e8546dec29de2b906de");//我的测试账号
        PlatformConfig.setWeixin("wx249c978c39903b95", "467d87ca73fd592a34839f054a16ea9c");//正式
    }
}
