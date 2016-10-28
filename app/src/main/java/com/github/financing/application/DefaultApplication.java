package com.github.financing.application;

import android.app.Application;

import com.android.volley.Cache;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;
import com.github.financing.request.SSLCertificateValidation;
import com.github.financing.request.SelfSSLSocketFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;

/**
 * Created by user on 2016/10/20.
 */
public class DefaultApplication extends Application{

    private static DefaultApplication mApplication;
    // 创建http请求队列
    private RequestQueue mDefaultQueue;
    // 创建自定义证书的HTTPS请求队列
    private RequestQueue mSelfSslQueue;
    // 创建默认的HTTPS请求队列
    private RequestQueue mDefalutSslQueue;


    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }

    /**
     *单列模式获取application对象
     */
    public static DefaultApplication getInstance(){
        return mApplication;
    }

    /**
     * 获取http请求队列
     */
    public RequestQueue getDefaultQueue(){
        if(mDefaultQueue == null){
            mDefaultQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mDefaultQueue;
    }

    /**
     * 获取默认证书的HTTPS请求队列
     */
    public RequestQueue getDefaultSslQueue(){
        if(mDefalutSslQueue == null){
            BasicNetwork netWork = new BasicNetwork(new HurlStack());
            Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024);
            mDefalutSslQueue = new RequestQueue(cache, netWork);
            mDefalutSslQueue.start();
            //TODO:校验
            SSLCertificateValidation.trustAllCertificate();
        }
        return mDefalutSslQueue;
    }

    /**
     * 获取自定义证书的HTTPS请求队列
     */
    public RequestQueue getSelfSslQueue(){
        if(mSelfSslQueue == null){
            SSLSocketFactory sslSocketFactory = SelfSSLSocketFactory.getSSLSocketFactory(getApplicationContext());
            BasicNetwork network = new BasicNetwork(new HurlStack(null, sslSocketFactory));
            Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024);
            mSelfSslQueue = new RequestQueue(cache,network);
            mSelfSslQueue.start();

            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
        }
        return mSelfSslQueue;
    }


}
