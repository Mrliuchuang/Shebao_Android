package com.example.liuchuang.shebao.Rizhijiku;

/**
 * @author 刘闯
 * @date 2019/3/26 0026.
 * Email：741163103@qq.com
 */

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.liuchuang.shebao.R;
import com.example.liuchuang.shebao.xListView.XListView;

import java.util.HashMap;
import java.util.List;

/**
 * 重写的Adapter
 */
public class MyAdapter extends BaseAdapter {

    Context context;
    List<ImageEntry> mList;
    XListView xlist_lv;
    HashMap<String, Drawable> imgCache;     // 图片缓存
    HashMap<Integer, TagInfo> tag_map;      // TagInfo缓存
    AsyncImageLoader loader;                // 异步加载图片类
    /**
     * 构造函数
     *
     * @param context 上下文
     * @param list    包含了所有要显示的图片的ImageEntry对象的列表
     */
    public MyAdapter(Context context, List<ImageEntry> list) {
        this.context = context;
        this.mList = list;
        imgCache = new HashMap<String, Drawable>();
        loader = new AsyncImageLoader();
        tag_map = new HashMap<Integer, TagInfo>();
    }
    //获取当前items项的大小，也可以看成是数据源的大小
    //getCount决定了listview一共有多少个item
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mList.size();
    }

    //根据item的下标获取到View对象
    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return mList.get(position);
    }

    //获取到items的id,对应onItemClick中long的na参数
    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
//        return mList.get(position).getId();
        return position;
        // return 0;
    }

    //根据传入item的下标，获取到view对象
        /*
         * int position,      表示item所在listView中的下标，也是在数据源中下标所对应的数据
         * View convertView,  缓存机制，当一些item项滑出屏幕的时候，会创建新的View对象，这样会使得内存资源占据，
         *                                  所以使用convertView判断是否为空，如果为空的说明item没有滑出，需要创建新的view对象
         *                          如果不为空，说明已经滑出类屏幕所以使用convertView ，view = convertView，
         *                                  可以把convert 理解为滑出的view对象
         * ViewGroup parent   视图组对象，即 表示当前绘制的items项所属的ListView对象。
         * */
    //getView返回了每个item项所显示的view
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder = null;

        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_rizhijilu, null, false);
            holder = new ViewHolder();
            holder.img = (ImageView) convertView.findViewById(R.id.image_ri);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        String imgurl = mList.get(position).getUrl();   // 得到该项所代表的url地址
        Drawable drawable = imgCache.get(imgurl);       // 先去缓存中找

        TagInfo tag = new TagInfo();
        tag.setPosition(position);  // 保存了当前在adapter中的位置
        tag.setUrl(imgurl);         // 保存当前项所要加载的url
        holder.img.setTag(tag);     // 为ImageView设置Tag，为以后再获取图片后好能找到它
        tag_map.put(position, tag); // 把该TagInfo对应position放入Tag缓存中

        //为列表的文本赋值
        TextView lianxifangshi_ri = (TextView) convertView.findViewById(R.id.lianxifangshi_ri);
        lianxifangshi_ri.setText(mList.get(position).getlianxifangshi());
        TextView shengfenzhenghao_ri = (TextView) convertView.findViewById(R.id.shengfenzhenghao_ri);
        shengfenzhenghao_ri.setText(mList.get(position).getshenfenzhenghao());
        TextView name_ri = (TextView) convertView.findViewById(R.id.name_ri);
        name_ri.setText(mList.get(position).getName());


        if (null != drawable) {                         // 找到了直接设置为图像
            holder.img.setImageDrawable(drawable);
        } else {                                      // 没找到则开启异步线程
            drawable = loader.loadDrawableByTag(tag, new AsyncImageLoader.ImageCallBack() {

                @Override
                public void obtainImage(TagInfo ret_info) {

                    imgCache.put(ret_info.getUrl(), ret_info.getDrawable());    // 首先把获取的图片放入到缓存中
                    // 通过返回的TagInfo去Tag缓存中找，然后再通过找到的Tag来获取到所对应的ImageView
                    ImageView tag_view = (ImageView) xlist_lv.findViewWithTag(tag_map.get(ret_info.getPosition()));
                    Log.i("carter", "tag_view: " + tag_view + " position: " + ret_info.getPosition());
                    if (null != tag_view) {
                        tag_view.setImageDrawable(ret_info.getDrawable());
                        notifyDataSetChanged();//这样可以在fragement中实现 异步更新图片
                    }
                }
            });


            if (null == drawable) { // 如果获取的图片为空，则默认显示一个图片
                holder.img.setImageResource(R.mipmap.ic_launcher);

            }
        }


        return convertView;
    }


    class ViewHolder {
        ImageView img;
    }

    public void SetXListView(XListView XListView) {
        this.xlist_lv = XListView;
    }

}

