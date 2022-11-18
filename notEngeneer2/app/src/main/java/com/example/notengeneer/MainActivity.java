package com.example.notengeneer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.notengeneer.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    }

    private void replaceFragment(Fragment fragment){    //per navigare con lka dashboard tra i vari fragment
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();

    }

}