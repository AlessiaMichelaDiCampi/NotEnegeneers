package com.example.notengeneer.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.notengeneer.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EventFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
import java.util.ArrayList;


public class EventFragment extends Fragment {

    ImageButton btnAddNewEvent;
    RecyclerView recyclerview;
    FirebaseFirestore db;
    String TAG = "DocSnippets";
    DatabaseReference database;
    MyAdapter myAdapter;
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

        myAdapter = new MyAdapter(getContext(), list);
        recyclerview.setAdapter(myAdapter);

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
                                myAdapter.notifyDataSetChanged();
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