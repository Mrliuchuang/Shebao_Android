package com.example.liuchuang.shebao;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.liuchuang.shebao.utius.Base64Util;
import com.example.liuchuang.shebao.utius.ShenfenzhengBean;
import com.google.gson.Gson;

import org.xutils.common.Callback;

import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Renzhengxinxitijiao extends AppCompatActivity {
    private EditText name_yonghu;
    private TextView shenfenzhenghao;
    private TextView xingbie;
    private TextView Adress;
    private TextView Birth;
    private TextView minzu;
    private EditText shoujihao;
    private LinearLayout camera;
    private Bitmap bitmap;
    ImageView imageView_renzheng;
    private static int CAMERA_REQUEST_CODE = 1;
    ProgressDialog dia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renzhengxinxitijiao);
        imageView_renzheng = findViewById(R.id.imageview_touxiang);
        Adress = findViewById(R.id.adress);
        Birth = findViewById(R.id.birth);
        imageView_renzheng.setImageResource(R.mipmap.module_login_activity_operator_activation_font);

        TextView tiaozhuan1 = findViewById(R.id.bug_jibenxinxitijiao1);
        tiaozhuan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        name_yonghu = findViewById(R.id.name_yonghu);
        shenfenzhenghao = findViewById(R.id.shenfenzhenghao);
        xingbie = findViewById(R.id.xingbie);
        minzu = findViewById(R.id.minzu);
        shoujihao = findViewById(R.id.shoujihao);
        camera = findViewById(R.id.camera);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("dda", "摄像头");
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_REQUEST_CODE);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (data == null) {
                return;
            } else {
                Bundle extras = data.getExtras();
                if (extras != null) {
                    bitmap = extras.getParcelable("data");
                    imageView_renzheng.setImageBitmap(bitmap);
                    upimage_touxiang();
                    dia = new ProgressDialog(this);
                    dia.setMessage("识别中......");
                    dia.show();
                }
            }
        }
    }


    public void tijiao_renzheng(View view) {
        shangchuanwenzi();


    }

    public byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();
    }


    private void upimage_touxiang() {
//        final ProgressDialog dia = new ProgressDialog(this);
//        dia.setMessage("识别中......");
//        dia.show();
        RequestParams params = new RequestParams("http://39.106.34.160/shebao/sfzbaidu.php");
        params.addBodyParameter("img", Base64Util.encode(Bitmap2Bytes(bitmap)));
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(final String result) {


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("img", result);

                        Gson gson = new Gson();
                        ShenfenzhengBean jsonbean = gson.fromJson(result, ShenfenzhengBean.class);
                        String name = jsonbean.getData().getName();
                        String adress = jsonbean.getData().getAddress();
                        String birth = jsonbean.getData().getBirth();
                        String sex = jsonbean.getData().getSex();
                        String id = jsonbean.getData().getId();
                        String nation = jsonbean.getData().getNation();
                        Log.e("name", name);
                        name_yonghu.setText(name);
                        Adress.setText(adress);
                        Birth.setText(birth);
                        xingbie.setText(sex);
                        shenfenzhenghao.setText(id);
                        minzu.setText(nation);
                        dia.dismiss();

                    }
                });

            }


            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("error", "ddd" + ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });


    }

    private void shangchuanwenzi() {


        String na = name_yonghu.getText().toString().trim();
        String sfzh = shenfenzhenghao.getText().toString().trim();
        String xb = xingbie.getText().toString().trim();
        String mz = minzu.getText().toString().trim();
        String sjh = shoujihao.getText().toString().trim();
        String adress = Adress.getText().toString().trim();
        String birth = Birth.getText().toString().trim();
        if (sjh.length() == 11 && na.length() != 0 && shenfenzhenghao.length() != 0) {

            //初始化okhttp客户端
            Toast.makeText(this, "提交成功！", Toast.LENGTH_SHORT).show();
            OkHttpClient client = new OkHttpClient.Builder().build();
            RequestBody post = new FormBody.Builder()
                    .add("name", na)
                    .add("shenfenzhenghao", sfzh)
                    .add("xingbie", xb)
                    .add("minzu", mz)
                    .add("shoujihao", sjh)
                    .add("touxianglujing", "path")
                    .add("adress", adress)
                    .add("birth", birth)
                    .build();
            //开始请求，填入url，和表单
            final Request request = new Request.Builder()
                    .url("http://39.106.34.160/shebao/renzhengtijiao.php")
                    .post(post)
                    .build();
            client.newCall(request).enqueue(new okhttp3.Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.e("eeee", "ddd" + response);
                            Intent intent = new Intent(Renzhengxinxitijiao.this, Huotijiance.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("sfzh", shenfenzhenghao.getText().toString().trim());
                            intent.putExtras(bundle);
                            startActivity(intent);

                        }
                    });
                }
            });
        } else {

            Toast.makeText(this, "输入有误", Toast.LENGTH_SHORT).show();
        }

    }
}
