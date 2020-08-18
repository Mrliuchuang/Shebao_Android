package com.example.liuchuang.shebao;


import android.app.ProgressDialog;

import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.text.TextUtils;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.example.liuchuang.shebao.utius.CacheUtils;
import com.example.liuchuang.shebao.zixun.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Huodong_Quwei extends Fragment {
    private ListView listView2;
    List<ItemBean1> datelist_shishi = new ArrayList<>();
    ProgressDialog dia;
    HashMap<Integer, String> hashMap_shishi= new HashMap<Integer, String>();
    String idcar1;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.huodong_qw, container, false);
        listView2 = view.findViewById(R.id.two);
        dia = new ProgressDialog(getActivity());
        dia.setMessage("正在加载数据。。。。。。");
        dia.show();
        getdatanet();

//        //得到缓存数据
//        String saveJson = CacheUtils.getString(getActivity(),"http://api.shujuzhihui.cn/api/hot/list2");
//        if(!TextUtils.isEmpty(saveJson)){
//            procexssdat1(saveJson);
//        }else {
//
//            dia = new ProgressDialog(getActivity());
//            dia.setMessage("正在加载数据。。。。。。");
//            dia.show();
//            getdatanet();
//
//        }

        // Toast.makeText(getActivity(), a, Toast.LENGTH_SHORT).show();
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String urla = hashMap_shishi.get(i);
                Intent intent = new Intent(getActivity(), zixun_content.class);
                Bundle bundle=new Bundle();
                bundle.putString("ID", urla);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
        return view;
    }

    private void getdatanet() {
        RequestParams params = new RequestParams("http://v.juhe.cn/toutiao/index");
        params.addParameter("key", "73a693d0903fab9b00f38c30101cd8c7");
        params.addParameter("type", "guonei");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //缓存数据
                //CacheUtils.putString(getActivity(), "http://api.shujuzhihui.cn/api/hot/list2",result);
                procexssdat1(result);
                dia.dismiss();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });




    }

    private void procexssdat1(String json) {
//        Gson gson = new Gson();
//        minsheng jsonbean = gson.fromJson(json, minsheng.class);
//        for (int i=0;i<45;i++){
//            String name = jsonbean.getRESULT().get(i).getKeyword();
//            String serche = jsonbean.getRESULT().get(i).getSearchNum();
//            idcar = jsonbean.getRESULT().get(i).getId();
//            hashMap.put(i, idcar);
//            datelist_minsheng.add(new ItemBean1(name, "热度"+serche, "民生热点"));
//            Myadapter1 myadapter = new Myadapter1(getActivity(), datelist_minsheng);
//            listView1.setAdapter(myadapter);
        //  }
        try {
            JSONObject jsonObject1 = new JSONObject(json);

            JSONObject two = jsonObject1.getJSONObject("result");
            JSONArray jsonArray1=two.getJSONArray("data");
            for (int i = 0; i < jsonArray1.length(); i++){
                JSONObject lan1 = jsonArray1.getJSONObject(i);
                String name1 = lan1.getString("title");
                String date1 = lan1.getString("date");
                String author_name1 = lan1.getString("author_name");
                idcar1 = lan1.getString("url");

                datelist_shishi.add(new ItemBean1(name1,  author_name1, date1));
                Myadapter1 myadapter = new Myadapter1(getActivity(), datelist_shishi);
                listView2.setAdapter(myadapter);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
