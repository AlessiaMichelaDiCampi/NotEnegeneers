package com.example.notengeneer.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notengeneer.R;
import com.example.notengeneer.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.notengeneer.databinding.ActivityProfileBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding binding;
    TextInputEditText etRegEmail;
    TextInputEditText etRegPassword;
    TextInputEditText etCap;
    TextInputEditText etName;
    TextInputEditText etSurname;
    TextInputEditText etState;
    TextInputEditText etAddress;
    TextInputEditText etAddressNumber;
    Button btnModify;

    DatePicker dateofBird;
    FirebaseFirestore firestore;
    private String currentUser;
    private User u;
    private DocumentReference docRef;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        setContentView(R.layout.activity_profile);

        etRegEmail = findViewById(R.id.emailProfile);

        etRegPassword = findViewById(R.id.passwProfile);
        etCap = findViewById(R.id.capProfile);//required
        etName =findViewById(R.id.nameProfile);
        etSurname = findViewById(R.id.surnameProfile);
        etState =findViewById(R.id.stateProfile);//required
        etAddress = findViewById(R.id.addressProfile);//required
        etAddressNumber = findViewById(R.id.nAddressProfile);//required
        dateofBird=findViewById(R.id.dateBirthProfile);
        btnModify = findViewById(R.id.btnModify);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());

        firestore=FirebaseFirestore.getInstance();
        docRef = firestore.collection("users").document(currentUser);


        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();

                    if (document.exists()) {

                        u=new User((String)document.get("email"),(String)document.get("name"),(String)document.get("surname"),(String)document.get("CAP"),(String)document.get("address"),(String)document.get("address_number"),(String)document.get("date_birth"),(String)document.get("state"));
                        //Log.d("PROVAAAA", u.email);
                        TextView p=findViewById(R.id.textView2);
                        p.setText(u.email);
                        etRegEmail.setText(u.email);
                        etName.setText(u.name);
                        etSurname.setText(u.surname);
                        etCap.setText(u.CAP);
                        etState.setText(u.state);
                        etAddress.setText(u.address);
                        etAddressNumber.setText(u.address_number);
                        Log.d("DATA NASCITA",u.getDateBirth());
                       /* Log.d("GIORNO NASCITA",u.getDay());
                        Log.d("MESE NASCITA",u.getMonth());
                        Log.d("ANNO NASCITA",u.getYear());*/
                        dateofBird.updateDate(u.getYear(),u.getMonth()-1,u.getDay());

                        Toast.makeText(ProfileActivity.this, document.getData().toString(), Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("TAG", "No such document");
                        Toast.makeText(ProfileActivity.this,"no such", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Log.d("TAG", "get failed with ", task.getException());
                    Toast.makeText(ProfileActivity.this, "get failed with"+task.getException(), Toast.LENGTH_SHORT).show();

                }
            }
        });
        btnModify.setOnClickListener(view ->{
            modifyUser();
        });
    }
    private void modifyUser() {
        String email = etRegEmail.getText().toString();
        String password = etRegPassword.getText().toString();
        String Cap = etCap.getText().toString();
        String name = etName.getText().toString();
        String surname = etSurname.getText().toString();
        String state = etState.getText().toString();
        String address = etAddress.getText().toString();
        String addressNumber = etAddressNumber.getText().toString();
        String dateBirth = ((dateofBird.getDayOfMonth())<10?"0":"")+(dateofBird.getDayOfMonth())+"-"+((dateofBird.getMonth())<10?"0":"")+(dateofBird.getMonth()+1)+"-"+dateofBird.getYear();
        u.update(email,name,surname,Cap,address,addressNumber,dateBirth,state);

        docRef.update(u.getMap());
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.updateEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("TAG", "User email address updated.");
                        }
                    }
                });
        user.updatePassword(password)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("TAG", "User password updated.");
                        }
                    }
                });

    }

}

