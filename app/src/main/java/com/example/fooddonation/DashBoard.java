package com.example.fooddonation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class DashBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        // Reference to the CardViews
        CardView cardView1 = findViewById(R.id.cardView1);
        CardView cardView2 = findViewById(R.id.cardView2);
        CardView cardView3 = findViewById(R.id.cardView3);
        CardView cardView4 = findViewById(R.id.cardView4);

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform an action when the card is clicked
                // For example, show a toast with the card's text
                startActivity(new Intent(getApplicationContext(), FreeFoodListActivity.class));
            }
        });



        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform an action when the card is clicked
                // For example, show a toast with the card's text
                startActivity(new Intent(getApplicationContext(), ViewStoreDonations.class));
            }
        });



        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform an action when the card is clicked
                // For example, show a toast with the card's text
                startActivity(new Intent(getApplicationContext(), PostAvailabilityActivity.class));
            }
        });

        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform an action when the card is clicked
                // For example, show a toast with the card's text
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });


    }
}