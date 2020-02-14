package com.example.greate;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.Calendar;

public class UploadRecipe extends AppCompatActivity {

    ImageView recipeImage;
    Uri uri;
    EditText input_name, input_description, input_recipe, input_calorie;
    String imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_recipe);

        recipeImage = (ImageView)findViewById(R.id.iv_foodImage);
        input_name = (EditText)findViewById(R.id.inputRecipeName);
        input_description = (EditText)findViewById(R.id.inputDescription);
        input_recipe = (EditText)findViewById(R.id.inputRecipe);
        input_calorie = (EditText)findViewById(R.id.inputCalorie);
    }

    public void btnSelectImage(View view) {

        Intent photoPicker = new Intent(Intent.ACTION_PICK);
        photoPicker.setType("image/*");
        startActivityForResult(photoPicker, 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {

            uri = data.getData();
            recipeImage.setImageURI(uri);


        }
        else Toast.makeText(this, "You haven't picked image", Toast.LENGTH_SHORT);
    }


    public void uploadImage() {

        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("RecipeImage").child(uri.getLastPathSegment());
        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while(!uriTask.isComplete());
                Uri urlImage = uriTask.getResult();
                imageUrl = urlImage.toString();
                uploadRecipe();

            }
        });


    }

    public void btnUploadRecipe(View view) {

        uploadImage();

    }

    public void uploadRecipe() {

        RecipeData recipeData = new RecipeData(
                input_name.getText().toString(),
                input_description.getText().toString(),
                input_recipe.getText().toString(),
                input_calorie.getText().toString(),
                imageUrl
        );

        String myCurrentDateTime = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

        FirebaseDatabase.getInstance().getReference("Recipe").child(myCurrentDateTime).setValue(recipeData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()) {
                    Toast.makeText(UploadRecipe.this, "Recipe Uploaded", Toast.LENGTH_SHORT);
                    finish();
                }



            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UploadRecipe.this, "Failed", Toast.LENGTH_SHORT);
            }
        });
    }
}
