package com.example.liuchuang.shebao.zixun;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.liuchuang.shebao.R;

import java.util.List;

public class Myadapter1 extends BaseAdapter {
    private List<ItemBean1> mDataList;
    private LayoutInflater mLayoutInflater;


    public Myadapter1(Context context, List<ItemBean1> list) {
        mLayoutInflater = LayoutInflater.from(context);
        mDataList = list;

    }
    @Override
    public int getCount() {
        return mDataList.size();
    }
    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            // 由于我们只需要将XML转化为View，并不涉及到具体的布局，所以第二个参数通常设置为null
//            convertView = mLayoutInflater.inflate(R.layout.item, null);
            convertView = mLayoutInflater.inflate(R.layout.zixun_minsheng, null);
        }

        TextView name_rizhi = (TextView) convertView.findViewById(R.id.minsheng_title);
        TextView lianxifangshi_rizhi = (TextView) convertView.findViewById(R.id.trend);
        TextView renyuanleixing_rizhi = (TextView) convertView.findViewById(R.id.renyuanleixing_rizhi);
        // 取出bean对象
        ItemBean1 bean = mDataList.get(position);
        // 设置控件的数据

        name_rizhi.setText(bean.name_rizhi);
        lianxifangshi_rizhi.setText(bean.lianxifangshi_rizhi);
        renyuanleixing_rizhi.setText(bean.renyuanleixing_rizhi);
        return convertView;
    }
}