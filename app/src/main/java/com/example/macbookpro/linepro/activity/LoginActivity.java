package com.example.macbookpro.linepro.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.macbookpro.linepro.R;
import com.example.macbookpro.linepro.api.Api;
import com.example.macbookpro.linepro.api.Server;
import com.example.macbookpro.linepro.model.Login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by macbookpro on 8/9/16.
 */
public class LoginActivity extends Activity implements View.OnClickListener{

    private EditText mUserName;
    private EditText mPassWord;
    private Button mLogin;
    private Button mRegister;
    private TextView mForgotPassword;
    private String strusername, strpassword;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUserName = (EditText) findViewById(R.id.edtusername);
        mPassWord = (EditText) findViewById(R.id.edtpassword);
        mLogin = (Button) findViewById(R.id.btnlogin);
        mRegister = (Button) findViewById(R.id.btnorregister);
        mForgotPassword = (TextView) findViewById(R.id.tvforgot);

        mRegister.setOnClickListener(this);
        mForgotPassword.setOnClickListener(this);
        init();

    }

    private void init() {
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
//                strusername = mUserName.getText().toString();
//                strpassword = mPassWord.getText().toString();
//                if (strusername.equals("") && strpassword.equals("")) {
//                    Toast.makeText(LoginActivity.this, "Nhap thong tin day du", Toast.LENGTH_SHORT).show();
//                } else {
//                    login(strusername, strpassword);
//                }
            }
        });
    }

//    private void login(final String username, String password) {
//
//        Api api = Server.getServer();
//        Call<Login> mServer = api.login(username, password);
//        mServer.enqueue(new Callback<Login>() {
//            @Override
//            public void onResponse(Call<Login> call, Response<Login> response) {
//                String status = response.body().getStatus();
//                String token = response.body().getToken();
//                if (status.equals(Server.RESPONE)) {
//                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                    intent.putExtra(Server.RESPONE, token);
//                    startActivity(intent);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Login> call, Throwable t) {
//
//            }
//        });
//    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnregister:
                register();
                break;
        }
    }

    private void register() {
        Intent intent = new Intent(LoginActivity.this, EnterPhoneActivity.class);
        startActivity(intent);
    }
}
