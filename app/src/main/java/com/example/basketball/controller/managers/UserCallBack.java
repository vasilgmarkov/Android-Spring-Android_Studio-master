package com.example.basketball.controller.managers;

import com.example.basketball.model.User;

/**
 * Created by usu27 on 23/5/16.
 */
public interface UserCallBack {


    void onSuccess(User userInfo);

    void onFailure(Throwable t);
}