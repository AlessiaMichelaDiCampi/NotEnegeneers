package com.example.notengeneer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.notengeneer.App;
import com.example.notengeneer.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class ShopItemActivity extends AppCompatActivity {

    App app = App.getInstance();

    CollectionReference db = app.getfbFs().collection("shop");

    int prodId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_item);

        Intent intent = getIntent();
        prodId = intent.getIntExtra("selected", 0);

        DocumentReference prd = db.document(prodId+"");

        prd.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    String desc = task.getResult().get("descrizione").toString();

                    ((TextView) findViewById(R.id.shop_item_prod_name)).setText(desc);
                }
            }
        });



    }
}
