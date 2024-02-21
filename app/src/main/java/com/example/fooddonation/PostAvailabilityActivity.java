package com.example.fooddonation;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class PostAvailabilityActivity extends AppCompatActivity {

    private EditText cityEditText;
    private EditText foodTypeEditText;
    private EditText timingsEditText;
    private EditText dateEditText;
    private EditText placeEditText;
    private Button postAvailabilityButton,fetch;

    private EditText timeEditText;
    private Calendar selectedDate = Calendar.getInstance();
    private Calendar selectedTime = Calendar.getInstance();

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_availability);

        db = FirebaseFirestore.getInstance();

        cityEditText = findViewById(R.id.cityEditText);
        foodTypeEditText = findViewById(R.id.foodTypeEditText);
        timeEditText = findViewById(R.id.timeEditText);
        dateEditText = findViewById(R.id.dateEditText);
        placeEditText = findViewById(R.id.placeEditText);
        postAvailabilityButton = findViewById(R.id.postAvailabilityButton);


        dateEditText = findViewById(R.id.dateEditText);
        timeEditText = findViewById(R.id.timeEditText);

        // Set up date and time pickers
        updateDateLabel();
        updateTimeLabel();
        postAvailabilityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postFreeFoodAvailability();
                // Navigate to the activity for displaying the list of free food availabilities

            }
        });
    }

    private void postFreeFoodAvailability() {
        String city = cityEditText.getText().toString();
        String foodType = foodTypeEditText.getText().toString();
        String timings = timeEditText.getText().toString();
        String date = dateEditText.getText().toString();
        String place = placeEditText.getText().toString();

        // You can add validation for the input fields here

        // Create a new document in the "free_food_availabilities" collection
        db.collection("free_food_availabilities")
                .add(new FreeFoodAvailability(city, foodType, timings, date, place))
                .addOnSuccessListener(documentReference -> {
                    // Clear input fields after successful submission
                    Toast.makeText(getApplicationContext(), "Submission Successfull , Thanks For Helping !", Toast.LENGTH_SHORT).show();

                })
                .addOnFailureListener(e -> {
                    // Handle the failure to post availability
                    Toast.makeText(getApplicationContext(), "Error Submitting !", Toast.LENGTH_SHORT).show();


                });
    }

    private void clearInputFields() {
        cityEditText.getText().clear();
        foodTypeEditText.getText().clear();
        timingsEditText.getText().clear();
        dateEditText.getText().clear();
        placeEditText.getText().clear();
    }

    public void showDatePickerDialog(View v) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                dateSetListener,
                selectedDate.get(Calendar.YEAR),
                selectedDate.get(Calendar.MONTH),
                selectedDate.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public void showTimePickerDialog(View v) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                timeSetListener,
                selectedTime.get(Calendar.HOUR_OF_DAY),
                selectedTime.get(Calendar.MINUTE),
                false);
        timePickerDialog.show();
    }

    private void updateDateLabel() {
        String myFormat = "MM/dd/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        dateEditText.setText(sdf.format(selectedDate.getTime()));
    }

    private void updateTimeLabel() {
        String myFormat = "hh:mm a";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        timeEditText.setText(sdf.format(selectedTime.getTime()));
    }

    private DatePickerDialog.OnDateSetListener dateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    selectedDate.set(Calendar.YEAR, year);
                    selectedDate.set(Calendar.MONTH, monthOfYear);
                    selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateDateLabel();
                }
            };

    private TimePickerDialog.OnTimeSetListener timeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    selectedTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    selectedTime.set(Calendar.MINUTE, minute);
                    updateTimeLabel();
                }
            };




}
