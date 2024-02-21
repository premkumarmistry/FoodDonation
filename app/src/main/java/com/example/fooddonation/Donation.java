package com.example.fooddonation;

public class Donation {

    private String foodType;
    private String quantity;
    private String expiryDate;
    private String donorType;

    public Donation() {
        // Default constructor required for Firestore
    }

    public Donation(String foodType, String quantity, String expiryDate, String donorType) {
        this.foodType = foodType;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
        this.donorType = donorType;
    }

    public String getFoodType() {
        return foodType;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getDonorType() {
        return donorType;
    }
}
