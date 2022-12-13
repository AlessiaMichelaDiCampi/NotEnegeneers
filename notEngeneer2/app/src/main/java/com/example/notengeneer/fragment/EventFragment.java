package com.example.notengeneer.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.notengeneer.models.Event;
import com.example.notengeneer.activities.EventaddActivity;
import com.example.notengeneer.adapters.EventAdapter;
import com.example.notengeneer.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class EventFragment extends Fragment {

    ImageButton btnAddNewEvent;
    RecyclerView recyclerview;
    FirebaseFirestore db;
    String TAG = "DocSnippets";
    DatabaseReference database;
    EventAdapter eventAdapter;
    ArrayList<Event> list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event, container, false);

        btnAddNewEvent = (ImageButton) view.findViewById(R.id.add_event);
        recyclerview = view.findViewById(R.id.eventList);
        database = FirebaseDatabase.getInstance().getReference().child("events");
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseFirestore db = FirebaseFirestore.getInstance();


        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        db.setFirestoreSettings(settings);

        list = new ArrayList<>();

        eventAdapter = new EventAdapter(getContext(), list);
        recyclerview.setAdapter(eventAdapter);

        db.collection("events").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Event newEvent = new Event();
                                newEvent.setName(document.getString("eventName"));
                                newEvent.setDescription(document.getString("eventDescription"));
                                newEvent.setType(document.getString("type"));
                                newEvent.setStartDate(document.getString("dateStart"));
                                newEvent.setFinishDate(document.getString("dateEnd"));
                                newEvent.setPosition(document.getString("position"));
                                list.add(newEvent);
                                eventAdapter.notifyDataSetChanged();
                                Log.d(TAG, document.getId() + " => " + document.get("finish_date"));
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
        });

        btnAddNewEvent.setOnClickListener(v ->{
            startActivity(new Intent(getActivity(), EventaddActivity.class));
        });

        return view;
    }
}