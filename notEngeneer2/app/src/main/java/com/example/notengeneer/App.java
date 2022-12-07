package com.example.notengeneer;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class App {

    static App instance = null;

    public static App getInstance() {
        if (instance == null)
            new App();
        return instance;
    }

    public FirebaseAuth mAuth;

    private FirebaseUser user = null;

    public App () {
        instance = this;

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        Log.d("myDebug", user.getUid());
    }

    public FirebaseUser getUser() {
        return user;
    }

    public void setUser(FirebaseUser user) {
        this.user = user;
    }
}
