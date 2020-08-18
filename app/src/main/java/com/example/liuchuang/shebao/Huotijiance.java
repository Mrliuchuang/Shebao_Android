package com.example.liuchuang.shebao;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liuchuang.shebao.Adapter.ItemBean1;
import com.example.liuchuang.shebao.Adapter.Myadapter1;
import com.example.liuchuang.shebao.utius.Base64Util;
import com.example.liuchuang.shebao.utius.Jsonbean;
import com.example.liuchuang.shebao.utius.ShenfenzhengBean;
import com.example.liuchuang.shebao.utius.huotibean;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class Huotijiance extends AppCompatActivity {
    ProgressDialog dia;

    private static int CAMERA_REQUEST_CODE = 1;
    private static int GALLERY_REQUEST_CODE = 2;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    ImageView imageView;
    String sfzh;
    Bitmap bm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huotijiance);
        Bundle bundle = this.getIntent().getExtras();//跳转传值
        TextView back_huoti = findViewById(R.id.back_huoti);
        TextView bug_huotijiance = findViewById(R.id.bug_huotijiance);
        bug_huotijiance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(Huotijiance.this, VideoChatViewActivity.class);
                startActivity(intent);

            }
        });
        back_huoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
        sfzh = bundle.getString("sfzh");
        imageView = (ImageView) findViewById(R.id.imageView_huoti);
        imageView.setImageResource(R.mipmap.renzheng);
        //   Toast.makeText(this, sfzh, Toast.LENGTH_SHORT).show();
        Button btn_camera = (Button) findViewById(R.id.btn_camera_huoti);
        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_REQUEST_CODE);
            }
        });

        Button btn_gallery = (Button) findViewById(R.id.btn_gallery_huoti);
        btn_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_REQUEST_CODE);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (data == null) {
                return;
            } else {
                Bundle extras = data.getExtras();
                if (extras != null) {
                    bm = extras.getParcelable("data");
                    imageView.setImageBitmap(bm);
                    sendimage();

                }
            }
        }

    }

    private void sendimage() {
        RequestParams params = new RequestParams("http://39.106.34.160/shebao/Huotijiance.php");
        params.addBodyParameter("img", base64(bm));
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("全是", "我是" + result);
//                Gson gson = new Gson();
//                huotibean jsonbean = gson.fromJson(result, huotibean.class);
//                int liveens = jsonbean.getResult().getFace_liveness();
//                if(liveens>0.95)
//                {
//                    Toast.makeText(Huotijiance.this, "成功", Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(Huotijiance.this, "fail", Toast.LENGTH_SHORT).show();
//                }
//                Log.e("我是", "我是"+liveens);

                try {
                    JSONArray jsonArray = new JSONArray(result);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        double face_liveness = jsonObject.getJSONObject("result").getDouble("face_liveness");
                        if (face_liveness > 0.5) {
                            renlianshibie();

                        } else {
                            Toast.makeText(Huotijiance.this, "活体检测失败，请重试！", Toast.LENGTH_SHORT).show();
                        }
                        Log.e("1", "活体分数" + face_liveness);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            private void renlianshibie() {


                OkHttpClient okHttpClient = new OkHttpClient();
                Gson gson = new Gson();
                Map<String, String> map = new HashMap<>();
                map.put("image_type", "BASE64");
                map.put("image", base64(bm));
                map.put("group_id_list", "student");
                String param = gson.toJson(map);
//       String param="{\n" +
//               "            \"image\": \"39.106.34.160/liuchuang/liu.jpg\",\n" +
//               "                \"image_type\": \"URL\",\n" +
//               "                \"group_id_list\": \"student\"\n" +
//               "        }" ;
                String url = "https://aip.baidubce.com/rest/2.0/face/v3/search?access_token=24.3febbd1b0a7fbd7e362b972f3fb758fa.2592000.1591943469.282335-15455209";
                RequestBody requestBody = RequestBody.create(JSON, param);
                Request request = new Request.Builder()
                        .url(url)
                        .post(requestBody)
                        .build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new okhttp3.Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String json = response.body().string();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.e("1", "人脸识别：" + json);
                                Gson gson = new Gson();
                                Jsonbean jsonbean = gson.fromJson(json, Jsonbean.class);
                                Double score = jsonbean.getResult().getUser_list().get(0).getScore();
                                if (score > 70) {
                                    Toast.makeText(Huotijiance.this, "系统认证通过！", Toast.LENGTH_SHORT).show();
                                    dia = new ProgressDialog(Huotijiance.this);
                                    dia.setMessage("上传照片中。。。。。");
                                    dia.show();
                                    sendbase64();
                                } else {
                                    Toast.makeText(Huotijiance.this, "不是本人！", Toast.LENGTH_SHORT).show();
                                }
                            }

                            private void sendbase64() {
                                OkHttpClient okHttpClient = new OkHttpClient();
                                //构造一个请求的内容
                                RequestBody requestBody = new FormBody.Builder()
                                        .add("img", base64(bm))
                                        .add("path", sfzh)
                                        .build();
                                Request request = new Request.Builder()
                                        .url("http://39.106.34.160/shebao/ImgUpload.php")
                                        .post(requestBody)
                                        .build();
                                Call call = okHttpClient.newCall(request);
                                call.enqueue(new okhttp3.Callback() {
                                    @Override
                                    public void onFailure(Call call, IOException e) {
                                    }

                                    @Override
                                    public void onResponse(Call call, Response response) throws IOException {
                                        final String json = response.body().string();
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Log.e("ddd", "dd" + json);

                                                dia.dismiss();
                                                Toast.makeText(Huotijiance.this, "头像上传成功!", Toast.LENGTH_SHORT).show();

                                                Intent intent = new Intent(Huotijiance.this, VideoChatViewActivity.class);
                                                startActivity(intent);

                                            }


                                        });
                                    }
                                });
                            }


                        });
                    }
                });


                Toast.makeText(Huotijiance.this, "活体检测成功", Toast.LENGTH_SHORT).show();

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

    private String base64(Bitmap bm) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 60, stream);
        byte[] bytes = stream.toByteArray();
        String img = new String(Base64.encodeToString(bytes, Base64.DEFAULT));
        return img;
    }
}

