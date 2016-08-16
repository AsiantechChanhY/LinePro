package com.example.macbookpro.linepro.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.macbookpro.linepro.CropImage.Crop;
import com.example.macbookpro.linepro.ImagePicker.ImagePicker;
import com.example.macbookpro.linepro.R;
import com.example.macbookpro.linepro.api.Api;
import com.example.macbookpro.linepro.api.Server;
import com.example.macbookpro.linepro.model.UpdateUsername;
import com.example.macbookpro.linepro.model.UploadAvatar;
import com.example.macbookpro.linepro.widget.CircleImageView;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by macbookpro on 8/10/16.
 */
public class RegisterActivity extends Activity implements View.OnClickListener {
    private CircleImageView mAvatar;
    private EditText mUsername;
    private Button mPlaceImage;
    private Button mRegister;

    private String strUsername;
    private String strAvatar;
    private String mediaPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();
    }

    private void init() {
        mAvatar = (CircleImageView) findViewById(R.id.imgavata);
        mPlaceImage = (Button) findViewById(R.id.btnimageavatar);
        mUsername = (EditText) findViewById(R.id.edtUsernameregister);
        mRegister = (Button) findViewById(R.id.btnregister);
        mPlaceImage.setOnClickListener(this);
        mRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnimageavatar: {
                final Dialog builder = new Dialog(this);
                builder.setTitle("Select your image");
                builder.setContentView(R.layout.dialog_avatar);

                ImageView fileSystem = (ImageView) builder.findViewById(R.id.imgfileSystem);
                fileSystem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mAvatar.setImageBitmap(null);
                        Crop.pickImage(RegisterActivity.this);
                        builder.dismiss();
                    }
                });

                ImageView camera = (ImageView) builder.findViewById(R.id.imgplaceavatar);
                camera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ImagePicker.pickImage(RegisterActivity.this, "");
                        builder.dismiss();
                    }
                });

                builder.show();
            }

            case R.id.btnregister:
                strUsername = mUsername.getText().toString();
                if (strUsername.equals("")) {
                    Toast.makeText(RegisterActivity.this, "Ban chua nhap day du thong tin", Toast.LENGTH_SHORT).show();
                }
                else {
//                    uploadAvatar();
                    Username(strUsername);
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Crop.REQUEST_PICK && resultCode == RESULT_OK) {
//            Uri selectedImage = data.getData();
            mAvatar.setImageBitmap(null);

            beginCrop(data.getData());

//            String[] filePathColumn = {MediaStore.Images.Media.DATA};
//            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
//            assert cursor != null;
//            cursor.moveToFirst();
//
//            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//            mediaPath = cursor.getString(columnIndex);

//            cursor.close();

        } else if (requestCode == Crop.REQUEST_CROP) {
            handleCrop(resultCode, data);
        }
    }

    private void beginCrop(Uri source) {
        Uri destination = Uri.fromFile(new File(getCacheDir(), "cropped"));
        Crop.of(source, destination).asSquare().start(this);
    }

    private void handleCrop(int resultCode, Intent result) {
        if (resultCode == RESULT_OK) {
            mAvatar.setImageURI(Crop.getOutput(result));
        } else if (resultCode == Crop.RESULT_ERROR) {
            Toast.makeText(this, Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void Username(String username) {
        Api api = Server.getServer();
        Call<UpdateUsername> call = api.updateusername(username);
        call.enqueue(new Callback<UpdateUsername>() {
            @Override
            public void onResponse(Call<UpdateUsername> call, Response<UpdateUsername> response) {
                if (response.isSuccessful()){
                    String status = response.body().getStatus();
                    Log.d("Aaaaaa", status + "");
                    if (status.equals(Server.RESPONE)){
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onFailure(Call<UpdateUsername> call, Throwable t) {

            }
        });
    }


//    private void uploadAvatar() {
//        Map<String, RequestBody> map = new HashMap<>();
//        File file = new File(String.valueOf(mAvatar));
//
//        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
//        map.put("file\"; filename=\"" + file.getName() + "\"", requestBody);
//
//        Api api = Server.getServer();
//        Call<UploadAvatar> call = api.upload("token", map);
//        call.enqueue(new Callback<UploadAvatar>() {
//            @Override
//            public void onResponse(Call<UploadAvatar> call, Response<UploadAvatar> response) {
//                UploadAvatar uploadAvatar = response.body();
//                Log.d("Aaaaa", "aaaaaaaaa" + uploadAvatar);
//                if (uploadAvatar != null) {
//                    if (uploadAvatar.getSuccess()) {
//                        Toast.makeText(getApplicationContext(), uploadAvatar.getStatus(),Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(getApplicationContext(), uploadAvatar.getStatus(),Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Log.v("Response", uploadAvatar.toString());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<UploadAvatar> call, Throwable t) {
//
//            }
//        });
//    }


}
