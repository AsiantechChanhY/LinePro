package com.example.macbookpro.linepro.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by macbookpro on 8/9/16.
 */
public class Server {

    public static final String URL = "http://linepro.dev.bap.jp/";
    public static final String RESPONE = "200";

    public static Api getServer() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final Api mServer = retrofit.create(Api.class);
        return mServer;
    }
}
