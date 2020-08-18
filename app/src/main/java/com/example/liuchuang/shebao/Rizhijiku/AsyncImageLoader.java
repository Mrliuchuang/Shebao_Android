package com.example.liuchuang.shebao.Rizhijiku;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;

import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.HashMap;

/**
 * @author 刘闯
 * @date 2019/3/26 0026.
 * Email：741163103@qq.com
 */

public class AsyncImageLoader {
    /**
     * 使用软引用SoftReference，可以由系统在恰当的时候更容易的回收
     */
    private HashMap<String, SoftReference<Drawable>> imageCache;


    public AsyncImageLoader(){
        imageCache = new HashMap<String, SoftReference<Drawable>>();
    }




    /**
     * 通过传入的TagInfo来获取一个网络上的图片
     * @param tag TagInfo对象，保存了position、url和一个待获取的Drawable对象
     * @param callback ImageCallBack对象，用于在获取到图片后供调用侧进行下一步的处理
     * @return drawable 从网络或缓存中得到的Drawable对象，可为null，调用侧需判断
     */
    public Drawable loadDrawableByTag(final TagInfo tag, final ImageCallBack callback){
        Drawable drawable;

        /**
         * 先在缓存中找，如果通过URL地址可以找到，则直接返回该对象
         */
        if(imageCache.containsKey(tag.getUrl())){
            drawable = imageCache.get(tag.getUrl()).get();
            if(null!=drawable){
                return drawable;
            }
        }


        /**
         * 用于在获取到网络图片后，保存图片到缓存，并触发调用侧的处理
         */
        final Handler handler = new Handler(){

            @Override
            public void handleMessage(Message msg) {

                TagInfo info = (TagInfo)msg.obj;
                imageCache.put(info.url, new SoftReference<Drawable>(info.drawable));
                callback.obtainImage(info);

                super.handleMessage(msg);
            }

        };


        /**
         * 如果在缓存中没有找到，则开启一个线程来进行网络请求
         */
        new Thread(new Runnable() {

            @Override
            public void run() {

                TagInfo info = getDrawableIntoTag(tag);
                Message msg = new Message();
                msg.what = 0;
                msg.obj = info;
                handler.sendMessage(msg);
            }
        }).start();

        return null;
    }



    /**
     * 通过传入的TagInfo对象，利用其URL属性，到网络请求图片，获取到图片后保存在TagInfo的Drawable属性中，并返回该TagInfo
     * @param info TagInfo对象，需要利用里面的url属性
     * @return TagInfo 传入的TagInfo对象，增加了Drawable属性后返回
     */
    public TagInfo getDrawableIntoTag(TagInfo info){
        URL request;
        InputStream input;
        Drawable drawable = null;

        try{
            request = new URL(info.getUrl());
            input = (InputStream)request.getContent();
            drawable = Drawable.createFromStream(input, "src"); // 第二个属性可为空，为DEBUG下使用，网上的说明
        }
        catch(Exception e){
            e.printStackTrace();
        }

        info.drawable = drawable;
        return info;
    }



    /**
     * 获取图片的回调接口，里面的obtainImage方法在获取到图片后进行调用
     */
    //interface类是让别的类继承的，如果没有类继承就没意义了，所以你不能用private(私有的)、protected(受保护的)来修饰它。如果修饰了别的类都没法继承它啦，就没意义啦interface类是让别的类继承的，如果没有类继承就没意义了，所以你不能用private(私有的)、protected(受保护的)来修饰它。如果修饰了别的类都没法继承它啦，就没意义啦
    interface ImageCallBack{
        /**
         * 获取到图片后在调用侧执行具体的细节
         * @param info TagInfo对象，传入的info经过处理，增加Drawable属性，并返回给传入者
         */
        public void obtainImage(TagInfo info);
    }



}

