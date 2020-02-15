package com.example.greate;

public class RecipeData {

    private String itemName;
    private String itemDescription;
    private String itemRecipe;
    private String calorieCount;
    private String itemImage;

    public RecipeData() {

    }

    public RecipeData(String itemName, String itemDescription, String itemRecipe, String calorieCount, String itemImage) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemRecipe = itemRecipe;
        this.calorieCount = calorieCount;
        this.itemImage = itemImage;
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

    public String getItemImage() {
        return itemImage;
    }

    public String getCalorieCount() {
        return calorieCount;
    }
}
