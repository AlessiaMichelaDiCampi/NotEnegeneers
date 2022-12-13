package com.example.notengeneer.activities;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.notengeneer.App;
import com.example.notengeneer.fragment.EventFragment;
import com.example.notengeneer.fragment.HomeFragment;
import com.example.notengeneer.R;
import com.example.notengeneer.fragment.RequestFragment;
import com.example.notengeneer.fragment.ShopFragment;
import com.example.notengeneer.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    App app = App.getInstance();

    FirebaseUser user = app.getUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (user == null)
            startActivity(new Intent(this, LoginActivity.class));

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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

        replaceFragment(new HomeFragment());

    }

    private void replaceFragment(Fragment fragment){    //per navigare con la dashboard tra i vari fragment
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frame_layout,fragment)
                .setReorderingAllowed(true)
                .addToBackStack("replacement")
                .commit();

    }

}