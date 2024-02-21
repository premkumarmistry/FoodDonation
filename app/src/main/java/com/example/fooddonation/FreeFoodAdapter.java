package com.example.fooddonation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FreeFoodAdapter extends RecyclerView.Adapter<FreeFoodAdapter.ViewHolder> {

    private List<FreeFoodAvailability> freeFoodList;

    public FreeFoodAdapter(List<FreeFoodAvailability> freeFoodList) {
        this.freeFoodList = freeFoodList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_free_food, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FreeFoodAvailability freeFoodAvailability = freeFoodList.get(position);
        holder.cityTextView.setText("City: " + freeFoodAvailability.getCity());
        holder.foodTypeTextView.setText("Food Type: " + freeFoodAvailability.getFoodType());
        holder.timingsTextView.setText("Timings: " + freeFoodAvailability.getTimings());
        holder.dateTextView.setText("Date: " + freeFoodAvailability.getDate());
        holder.placeTextView.setText("Place: " + freeFoodAvailability.getPlace());
    }

    @Override
    public int getItemCount() {
        return freeFoodList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView cityTextView;
        public TextView foodTypeTextView;
        public TextView timingsTextView;
        public TextView dateTextView;
        public TextView placeTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cityTextView = itemView.findViewById(R.id.cityTextView);
            foodTypeTextView = itemView.findViewById(R.id.foodTypeTextView);
            timingsTextView = itemView.findViewById(R.id.timingsTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            placeTextView = itemView.findViewById(R.id.placeTextView);
        }
    }
}
