package com.he.skt.kotlin.xdemo;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import androidx.annotation.NonNull;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;


public class MyApp extends Application {
    private static MyApp mContext;
    public static MyApp getInstance() {
        return mContext;
    }
    /**
     * 全局初始化下拉加载上拉刷新style
     */
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
                //return new MyRefreshHeader(context);//自定义
                layout.setPrimaryColorsId(R.color.colorPrimaryDark, android.R.color.white);//全局设置主题颜色
                return new MaterialHeader(context).setColorSchemeColors(mContext.getResources().getColor(R.color.colorPrimaryDark));
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout refreshLayout) {

                refreshLayout.setEnableLoadMoreWhenContentNotFull(false);//不足一页不刷新
                 return new ClassicsFooter(context);
                // return new MyRefreshFooter(context);
            }
        });
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;

    }


}
