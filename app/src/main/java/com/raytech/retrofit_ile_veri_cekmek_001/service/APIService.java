package com.raytech.retrofit_ile_veri_cekmek_001.service;

import com.raytech.retrofit_ile_veri_cekmek_001.model.Albums;

import java.util.List;

import retrofit2.Call;

import retrofit2.http.GET;

public interface APIService {

    @GET("albums")
    Call<List<Albums>> getAlbumList();
}
