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
import com.google.gson.Gson;

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


public class Huodong_shishi extends Fragment {
    private ListView listView3;
    List<ItemBean1> datelist_redian = new ArrayList<>();
    ProgressDialog dia;
    HashMap<Integer, String> hashMap3 = new HashMap<Integer, String>();
    String idcar3;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.huodong_swn, container, false);
        listView3 = view.findViewById(R.id.lv_one);

//
        //得到缓存数据
        String saveJson = CacheUtils.getString(getActivity(),"http://api.shujuzhihui.cn/api/hot/list3");
        if(!TextUtils.isEmpty(saveJson)){
            procexssdat(saveJson);
        }else {

            dia = new ProgressDialog(getActivity());
            dia.setMessage("正在加载数据。。。。。。");
            dia.show();
            getdatanet();

        }

        // Toast.makeText(getActivity(), a, Toast.LENGTH_SHORT).show();
        listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String urla = hashMap3.get(i);
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
        RequestParams params = new RequestParams("http://api.shujuzhihui.cn/api/hot/list");
        params.addBodyParameter("appKey", "9c5b96f0b4d5430489eccc32e7a9323f");
        params.addBodyParameter("queryType", "renwu");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //缓存数据
                CacheUtils.putString(getActivity(), "http://api.shujuzhihui.cn/api/hot/list3",result);
                procexssdat(result);
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

    private void procexssdat(String json) {
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
            JSONObject jsonObject3 = new JSONObject(json);
            String a3 = jsonObject3.getString("ERRORCODE");
            JSONArray jsonArray = jsonObject3.getJSONArray("RESULT");
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject lan3 = jsonArray.getJSONObject(i);
                String name3 = lan3.getString("keyword");
                String serche3 = lan3.getString("searchNum");
                idcar3 = lan3.getString("id");
                hashMap3.put(i, idcar3);
                datelist_redian.add(new ItemBean1(name3, "热度" + serche3, "人物热点"));
                Myadapter1 myadapter = new Myadapter1(getActivity(), datelist_redian);
                listView3.setAdapter(myadapter);
            }
            Log.e("lslslslslslslsl", a3);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
