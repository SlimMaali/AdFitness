package com.as.AdFitness.utility;

import com.as.AdFitness.service.ParticipationService;
import com.as.AdFitness.service.ProfileService;
import com.as.AdFitness.service.RoomService;
import com.as.AdFitness.service.SessionService;
import com.as.AdFitness.service.UserService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    private static Api instance = null;
    public static final String BASE_URL = "http://10.0.2.2:8000/api/";

    // services
    private UserService userService;
    private RoomService roomService;
    private SessionService sessionService;
    private ParticipationService participationService;
    private ProfileService profileService;


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
        this.roomService = retrofit.create(RoomService.class);
        this.sessionService = retrofit.create(SessionService.class);
        this.participationService = retrofit.create(ParticipationService.class);
        this.profileService = retrofit.create(ProfileService.class);
        //others here
    }

    public UserService getUserService() {
        return this.userService;
    }
    public RoomService getRoomService() { return this.roomService; }
    public SessionService getSessionService() { return sessionService; }
    public ParticipationService getParticipationService() { return this.participationService; }
    public ProfileService getProfileService() { return profileService; }
}
