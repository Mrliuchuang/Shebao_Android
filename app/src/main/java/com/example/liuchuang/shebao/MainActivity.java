package com.example.liuchuang.shebao;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import java.lang.reflect.Field;

import static org.xutils.common.util.DensityUtil.dip2px;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener, View.OnClickListener {

    private BottomNavigationBar bottomNavigationBar;
    int lastSelectedPosition = 0;
    private String TAG = MainActivity.class.getSimpleName();
    private MyFragment mMyFragment;
    private ScanFragment mScanFragment;
    private HomeFragment mHomeFragment;
    private TextView daohang;
    private ImageView xiaoxi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        daohang = findViewById(R.id.daohang);
        xiaoxi = findViewById(R.id.xiaoxi);
        xiaoxi.setOnClickListener(this);



        /**
         * bottomNavigation 设置
         */

        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);

        /** 导航基础设置 包括按钮选中效果 导航栏背景色等 */
        bottomNavigationBar
                .setTabSelectedListener(this)
                .setMode(BottomNavigationBar.MODE_FIXED)
//                /**
//                 *  setMode() 内的参数有三种模式类型：
//                 *  MODE_DEFAULT 自动模式：导航栏Item的个数<=3 用 MODE_FIXED 模式，否则用 MODE_SHIFTING 模式
//                 *  MODE_FIXED 固定模式：未选中的Item显示文字，无切换动画效果。
//                 *  MODE_SHIFTING 切换模式：未选中的Item不显示文字，选中的显示文字，有切换动画效果。
//                 */
//
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
//                /**
//                 *  setBackgroundStyle() 内的参数有三种样式
//                 *  BACKGROUND_STYLE_DEFAULT: 默认样式 如果设置的Mode为MODE_FIXED，将使用BACKGROUND_STYLE_STATIC
//                 *                                    如果Mode为MODE_SHIFTING将使用BACKGROUND_STYLE_RIPPLE。
//                 *  BACKGROUND_STYLE_STATIC: 静态样式 点击无波纹效果
//                 *  BACKGROUND_STYLE_RIPPLE: 波纹样式 点击有波纹效果
//                 */

                .setActiveColor("#4286f5") //选中颜色
                .setInActiveColor("#90a4ad") //未选中颜色
                .setBarBackgroundColor("#f5f5f5");//导航栏背景色


        /** 添加导航按钮 */
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.shouye, "便民"))
                .addItem(new BottomNavigationItem(R.mipmap.aixin1, "爱心认证"))
                .addItem(new BottomNavigationItem(R.mipmap.gerenzhongxin, "个人中心"))
                .setFirstSelectedPosition(lastSelectedPosition)
                .initialise(); //initialise 一定要放在 所有设置的最后一项

        setDefaultFragment();//设置默认导航栏

    }

    /**
     * 设置默认导航栏
     */
    private void setDefaultFragment() {
//        FragmentManager fm = getFragmentManager();
//        FragmentTransaction transaction = fm.beginTransaction();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mHomeFragment = HomeFragment.newInstance("首页");
        transaction.replace(R.id.tb, mHomeFragment);
        transaction.commit();
    }

    /**
     * 设置导航选中的事件
     */
    @Override
    public void onTabSelected(int position) {
        Log.d(TAG, "onTabSelected() called with: " + "position = [" + position + "]");
//        FragmentManager fm = this.getFragmentManager();
        FragmentManager fm = this.getSupportFragmentManager();
        //开启事务
        FragmentTransaction transaction = fm.beginTransaction();
        switch (position) {
            case 0:
                if (mHomeFragment == null) {
                    mHomeFragment = HomeFragment.newInstance("便民");
                }
                transaction.replace(R.id.tb, mHomeFragment);
                daohang.setText("便民");
                break;
            case 1:
                if (mScanFragment == null) {
                    mScanFragment = ScanFragment.newInstance("爱心认证");
                }
                transaction.replace(R.id.tb, mScanFragment);
                daohang.setText("爱心认证");
                break;
            case 2:
                if (mMyFragment == null) {
                    mMyFragment = MyFragment.newInstance("个人中心");
                }
                transaction.replace(R.id.tb, mMyFragment);
                daohang.setText("个人中心");
                break;
            default:
                break;
        }

        transaction.commit();// 事务提交
    }

    /**
     * 设置未选中Fragment 事务
     */
    @Override
    public void onTabUnselected(int position) {

    }

    /**
     * 设置释放Fragment 事务
     */
    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.xiaoxi:
                PopWindow popWindow=new PopWindow(this);
                popWindow.showPopupWindow(xiaoxi);
                break;
            default:
                break;
        }
    }



}