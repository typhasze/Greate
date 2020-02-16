package com.example.greate;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    ImageButton recipeSelect;
    ImageButton recordCalorie;
    ImageButton nearbyRestaurant;
    ImageButton randomRecipe;

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
}
