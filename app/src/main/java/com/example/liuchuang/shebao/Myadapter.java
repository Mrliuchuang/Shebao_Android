package com.example.liuchuang.shebao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
public class Myadapter extends BaseAdapter {
    private List<ItemBean> mDataList;
    private LayoutInflater mLayoutInflater;


    public Myadapter(Context context, List<ItemBean> list) {
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
            convertView = mLayoutInflater.inflate(R.layout.item, null);
        }
//        ImageView itemImage = (ImageView) convertView.findViewById(R.id.iv_image);
        TextView itemTitle = (TextView) convertView.findViewById(R.id.tv_title);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView itemContent = (TextView) convertView.findViewById(R.id.tv_content);
        // 取出bean对象
        ItemBean bean = mDataList.get(position);
        // 设置控件的数据
        name.setText(bean.itemImageResid);
        itemTitle.setText(bean.itemTitle);
        itemContent.setText(bean.itemContent);
        return convertView;
    }
}