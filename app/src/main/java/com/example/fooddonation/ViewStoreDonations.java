package com.example.fooddonation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import java.util.ArrayList;
import java.util.List;

public class ViewStoreDonations extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DonationAdapter adapter;
    private List<Donation> donationsList;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_store_donations);

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        // Initialize RecyclerView and its adapter
        recyclerView = findViewById(R.id.recyclerView);
        donationsList = new ArrayList<>();
        adapter = new DonationAdapter(donationsList);

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Query donations data from Firestore
        Query query = db.collection("donations");
        query.addSnapshotListener((value, error) -> {
            if (error != null) {
                // Handle error
                return;
            }

            for (DocumentChange documentChange : value.getDocumentChanges()) {
                switch (documentChange.getType()) {
                    case ADDED:
                        Donation donation = documentChange.getDocument().toObject(Donation.class);
                        donationsList.add(donation);
                        adapter.notifyDataSetChanged();
                        break;
                    // Handle other types (MODIFIED, REMOVED) if needed
                }
            }
        });
    }
}
