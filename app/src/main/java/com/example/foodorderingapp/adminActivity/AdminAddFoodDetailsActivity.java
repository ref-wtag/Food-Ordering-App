package com.example.foodorderingapp.adminActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodorderingapp.R;

public class AdminAddFoodDetailsActivity extends AppCompatActivity {

    private static final int IMAGE_REQUEST_CODE = 1001;

    private EditText editTextFoodName;
    private EditText editTextFoodDescription;
    private EditText editTextFoodPrice;
    private ImageView imageViewUpload;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_food_details);

        editTextFoodName = findViewById(R.id.editTextFoodName);
        editTextFoodDescription = findViewById(R.id.editTextFoodDescription);
        editTextFoodPrice = findViewById(R.id.editTextFoodPrice);
        imageViewUpload = findViewById(R.id.imageViewUpload);
        Button buttonUploadImage = findViewById(R.id.buttonUploadImage);
        Button buttonSaveFoodDetails = findViewById(R.id.buttonSaveFoodDetails);

        buttonUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImageChooser();
            }
        });

        buttonSaveFoodDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFoodDetails();
            }
        });
    }

    private void openImageChooser() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, IMAGE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            imageViewUpload.setImageURI(imageUri);
        }
    }

    private void saveFoodDetails() {
        String foodName = editTextFoodName.getText().toString().trim();
        String foodDescription = editTextFoodDescription.getText().toString().trim();
        String foodPrice = editTextFoodPrice.getText().toString().trim();

        if (foodName.isEmpty() || foodDescription.isEmpty() || foodPrice.isEmpty() || imageUri == null) {
            Toast.makeText(this, "Please fill in all fields and upload an image", Toast.LENGTH_SHORT).show();
            return;
        }

        // Here you would save the food details to your database or API
        Toast.makeText(this, "Food details saved successfully!", Toast.LENGTH_SHORT).show();
        finish(); // Close the activity after saving
    }
}