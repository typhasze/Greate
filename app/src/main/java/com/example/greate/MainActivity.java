package com.example.greate;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageButton recipeSelect;
    ImageButton recordCalorie;
    ImageButton nearbyRestaurant;
    ImageButton randomRecipe;
    private DatabaseReference databaseReference;
    String itemImage;
    String itemDescription;
    String itemName;
    String itemRecipe;
    String itemCalorie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(myToolbar);

        recipeSelect = (ImageButton) findViewById(R.id.selectRecipe);
        recordCalorie = (ImageButton) findViewById(R.id.recordCalorie);
        nearbyRestaurant = (ImageButton) findViewById(R.id.nearbyRestaurant);
        randomRecipe = (ImageButton) findViewById(R.id.randomRecipe);

        recipeSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRecipeSelect();
            }
        });

        nearbyRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNearbyRestaurant();
            }
        });

        randomRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRandomRecipe();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public void openRecipeSelect() {
        Intent intent = new Intent(this, RecipeSelection.class);
        startActivity(intent);
    }

    public void openNearbyRestaurant() {
        Intent intent = new Intent(this, GoogleMapsActivity.class);
        startActivity(intent);
    }

    public void openRandomRecipe() {

        final ArrayList<RecipeData> myRecipeList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Recipe");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                long n = dataSnapshot.getChildrenCount();
                for (DataSnapshot itemSnapShot: dataSnapshot.getChildren()) {
                    RecipeData recipeData = itemSnapShot.getValue(RecipeData.class);
                    myRecipeList.add(recipeData);
                }
                int i = (int)(Math.random() * (n - 0 + 1) + 0);
                RecipeData recipeToUse = myRecipeList.get(i);
                itemImage = recipeToUse.getItemImage();
                itemDescription = recipeToUse.getItemDescription();
                itemName = recipeToUse.getItemName();
                itemRecipe = recipeToUse.getItemRecipe();
                itemCalorie = recipeToUse.getCalorieCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Intent intent = new Intent(this, RecipeDetail.class);
        intent.putExtra("Image", itemImage);
        intent.putExtra("Description", itemDescription);
        intent.putExtra("Title", itemName);
        intent.putExtra("RecipeTxt", itemRecipe);
        intent.putExtra("Calorie", itemCalorie);
        startActivity(intent);

    }
}
