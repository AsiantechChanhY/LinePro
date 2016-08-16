package com.example.macbookpro.linepro.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.macbookpro.linepro.R;
import com.example.macbookpro.linepro.api.Api;
import com.example.macbookpro.linepro.api.Server;
import com.example.macbookpro.linepro.model.Register;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by macbookpro on 8/9/16.
 */
public class EnterPhoneActivity extends Activity implements View.OnClickListener{
    private Button mNext;
    private EditText mPhone;
    private String strphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterphone);

        init();
    }

    private void init() {
        mPhone = (EditText) findViewById(R.id.edtphonenumber);
        mNext = (Button) findViewById(R.id.btnnextphone);
        mNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnnextphone:
                strphone = mPhone.getText().toString();
                if (strphone.equals("")) {
                    Toast.makeText(EnterPhoneActivity.this, "Ban chua nhap so phone", Toast.LENGTH_SHORT).show();
                }
                else {
                    next(strphone);
                }
        }
    }

    private void next(String phone) {
        Api api = Server.getServer();
        Call<Register> registerCall = api.register("vn",phone);
        registerCall.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                if (response.isSuccessful()){
                    String status = response.body().getStatus();
                    if (status.equals(Server.RESPONE)){
                        Intent intent = new Intent(EnterPhoneActivity.this, RegisterActivity.class);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {

            }
        });

    }

}
