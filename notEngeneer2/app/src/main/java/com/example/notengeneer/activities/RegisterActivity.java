package com.example.notengeneer.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.notengeneer.R;
import com.example.notengeneer.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterActivity extends AppCompatActivity {

    TextInputEditText etRegEmail;
    TextInputEditText etRegPassword;
    TextInputEditText etCap;
    TextView tvLoginHere;
    Button btnRegister;
    TextInputEditText etName;
    TextInputEditText etSurname;
    TextInputEditText etState;
    TextInputEditText etAddress;
    TextInputEditText etAddressNumber;
    DatePicker dateofBird;


    FirebaseAuth mAuth;
    FirebaseFirestore firestore;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etRegEmail = findViewById(R.id.etRegEmail); //required
        etRegPassword = findViewById(R.id.etRegPass);//required
        tvLoginHere = findViewById(R.id.tvLoginHere);
        btnRegister = findViewById(R.id.btnRegister);
        etCap = findViewById(R.id.etCap);//required


        etName =findViewById(R.id.Name);
        etSurname = findViewById(R.id.Surname);
        etState =findViewById(R.id.State);//required
        etAddress = findViewById(R.id.Address);//required
        etAddressNumber = findViewById(R.id.nAddress);//required
        dateofBird=findViewById(R.id.DateBirth);


        mAuth = FirebaseAuth.getInstance();
        firestore =FirebaseFirestore.getInstance();
        btnRegister.setOnClickListener(view ->{
            createUser();
        });

        tvLoginHere.setOnClickListener(view ->{
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        });
    }

    private void createUser(){
        String email = etRegEmail.getText().toString();
        String password = etRegPassword.getText().toString();
        String Cap = etCap.getText().toString();
        String name = etName.getText().toString();
        String surname = etSurname.getText().toString();
        String state = etState.getText().toString();
        String address = etAddress.getText().toString();
        String addressNumber = etAddressNumber.getText().toString();
        String dateBirth = ((dateofBird.getDayOfMonth())<10?"0":"")+(dateofBird.getDayOfMonth())+"-"+((dateofBird.getMonth())<10?"0":"")+(dateofBird.getMonth()+1)+"-"+dateofBird.getYear();





        if (TextUtils.isEmpty(email)){
            etRegEmail.setError("Email cannot be empty");
            etRegEmail.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            etRegPassword.setError("Password cannot be empty");
            etRegPassword.requestFocus();
        }else if(TextUtils.isEmpty(Cap)) {
            etRegPassword.setError("Cap cannot be empty");
            etRegPassword.requestFocus();
        }else if(TextUtils.isEmpty(address)) {
            etAddress.setError("Cap cannot be empty");
            etAddress.requestFocus();
        }else if(TextUtils.isEmpty(addressNumber)) {
            etAddressNumber.setError("Cap cannot be empty");
            etAddressNumber.requestFocus();
        }else if(TextUtils.isEmpty(state)) {
            etState.setError("Cap cannot be empty");
            etState.requestFocus();
        }else{

                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){


                            User u= new User(email,name,surname,Cap,address,addressNumber,dateBirth,state);
                            Toast.makeText(RegisterActivity.this, task.getResult().getUser().getUid(), Toast.LENGTH_SHORT).show();


                            firestore.collection("users").document(task.getResult().getUser().getUid()).set(u.getMap()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(RegisterActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(),"Failure"+e.getMessage(), Toast.LENGTH_LONG).show(); //se non va a buon fine mostro questo
                                }
                            });

                        }else{
                            Toast.makeText(RegisterActivity.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        }
    }
