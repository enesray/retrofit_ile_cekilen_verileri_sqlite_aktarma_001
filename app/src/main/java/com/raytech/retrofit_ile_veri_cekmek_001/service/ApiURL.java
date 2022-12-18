package com.raytech.retrofit_ile_veri_cekmek_001.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiURL {
    private static Retrofit retrofit=null;
    private static String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static Gson gson = new GsonBuilder().setLenient().create();

    public static Retrofit getClient(){
        if(retrofit== null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(new OkHttpClient())
                    .build();
            return retrofit;
        }
        return retrofit;
    }
    //ApiClient.class
}
