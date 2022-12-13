package com.example.notengeneer;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class App {

    static App instance = null;

    public static App getInstance() {
        if (instance == null)
            new App();
        return instance;
    }

    private final FirebaseAuth fbAuth;
    private final FirebaseFirestore fbFs;
    private final FirebaseDatabase fbDB;

    private FirebaseUser user = null;

    public App () {
        instance = this;

        fbDB = FirebaseDatabase.getInstance("https://notengeneers-default-rtdb.europe-west1.firebasedatabase.app/");
        fbFs = FirebaseFirestore.getInstance();
        fbAuth = FirebaseAuth.getInstance();
        user = fbAuth.getCurrentUser();
    }

    public FirebaseAuth getfbAuth() {
        return fbAuth;
    }

    public FirebaseFirestore getfbFs() {
        return fbFs;
    }

    public FirebaseDatabase getFbDB() {
        return fbDB;
    }

    public FirebaseUser getUser() {
        return user;
    }

    public void setUser(FirebaseUser user) {
        this.user = user;
    }
}
