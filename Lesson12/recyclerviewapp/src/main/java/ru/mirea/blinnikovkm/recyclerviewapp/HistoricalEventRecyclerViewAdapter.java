package ru.mirea.blinnikovkm.recyclerviewapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoricalEventRecyclerViewAdapter extends
        RecyclerView.Adapter<HistoricalEventViewHolder> {
    private List<HistoricalEvent> events;
    private Context context;

    public HistoricalEventRecyclerViewAdapter(List<HistoricalEvent> events) {
        this.events = events;
    }

    @NonNull
    @Override
    public HistoricalEventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.historical_event_item, parent,
                false);
        return new HistoricalEventViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoricalEventViewHolder holder, int position) {
        HistoricalEvent event = events.get(position);

        holder.getTextViewEventName().setText(event.getName());
        holder.getTextViewEventDesc().setText(event.getDesc());

        int resID = context.getResources().getIdentifier(event.getImgUrl(), "drawable",
                context.getPackageName());
        holder.getImageViewEvent().setImageResource(resID);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}
