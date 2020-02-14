package com.example.greate;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecipeSelection extends AppCompatActivity {

    RecyclerView mRecyclerView;
    List<RecipeData> myRecipeList;
    RecipeData mRecipeData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_selection);

        mRecyclerView = (RecyclerView)findViewById(R.id.recycleView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(RecipeSelection.this, 1);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        myRecipeList = new ArrayList<>();


        mRecipeData = new RecipeData("Some Baked Asparagus", "Who doesn't love baked asparagus", "Baked Asparagus on Bread with Cheese", 250, R.drawable.baf);
        myRecipeList.add(mRecipeData);

        mRecipeData = new RecipeData("BCGP", "Food 2", "Food 2", 350, R.drawable.bcgp);
        myRecipeList.add(mRecipeData);

        mRecipeData = new RecipeData("KCSF", "Food 3", "Food 3", 450, R.drawable.kcsf);
        myRecipeList.add(mRecipeData);

        mRecipeData = new RecipeData("KHP", "Food 4", "Food 4", 550, R.drawable.khp);
        myRecipeList.add(mRecipeData);

        MyAdapter myAdapter = new MyAdapter(RecipeSelection.this, myRecipeList);
        mRecyclerView.setAdapter(myAdapter);

    }
}
