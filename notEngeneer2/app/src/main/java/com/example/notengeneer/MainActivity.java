package com.example.notengeneer;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.notengeneer.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    Button profilebutt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        profilebutt =  findViewById(R.id.profButt);
        profilebutt.setOnClickListener(view ->{
            startActivity(new Intent(MainActivity.this, Profile.class));
        });

        binding.bottomNavigationView.setOnItemSelectedListener(item->{

            switch (item.getItemId()){

                case R.id.home:
                    replaceFragment(new HomeFragment());
                    Toast.makeText(MainActivity.this, "HOME", Toast.LENGTH_SHORT).show();

                    break;
                case R.id.event:
                    replaceFragment(new EventFragment());
                    Toast.makeText(MainActivity.this, "event", Toast.LENGTH_SHORT).show();

                    break;
                case R.id.shop:
                    replaceFragment(new ShopFragment());
                    Toast.makeText(MainActivity.this, "shop", Toast.LENGTH_SHORT).show();

                    break;
                case R.id.help:
                    replaceFragment(new RequestFragment());
                    Toast.makeText(MainActivity.this, "help", Toast.LENGTH_SHORT).show();

                    break;
            }

            return true;
        });

    }

    private void replaceFragment(Fragment fragment){    //per navigare con la dashboard tra i vari fragment
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();

    }

}