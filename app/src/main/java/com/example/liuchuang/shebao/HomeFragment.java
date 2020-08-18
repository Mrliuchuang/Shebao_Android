package com.example.liuchuang.shebao;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements View.OnClickListener {


    private ViewPager vp1;
    private ArrayList<Fragment> list;
    private RelativeLayout rel1, rel2, rel3;
    private TextView tv1, tv2, tv3;
    private myf fmAdapter;
    private ViewPager vp;
    private LinearLayout ll;
    private List<ImageView> imageList;
    private ArrayList<ImageView> dotsList;
    private int[] images = {R.mipmap.i, R.mipmap.c, R.mipmap.d, R.mipmap.e, R.mipmap.f, R.mipmap.g, R.mipmap.h};
    public Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            int currentItem = vp.getCurrentItem();
            //切换至下一个页面
            vp.setCurrentItem(++currentItem);
            //再次调用
            handler.sendEmptyMessageDelayed(1, 5000);
        }

        ;
    };

    public static HomeFragment newInstance(String param1) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }
    public HomeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout3, container, false);
        vp = (ViewPager) view.findViewById(R.id.vp);
        ll = (LinearLayout) view.findViewById(R.id.ll);

        //初始化数据
        initImages();
        //初始化小圆点
        initDots();
        //适配器
        ViewPagerIndicator adapter = new ViewPagerIndicator(imageList, handler);
        vp.setAdapter(adapter);
        //初始化vp的位置
        vp.setCurrentItem(imageList.size() * 1000);
        //页面改变监听
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                //遍历小圆点集合
                for (int i = 0; i < dotsList.size(); i++) {
                    if (position % dotsList.size() == i) {//设置当前小圆点
                        dotsList.get(i).setImageResource(R.mipmap.blue);
                    } else {//设置其他小圆点
                        dotsList.get(i).setImageResource(R.mipmap.white);
                    }
                }
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        //两秒发送一个消息
        handler.sendEmptyMessageDelayed(1, 5000);
        list = new ArrayList<Fragment>();
        list.add(new Huodong_Songwennuan());
        list.add(new Huodong_Quwei());
        list.add(new Huodong_shishi());
        // fmAdapter = new myf(getSupportFragmentManager(), list);
        fmAdapter = new myf(getFragmentManager(), list);

        vp1 = (ViewPager) view.findViewById(R.id.vp_huodong);

        vp1.setAdapter(fmAdapter);
        vp1.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int v) {
                int currentItem = vp1.getCurrentItem();
                resetTextView();
                resetRelativeLayout();
                switch (currentItem) {
                    case 0:
                        tv1.setTextColor(ContextCompat.getColor(getActivity(), R.color.zhuse));
                        tv2.setTextColor(ContextCompat.getColor(getActivity(), R.color.zhuse1));
                        tv3.setTextColor(ContextCompat.getColor(getActivity(), R.color.zhuse1));
                        rel1.setBackgroundResource(R.drawable.viewpager_white_left);
                        break;
                    case 1:
                        tv1.setTextColor(ContextCompat.getColor(getActivity(), R.color.zhuse1));
                        tv3.setTextColor(ContextCompat.getColor(getActivity(), R.color.zhuse1));
                        tv2.setTextColor(ContextCompat.getColor(getActivity(), R.color.zhuse));
                        rel2.setBackgroundResource(R.drawable.viewpager_white_right);
                        break;
                    case 2:
                        tv1.setTextColor(ContextCompat.getColor(getActivity(), R.color.zhuse1));
                        tv2.setTextColor(ContextCompat.getColor(getActivity(), R.color.zhuse1));
                        tv3.setTextColor(ContextCompat.getColor(getActivity(), R.color.zhuse));
                        rel3.setBackgroundResource(R.drawable.viewpager_white_three);
                    default:
                        break;
                }

            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        rel1 = (RelativeLayout) view.findViewById(R.id.rel_hd_swn);
        rel2 = (RelativeLayout) view.findViewById(R.id.rel_hd_qw);
        rel3 = view.findViewById(R.id.rel_shisi);
        tv1 = (TextView) view.findViewById(R.id.hd_songwn_tv);
        tv2 = (TextView) view.findViewById(R.id.hd_quwei_tv);
        tv3 = (TextView) view.findViewById(R.id.hd_shishi);


//取消滑动
        vp1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                return true;
            }
        });
        viewpagedianji(0);//加载第几个页面
        rel1.setOnClickListener(this);
        rel2.setOnClickListener(this);
        rel3.setOnClickListener(this);


        return view;
    }


    protected void resetTextView() {
        tv1.setTextColor(Color.WHITE);
        tv2.setTextColor(Color.WHITE);
        tv3.setTextColor(Color.WHITE);
    }

    protected void resetRelativeLayout() {
        rel1.setBackgroundResource(R.drawable.viewpager_button_left);
        rel2.setBackgroundResource(R.drawable.viewpager_button_right);
        rel3.setBackgroundResource(R.drawable.viewpager_button_three);
    }

    private void viewpagedianji(int i) {
        resetRelativeLayout();
        resetTextView();
        switch (i) {
            case 0:
                tv1.setTextColor(ContextCompat.getColor(getActivity(), R.color.zhuse));
                rel1.setBackgroundResource(R.drawable.viewpager_white_left);
                break;
            case 1:
                tv2.setTextColor(ContextCompat.getColor(getActivity(), R.color.zhuse));
                rel2.setBackgroundResource(R.drawable.viewpager_white_right);
                break;
            case 2:
                tv3.setTextColor(ContextCompat.getColor(getActivity(), R.color.zhuse));
                rel3.setBackgroundResource(R.drawable.viewpager_white_three);
                break;
            default:
                break;
        }
        vp1.setCurrentItem(i);
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rel_hd_swn:
                viewpagedianji(0);
                break;
            case R.id.rel_hd_qw:
                viewpagedianji(1);
                break;
            case R.id.rel_shisi:
                viewpagedianji(2);
                break;
            default:
                break;
        }
    }


    class myf extends FragmentPagerAdapter {
        private List<Fragment> list;

        public myf(FragmentManager fm, ArrayList<Fragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }


    private void initDots() {
        //实例化一个集合存放小圆点
        dotsList = new ArrayList<ImageView>();
        for (int i = 0; i < images.length; i++) {
            ImageView view = new ImageView(getActivity());
            //把第一个小圆点设置为选中状态
            if (i == 0) {
                view.setImageResource(R.mipmap.blue);
            } else {
                view.setImageResource(R.mipmap.white);
            }

//          ViewPager.LayoutParams params = new ViewPager.LayoutParams(8,8);
//            params.setMargins(5,0, 5, 0);
            DrawerLayout.LayoutParams params = new DrawerLayout.LayoutParams(20, 20);
            params.setMargins(10, 0, 5, 0);
            //把小圆点添加到布局中
            ll.addView(view, params);
            //把小圆点添加到集合
            dotsList.add(view);
        }
    }

    private void initImages() {
        //实例化一个集合，用于存放图片
        imageList = new ArrayList<ImageView>();
        for (int i = 0; i < images.length; i++) {
            ImageView view = new ImageView(getActivity());
            view.setImageResource(images[i]);
            //添加到集合
            imageList.add(view);
        }
    }
}