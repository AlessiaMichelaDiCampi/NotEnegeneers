package com.example.notengeneer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Event> list;

    public MyAdapter(Context context, ArrayList<Event> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.event, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Event event = list.get(position);

        holder.eventName.setText(event.getName());
        holder.eventDescription.setText(event.getDescription());
        holder.eventType.setText(event.getType());
        holder.eventDate.setText(event.getStartDate());
        holder.eventFinishDate.setText(event.getFinishDate());
        holder.eventPosition.setText(event.getPosition());
        holder.eventManager.setText(event.getManager());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView eventName, eventDescription, eventType, eventDate, eventFinishDate, eventPosition, eventManager;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.eventName = itemView.findViewById(R.id.firstEventName);
            this.eventDescription = itemView.findViewById(R.id.firstEventDescription);
            this.eventType = itemView.findViewById(R.id.firstEventType);
            this.eventDate = itemView.findViewById(R.id.firstEventDate);
            this.eventFinishDate = itemView.findViewById(R.id.firstEventFinishDate);
            this.eventPosition = itemView.findViewById(R.id.firstEventPosition);
            this.eventManager = itemView.findViewById(R.id.firstEventManager);
        }
    }
}
