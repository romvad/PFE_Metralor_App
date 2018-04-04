package com.example.rvadam.pfe.Login;

/**
 * Created by rvadam on 04/04/2018.
 */

public class LoginPresenter implements LoginContract.UserActionsListener {

    private final LoginContract.View mLoginView;

    public LoginPresenter(LoginContract.View mLoginView) {
        this.mLoginView = mLoginView;
    }

    @Override
    public void authenticationProcessing(String login, String password) {

    }
}
