package com.example.liuchuang.shebao;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.liuchuang.shebao.shebaochaxun.yanglaobaoxian;


public class ScanFragment extends Fragment implements View.OnClickListener {
    public static ScanFragment newInstance(String param1) {
        ScanFragment fragment = new ScanFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public ScanFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout2, container, false);
        ImageView shipintonghua = view.findViewById(R.id.bt_shipintonghua);

       LinearLayout yanglaobaoxian1 = view.findViewById(R.id.yanglaobaoxian);
        LinearLayout yiliaobaoxian = view.findViewById(R.id.yiliaobaoxian);
        LinearLayout shiyebaoxian = view.findViewById(R.id.shiyebaoxian);
        LinearLayout gongshangbaoxian = view.findViewById(R.id.gongshangbaoxian);
        LinearLayout shengyubaoxian = view.findViewById(R.id.shengyubaoxian);
        LinearLayout zhufangjijin = view.findViewById(R.id.zhufangjijin);


        yiliaobaoxian.setOnClickListener(this);
        shiyebaoxian.setOnClickListener(this);
        gongshangbaoxian.setOnClickListener(this);
        shengyubaoxian.setOnClickListener(this);
        zhufangjijin.setOnClickListener(this);
        yanglaobaoxian1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),yanglaobaoxian.class);
                startActivity(intent);

            }
        });
        shipintonghua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(getActivity(),Renzhengxinxitijiao.class);
//                startActivity(intent);
                Intent intent=new Intent(getActivity(),Renzhengxinxitijiao.class);
                startActivity(intent);
            }
        });
        Bundle bundle = getArguments();
        String agrs1 = bundle.getString("agrs1");
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.yiliaobaoxian:
                Intent intent = new Intent(getActivity(),yanglaobaoxian.class);
                startActivity(intent);
                break;
            case  R.id.shiyebaoxian:
                Intent intent1 = new Intent(getActivity(),yanglaobaoxian.class);
                startActivity(intent1);
                break;
            case  R.id.gongshangbaoxian:
                Intent intent2 = new Intent(getActivity(),yanglaobaoxian.class);
                startActivity(intent2);
                break;
            case  R.id.shengyubaoxian:
                Intent intent3 = new Intent(getActivity(),yanglaobaoxian.class);
                startActivity(intent3);
                break;
            case  R.id.zhufangjijin:
                Intent intent4 = new Intent(getActivity(),yanglaobaoxian.class);
                startActivity(intent4);
                break;
        }
    }
}