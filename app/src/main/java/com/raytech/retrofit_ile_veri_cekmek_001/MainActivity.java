package com.raytech.retrofit_ile_veri_cekmek_001;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.raytech.retrofit_ile_veri_cekmek_001.model.Albums;
import com.raytech.retrofit_ile_veri_cekmek_001.service.APIService;
import com.raytech.retrofit_ile_veri_cekmek_001.sqlite.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ArrayList<Albums> albumModels;
    private String BASE_URL = "https://jsonplaceholder.typicode.com/";
    Retrofit retrofit;
    private DatabaseHelper dbHelper;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);

        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        loadData(); //retrofit ile çekilen verilerin gösterilmesi için
        addAlbum(); //çekilen verileri sqlite aktarmak için
    }

    private void loadData() {
        APIService apiService = retrofit.create(APIService.class);
        Call<List<Albums>> call = apiService.getAlbumList();
        call.enqueue(new Callback<List<Albums>>() {
            @Override
            public void onResponse(Call<List<Albums>> call, Response<List<Albums>> response) {
                if (response.isSuccessful()) {
                    List<Albums> responseList = response.body();
                    albumModels = new ArrayList<>(responseList);
                    System.out.println(albumModels);

                    for (Albums albumModel : albumModels) {
                        System.out.println("UserID : " + albumModel.getUserId());
                        System.out.println("Başlık :" + albumModel.getTitle());
                        System.out.println("ID : " + albumModel.getId());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Albums>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void addAlbum() {
        APIService apiService = retrofit.create(APIService.class);
        Call<List<Albums>> call = apiService.getAlbumList();

        call.enqueue(new Callback<List<Albums>>() {
            @Override
            public void onResponse(Call<List<Albums>> call, Response<List<Albums>> response) {
                if (response.isSuccessful()) {
                    List<Albums> albums = response.body();
                    dbHelper.addAlbums(albums);
                    System.out.println("Veriler sqlite içerisine kaydedildi");
                }
            }

            @Override
            public void onFailure(Call<List<Albums>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Hata : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}