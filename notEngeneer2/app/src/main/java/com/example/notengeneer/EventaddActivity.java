package com.example.notengeneer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.type.DateTime;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EventaddActivity extends AppCompatActivity {
    EditText etEventName, etEventDescription, etEventType, etEventPosition;
    Button btnCreateEvent;
    DatePicker dpEventDate, dpEventFinishDate;
    TimePicker tpEventHour, tpEventFinishHour;
    FirebaseAuth mAuth;
    FirebaseFirestore firestore;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventadd);

        Date dtMin = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dtMin);
        c.add(Calendar.DATE, 1);
        dtMin = c.getTime();

        Date dtMax = new Date();
        c.setTime(dtMax);
        c.add(Calendar.DATE, 90);
        dtMax = c.getTime();

        etEventName = (EditText) findViewById(R.id.editTextEventName);
        etEventDescription = (EditText) findViewById(R.id.editTextTextEventDescription);
        etEventType = (EditText) findViewById(R.id.editTextEventType);
        etEventPosition = (EditText) findViewById(R.id.editTextEventPosition);
        dpEventDate = (DatePicker) findViewById(R.id.eventDate);
        dpEventFinishDate = (DatePicker) findViewById(R.id.eventFinishDate);
        tpEventHour = (TimePicker) findViewById(R.id.eventHour);
        tpEventFinishHour = (TimePicker) findViewById(R.id.eventFinishHour);

        btnCreateEvent = (Button) findViewById(R.id.createEventButton);

        mAuth = FirebaseAuth.getInstance();
        firestore =FirebaseFirestore.getInstance();

        dpEventDate.setMinDate(dtMin.getTime());
        dpEventDate.setMaxDate(dtMax.getTime());

        btnCreateEvent.setOnClickListener(view -> {
            createEvent();

        });


    }

    private void createEvent(){
        String EventName = etEventName.getText().toString();
        String EventDescription = etEventDescription.getText().toString();
        String EventType = etEventType.getText().toString();
        String EventHour = tpEventHour.getHour() + ":" + tpEventHour.getMinute();
        String EventDate = dpEventDate.getDayOfMonth()+"/"+((dpEventDate.getMonth())<10?"0":"")+(dpEventDate.getMonth()+1)+"/"+dpEventDate.getYear() + "-" + EventHour;
        String EventFinishHour = tpEventFinishHour.getHour() + ":" + tpEventFinishHour.getMinute();
        String EventFinishDate = dpEventFinishDate.getDayOfMonth()+"/"+((dpEventFinishDate.getMonth())<10?"0":"")+(dpEventFinishDate.getMonth()+1)+"/"+dpEventFinishDate.getYear() + "-" + EventFinishHour;
        String EventPosition = etEventPosition.getText().toString();

        if(TextUtils.isEmpty(EventName)){
            etEventName.setError("Email cannot be empty");
            etEventName.requestFocus();
        }else{
            String EventManager = mAuth.getCurrentUser().toString();
            Map<String,Object> event = new HashMap<>();
            event.put("eventName", EventName);
            event.put("manager", EventManager);
            event.put("eventDescription", EventDescription);
            event.put("type", EventType);
            event.put("dateStart", EventDate);
            event.put("dateEnd", EventFinishDate);
            event.put("position", EventPosition);

            firestore.collection("events").add(event).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Toast.makeText(EventaddActivity.this, "Event Add", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(EventaddActivity.this, LoginActivity.class));
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(),"Failure", Toast.LENGTH_LONG).show(); //se non va a buon fine mostro questo
                }
            });
        }
    }
}