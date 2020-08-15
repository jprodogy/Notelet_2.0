package com.example.notelet20.ui.data;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.notelet20.ui.login.LoginActivity;
import com.example.notelet20.ui.login.LoginViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource extends AppCompatActivity {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private Result<FirebaseUser> result;
    private LoginViewModel view = LoginActivity.loginViewModel;


    public void login(String username, String password) {
        mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("YES", "signInWithEmail:success");
                            result = new Result.Success<>(mAuth.getCurrentUser());

                        } else {
                            result = new Result.Error(new FirebaseAuthException("Error logging in", "mAuth"));
                        }

                    }
        });



    }

    public Result<FirebaseUser> getResult(){

        return result;
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
