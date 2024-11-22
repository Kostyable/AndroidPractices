package ru.mirea.blinnikovkm.recyclerviewapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HistoricalEventViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageViewEvent;
    private TextView textViewEventName;
    private TextView textViewEventDesc;

    public HistoricalEventViewHolder(@NonNull View itemView) {
        super(itemView);
        this.imageViewEvent = itemView.findViewById(R.id.imageViewEvent);
        this.textViewEventName = itemView.findViewById(R.id.textViewEventName);
        this.textViewEventDesc = itemView.findViewById(R.id.textViewEventDesc);
    }

    public ImageView getImageViewEvent() {
        return imageViewEvent;
    }

    public TextView getTextViewEventName() {
        return textViewEventName;
    }

    public TextView getTextViewEventDesc() {
        return textViewEventDesc;
    }
}
