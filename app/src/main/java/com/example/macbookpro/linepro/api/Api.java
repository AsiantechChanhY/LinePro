package com.example.macbookpro.linepro.api;


import com.example.macbookpro.linepro.model.Login;
import com.example.macbookpro.linepro.model.Register;
import com.example.macbookpro.linepro.model.UpdateUsername;
import com.example.macbookpro.linepro.model.UploadAvatar;

import java.util.Map;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.PartMap;

/**
 * Created by macbookpro on 8/9/16.
 */
public interface Api {

    @FormUrlEncoded
    @POST("api/v1/users/login")
    Call<Login> login(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("api/v1/users/register")
    Call<Register> register(@Field("location") String location, @Field("telephone") String telephone);

    @Multipart
    @POST("user/{id}/avatar")
    Call<UploadAvatar> upload(
            @Header("Authorization") String authorization,
            @PartMap Map<String, RequestBody> avatar
            );

    @FormUrlEncoded
    @PUT("user/{id}/username")
    Call<UpdateUsername> updateusername(@Field("username") String  username);
}
