package com.example.greate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RecipeSelection extends AppCompatActivity {

    RecyclerView mRecyclerView;
    List<RecipeData> myRecipeList;
    RecipeData mRecipeData;

    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_selection);

        mRecyclerView = (RecyclerView)findViewById(R.id.recycleView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(RecipeSelection.this, 1);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        myRecipeList = new ArrayList<>();


        final MyAdapter myAdapter = new MyAdapter(RecipeSelection.this, myRecipeList);
        mRecyclerView.setAdapter(myAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Recipe");

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                myRecipeList.clear();

                for (DataSnapshot itemSnapShot: dataSnapshot.getChildren()) {

                    RecipeData recipeData = itemSnapShot.getValue(RecipeData.class);
                    myRecipeList.add(recipeData);

                }

                myAdapter.notifyDataSetChanged();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void btn_uploadActivity(View view) {

        startActivity(new Intent(this, UploadRecipe.class));

    }
}
