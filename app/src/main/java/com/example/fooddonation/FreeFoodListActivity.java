package com.example.fooddonation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;

public class FreeFoodListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FreeFoodAdapter freeFoodAdapter;
    private List<FreeFoodAvailability> freeFoodList;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_food_list);

        db = FirebaseFirestore.getInstance();
        freeFoodList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        freeFoodAdapter = new FreeFoodAdapter(freeFoodList);
        recyclerView.setAdapter(freeFoodAdapter);

        // Fetch data from Firestore and populate the RecyclerView
        fetchDataFromFirestore();



    }

    private void fetchDataFromFirestore() {
        db.collection("free_food_availabilities")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            FreeFoodAvailability freeFoodAvailability = document.toObject(FreeFoodAvailability.class);
                            freeFoodList.add(freeFoodAvailability);
                        }
                        freeFoodAdapter.notifyDataSetChanged();
                    } else {
                        // Handle errors
                    }
                });
    }
}
