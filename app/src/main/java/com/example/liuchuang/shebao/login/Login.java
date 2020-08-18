package com.example.liuchuang.shebao.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liuchuang.shebao.MainActivity;
import com.example.liuchuang.shebao.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Login extends AppCompatActivity {
    private TextView zhuce;
    EditText mEditText;
    EditText mEditText2;
    Button mButton;
    int retCode;
    private  TextView deng;
    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_loginlogin);
        zhuce = (TextView) findViewById(R.id.zhuce);
        deng = (TextView) findViewById(R.id.deng);
        deng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }
        });
        zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Zhuce.class);
                startActivity(intent);
            }
        });


        mEditText = (EditText) findViewById(R.id.username);
        mEditText2 = (EditText) findViewById(R.id.password);
        mButton = (Button) findViewById(R.id.login_btn);
        //监听事件
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //这是后端提供的登录接口
                start("http://39.106.34.160/myphp/login.php");
            }
        });

    }

    private void start(String s) {
        //初始化okhttp客户端
        OkHttpClient client = new OkHttpClient.Builder().build();
        //创建post表单，获取username和password（没有做非空判断）
        String username = mEditText.getText().toString();
        String password = mEditText2.getText().toString();
        if (username.length() > 0 && password.length() > 0) {
            RequestBody post = new FormBody.Builder()
                    .add("username", username)
                    .add("password", password)
                    .build();
            //开始请求，填入url，和表单
            final Request request = new Request.Builder()
                    .url(s)
                    .post(post)
                    .build();

            //客户端回调
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    //失败的情况（一般是网络链接问题，服务器错误等）
                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {
                    //UI线程运行
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                //临时变量（这是okhttp的一个锅，一次请求的response.body().string()只能用一次，否则就会报错）
                                Login.this.s = response.body().string();

                                //解析出后端返回的数据来
                                JSONObject jsonObject = new JSONObject(String.valueOf(Login.this.s));
                                retCode = jsonObject.getInt("success");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            //客户端自己判断是否成功。
                            if (retCode == 1) {
                                Toast.makeText(Login.this, "successful!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Login.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(Login.this, "错误!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });
        } else {
            Toast.makeText(Login.this, "账号密码不能为空!", Toast.LENGTH_SHORT).show();
        }
    }
}
