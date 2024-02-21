package com.example.fooddonation;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Spinner foodTypeSpinner;
    private EditText quantityEditText;
    private DatePicker expiryDatePicker;
    private Spinner donorTypeSpinner;
    private Button submitButton;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        foodTypeSpinner = findViewById(R.id.foodTypeSpinner);
        quantityEditText = findViewById(R.id.quantityEditText);
        expiryDatePicker = findViewById(R.id.expiryDatePicker);
        donorTypeSpinner = findViewById(R.id.donorTypeSpinner);
        submitButton = findViewById(R.id.submitButton);

        // Set up Firebase
        db = FirebaseFirestore.getInstance();


        // Set up Spinners
        setUpFoodTypeSpinner();
        setUpDonorTypeSpinner();

        // Set up Button click listener
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitDonation();
            }
        });
    }

    private void setUpFoodTypeSpinner() {
        // Dummy data for food types
        String[] foodTypes = {"Fruits", "Vegetables", "Dairy", "Grains", "Proteins"};

        ArrayAdapter<String> foodTypeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, foodTypes);
        foodTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foodTypeSpinner.setAdapter(foodTypeAdapter);
    }

    private void setUpDonorTypeSpinner() {
        // Dummy data for donor types
        String[] donorTypes = {"Restaurant", "Grocery Store", "Catering Service"};

        ArrayAdapter<String> donorTypeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, donorTypes);
        donorTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        donorTypeSpinner.setAdapter(donorTypeAdapter);
    }

    private void submitDonation() {
        String foodType = foodTypeSpinner.getSelectedItem().toString();
        String quantity = quantityEditText.getText().toString();
        String expiryDate = getFormattedDate();
        String donorType = donorTypeSpinner.getSelectedItem().toString();

        // You can add validation for the input fields here

        // Create a new document in the "donations" collection
        db.collection("donations")
                .add(new Donation(foodType, quantity, expiryDate, donorType))
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(MainActivity.this, "Donation submitted!", Toast.LENGTH_SHORT).show();
                    // Clear input fields after successful submission
                    clearInputFields();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(MainActivity.this, "Error submitting donation", Toast.LENGTH_SHORT).show();
                });
    }

    private String getFormattedDate() {
        int day = expiryDatePicker.getDayOfMonth();
        int month = expiryDatePicker.getMonth() + 1; // Months are zero-based
        int year = expiryDatePicker.getYear();

        return String.format("%02d-%02d-%04d", day, month, year);
    }

    private void clearInputFields() {
        quantityEditText.getText().clear();
        // You may customize this method based on additional fields
    }
}
