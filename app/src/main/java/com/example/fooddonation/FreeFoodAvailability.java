package com.example.fooddonation;

public class FreeFoodAvailability {

    private String city;
    private String foodType;
    private String timings;
    private String date;
    private String place;

    public FreeFoodAvailability() {
        // Default constructor required for Firestore
    }

    public FreeFoodAvailability(String city, String foodType, String timings, String date, String place) {
        this.city = city;
        this.foodType = foodType;
        this.timings = timings;
        this.date = date;
        this.place = place;
    }

    public String getCity() {
        return city;
    }

    public String getFoodType() {
        return foodType;
    }

    public String getTimings() {
        return timings;
    }

    public String getDate() {
        return date;
    }

    public String getPlace() {
        return place;
    }
}
