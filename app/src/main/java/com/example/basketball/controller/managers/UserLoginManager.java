package com.example.basketball.controller.managers;

import android.content.Context;
import android.util.Log;

import com.example.basketball.controller.services.UserService;
import com.example.basketball.model.User;
import com.example.basketball.model.UserToken;
import com.example.basketball.util.CustomProperties;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserLoginManager {
    private static UserLoginManager ourInstance;
    private UserToken userToken;
    private Context context;
    private String bearerToken;
    private UserService userService;
    private User user;
    private Retrofit retrofit;

    private UserLoginManager(Context cntxt) {
        context = cntxt;
        retrofit = new Retrofit.Builder()
                .baseUrl(CustomProperties.getInstance(context).get("app.baseUrl"))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        userService = retrofit.create(UserService.class);
    }


    public static UserLoginManager getInstance(Context context) {
        if(ourInstance == null){
            ourInstance = new UserLoginManager(context);
        }

        ourInstance.context = context;
        return ourInstance;
    }

    public synchronized void performLogin(String username, String password, final LoginCallback loginCallback){
        Call<UserToken> call =  UserTokenManager.getInstance(context).getUserToken(username, password);

        call.enqueue(new Callback<UserToken>() {
            @Override
            public void onResponse(Call<UserToken> call, Response<UserToken> response) {
                Log.i("UserLoginManager ", " performtaks->call.enqueue->onResponse res: " + response.body());
                userToken = response.body();

                int code = response.code();

                if (code == 200 || code == 201) {
                    bearerToken = "Bearer " + userToken.getAccessToken();
                    loginCallback.onSuccess(userToken);
                } else {
                    loginCallback.onFailure(new Throwable("ERROR " + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<UserToken> call, Throwable t) {
                Log.e("UserLoginManager ", " performtaks->call.enqueue->onResponse err: " + t.toString());
                loginCallback.onFailure(t);
            }
        });
    }


    public synchronized void newUser(final UserCallBack userCallback, final User user) {
        // Call<List<Apuesta>> call = playerService.getAllPlayer(UserLoginManager.getInstance(context).getBearerToken());
        Call <Void> call = userService.createUser(user);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                //  apuestas1x2 = response.body();

                int code = response.code();

                if (code == 200 || code == 201) {
                    userCallback.onSuccess(user);
                    Log.e("Apuesta->", "Realizada: OOK" + 100);

                } else {
                    userCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("PlayerManager->", "getAllPlayers()->ERROR: " + t);

                userCallback.onFailure(t);
            }
        });
    }

    public UserToken getUserToken(){
        return userToken;
    }

    public String getBearerToken() {
        return bearerToken;
    }
}