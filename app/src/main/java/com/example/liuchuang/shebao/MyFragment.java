package com.example.liuchuang.shebao;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.liuchuang.shebao.Rizhijiku.Renzhengrizhijilu;
import com.example.liuchuang.shebao.login.Login;
import com.example.liuchuang.shebao.login.welcome;


public class MyFragment extends Fragment {
private RelativeLayout chengyuan;
    private RelativeLayout tologin;
    private RelativeLayout gerenxinxi;
    private RelativeLayout renzhengrizhi;
    private RelativeLayout gongzuodi;
    public static MyFragment newInstance(String param1) {
        MyFragment fragment = new MyFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public MyFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout1, container, false);
        chengyuan = view.findViewById(R.id.jiatingchengyuanxinxi);
        gerenxinxi = view.findViewById(R.id.gerenxinxishezhi);
        gongzuodi = view.findViewById(R.id.gongzuodi);
        gongzuodi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "您未添加工作地与常驻地！", Toast.LENGTH_SHORT).show();
            }
        });
        gerenxinxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "您未设置个人信息！", Toast.LENGTH_SHORT).show();
            }
        });
        tologin = view.findViewById(R.id.tologin);
        tologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);
            }
        });
        renzhengrizhi =view.findViewById(R.id.renzhengrizhi);
        chengyuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Chengyuanxinxi.class);
                startActivity(intent
                );
            }
        });
        renzhengrizhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getActivity(), Renzhengrizhijilu.class);
                startActivity(intent1);
            }
        });
        return view;
    }

}