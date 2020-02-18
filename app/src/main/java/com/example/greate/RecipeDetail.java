package com.example.greate;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Picasso;

public class RecipeDetail extends AppCompatActivity {

    TextView recipeDescription, recipeTitle, recipeText, recipeCalorie;
    ImageView recipeImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        recipeDescription = (TextView)findViewById(R.id.txtDescription);
        recipeImage = (ImageView)findViewById(R.id.ivImage2);
        recipeTitle = (TextView)findViewById(R.id.txtTitle);
        recipeText = (TextView)findViewById(R.id.txtRecipe);
        recipeCalorie = (TextView)findViewById(R.id.calorieCount2);

        Bundle mBundle = getIntent().getExtras();

        if(mBundle != null){

            recipeDescription.setText(mBundle.getString("Description"));
            recipeTitle.setText(mBundle.getString("Title"));
            recipeText.setText(mBundle.getString("RecipeTxt"));
            recipeCalorie.setText(mBundle.getString("Calorie"));
            Picasso.get().load(mBundle.getString("Image")).into(recipeImage);

        }
    }
}
