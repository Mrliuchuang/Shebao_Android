package com.example.liuchuang.shebao.Adapter;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2017/12/8.
 */

public class ItemBean1 {
    public int itemImageResid;
    public String name_rizhi;
    public String zhuangtai_rizhi;
    public String shengfenzhenghao_rizhi;
    public String lianxifangshi_rizhi;
    public String renyuanleixing_rizhi;
    private String imageUrl;

    public ItemBean1(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public ItemBean1(int itemImageResid, String name_rizhi, String zhuangtai_rizhi, String shengfenzhenghao_rizhi, String lianxifangshi_rizhi, String renyuanleixing_rizhi) {
        this.itemImageResid = itemImageResid;
        this.name_rizhi = name_rizhi;
        this.zhuangtai_rizhi = zhuangtai_rizhi;
        this.shengfenzhenghao_rizhi = shengfenzhenghao_rizhi;
        this.lianxifangshi_rizhi = lianxifangshi_rizhi;
        this.renyuanleixing_rizhi = renyuanleixing_rizhi;
    }

}