package com.example.notelet20.ui.login;

import android.content.Context;
import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.notelet20.R;
import com.example.notelet20.ui.data.LoginRepository;
import com.example.notelet20.ui.data.Result;
import com.google.firebase.auth.FirebaseUser;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private LoginRepository loginRepository;


    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }


    public void login(String username, String password) {
        // can be launched in a separate asynchronous job
        loginRepository.login(username, password);

        Result<FirebaseUser> result = loginRepository.getResult();
        if (result instanceof Result.Success) {
            FirebaseUser data = ((Result.Success<FirebaseUser>) result).getData();
            loginResult.setValue(new LoginResult(new LoggedInUserView(data.getEmail())));
        } else {
            loginResult.setValue(new LoginResult(R.string.login_failed));

        }
    }


    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    public void setUser(Result<FirebaseUser> result ){
        if (result instanceof Result.Success) {
            FirebaseUser data = ((Result.Success<FirebaseUser>) result).getData();
            loginResult.setValue(new LoginResult(new LoggedInUserView(data.getEmail())));
        } else {
            loginResult.setValue(new LoginResult(R.string.login_failed));

        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }

}
