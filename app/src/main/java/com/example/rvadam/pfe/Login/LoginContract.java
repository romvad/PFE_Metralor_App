package com.example.rvadam.pfe.Login;

/**
 * Created by rvadam on 04/04/2018.
 */

public interface LoginContract {

    interface View {
    }
    interface UserActionsListener {
        void authenticationProcessing(String login, String password);
    }
}
