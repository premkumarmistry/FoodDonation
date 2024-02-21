package com.example.fooddonation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class DonationAdapter extends RecyclerView.Adapter<DonationAdapter.ViewHolder> {

    private List<Donation> donationsList;

    public DonationAdapter(List<Donation> donationsList) {
        this.donationsList = donationsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_donation, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Donation donation = donationsList.get(position);

        // Bind data to ViewHolder
        holder.foodTypeTextView.setText("Food Type: " + donation.getFoodType());
        holder.quantityTextView.setText("Quantity: " + donation.getQuantity());
        holder.expiryDateTextView.setText("Expiry Date: " + donation.getExpiryDate());
        holder.donorTypeTextView.setText("Donor Type: " + donation.getDonorType());
    }

    @Override
    public int getItemCount() {
        return donationsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView foodTypeTextView;
        TextView quantityTextView;
        TextView expiryDateTextView;
        TextView donorTypeTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodTypeTextView = itemView.findViewById(R.id.foodTypeTextView);
            quantityTextView = itemView.findViewById(R.id.quantityTextView);
            expiryDateTextView = itemView.findViewById(R.id.expiryDateTextView);
            donorTypeTextView = itemView.findViewById(R.id.donorTypeTextView);
        }
    }
}
