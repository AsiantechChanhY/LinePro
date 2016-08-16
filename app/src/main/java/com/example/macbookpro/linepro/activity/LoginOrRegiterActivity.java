package com.example.macbookpro.linepro.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.macbookpro.linepro.R;

/**
 * Created by macbookpro on 8/9/16.
 */
public class LoginOrRegiterActivity extends Activity implements View.OnClickListener{
    private Button mLogin;
    private Button mRegiter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        mLogin = (Button) findViewById(R.id.imglogin);
        mRegiter = (Button) findViewById(R.id.imgregister);

        mLogin.setOnClickListener(this);
        mRegiter.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imglogin:
                Intent intent = new Intent(LoginOrRegiterActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.imgregister:
                Intent intent1 = new Intent(LoginOrRegiterActivity.this, EnterPhoneActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
