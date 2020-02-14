package com.example.greate;

public class RecipeData {

    private String itemName;
    private String itemDescription;
    private String itemRecipe;
    private int calorieCount;
    private int itemImage;

    public RecipeData(String itemName, String itemDescription, String itemRecipe, int calorieCount, int itemImage) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemRecipe = itemRecipe;
        this.itemImage = itemImage;
        this.calorieCount = calorieCount;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public String getItemRecipe() {
        return itemRecipe;
    }

    public int getItemImage() {
        return itemImage;
    }

    public int getCalorieCount() {
        return calorieCount;
    }
}
