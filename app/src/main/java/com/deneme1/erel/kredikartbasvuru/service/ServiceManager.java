package com.deneme1.erel.kredikartbasvuru.service;

import com.deneme1.erel.kredikartbasvuru.model.Register;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by melike on 17/12/2016.
 */
public class ServiceManager {
    private static Retrofit retrofit = null;
    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://www.melikeyucel.com.tr")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public interface ServiceRequest {
        @POST("/services/register.php")
        Call<Boolean> addForm(@Body Register register);
    }
}