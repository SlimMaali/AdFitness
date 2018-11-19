package com.as.AdFitness.utility;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    private static Api instance = null;
    public static final String BASE_URL = "http://10.0.2.2:8000/api/";

    // services
    private UserService userService;


    public static Api getInstance() {
        if (instance == null) {
            instance = new Api();
        }

        return instance;
    }
    // Build retrofit once when creating a single instance
    private Api() {
        buildRetrofit();
    }

    private void buildRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Building  services once
        this.userService = retrofit.create(UserService.class);
        //others here
    }

    public UserService getUserService() {
        return this.userService;
    }
}
