package com.example.tasks.service.repository.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static String BASE_URL = "http://devmasterteam.com/CursoAndroidAPI/";
    private static Retrofit mRetrofit;

    private RetrofitClient(){};

    private static Retrofit getRetrofitInstance() {
        if(mRetrofit == null) {
            mRetrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }

    public static <S> S createService(Class<S> sClass) {
        return getRetrofitInstance().create(sClass);
    }
}
