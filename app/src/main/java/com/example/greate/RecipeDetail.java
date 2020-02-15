package com.example.greate;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RecipeDetail extends AppCompatActivity {

    TextView recipeDescription, recipeTitle;
    ImageView recipeImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        recipeDescription = (TextView)findViewById(R.id.txtRecipe);
        recipeImage = (ImageView)findViewById(R.id.ivImage2);
        recipeTitle = (TextView)findViewById(R.id.txtTitle);

        Bundle mBundle = getIntent().getExtras();

        if(mBundle != null){

            recipeDescription.setText(mBundle.getString("Description"));
            recipeTitle.setText(mBundle.getString("Title"));
            //recipeImage.setImageResource(mBundle.getInt("Image"));
            //Picasso.get().load(mBundle.getInt("Image")).into(recipeImage);

        }
    }
}
