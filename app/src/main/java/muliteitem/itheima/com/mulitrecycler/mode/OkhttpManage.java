package muliteitem.itheima.com.mulitrecycler.mode;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import muliteitem.itheima.com.mulitrecycler.interfaces.OnCallbacklistener;


/**
 * @作者 : 杨玉安
 * @日期 : 2017/1/4 0004 7:59
 */

public class OkhttpManage {
    //实例化OkHttpClient
    private OkHttpClient mOkHttpClient;
    private Gson mGson;
    //构造方法的对象引用
    private static OkhttpManage mOkhttpManage;
    private Handler handler;

    //创建私有化构造方法
    private OkhttpManage() {
        //实例化OkHttpClient
        mOkHttpClient = new OkHttpClient();
        //实例化Gson
        mGson = new Gson();
        //实例化出来一个带有主线程轮询器的handler对象
        handler = new Handler(Looper.getMainLooper());
    }

    /**
     * 构造单例模式
     */
    public static OkhttpManage newInstence() {

        if (mOkhttpManage == null) {
            synchronized (OkhttpManage.class) {
                if (mOkhttpManage == null) {
                    mOkhttpManage = new OkhttpManage();
                }
            }
        }
        return mOkhttpManage;
    }


    /**
     * 简单的GET请求数据的封装
     *
     * @param url      接口的URL
     * @param classs   请求网络时解析出来的Bean类对象
     * @param listener 监听请求事件是否正确的接口回调
     * @param <T>      泛型的运用   可以是实现传入不同的实体类  解析出来不同的实体类内容
     */
    public <T> void okHttpRequest(String url, final Class<T> classs, final OnCallbacklistener<T> listener) {
        //实例化Request对象   这里是构建者模式
        final Request request = new Request.Builder().url(url).build();
        Call newCall = mOkHttpClient.newCall(request);
        //加入队列
        newCall.enqueue(new Callback() {

            @Override
            public void onFailure(Request request, IOException e) {
                listener.onFial();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                //获得OKHttp请求的数据，，这里可以的到其他的东西如  流对象
                String gson = response.body().string();
                final T bean = mGson.fromJson(gson.toString(), classs);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onSuccess(bean);
                    }
                });
            }
        });

    }
}
