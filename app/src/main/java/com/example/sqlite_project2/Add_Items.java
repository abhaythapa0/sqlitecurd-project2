package com.example.sqlite_project2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.github.dhaval2404.imagepicker.ImagePicker;

import de.hdodenhof.circleimageview.CircleImageView;

public class Add_Items extends AppCompatActivity {
    CircleImageView imageView;
    EditText txt_name, txt_description;
    Button Add_btn;
    Uri imageUri;


    private final static int IMAGE_PICKER_REQUEST = 998;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_items);

        imageView=findViewById(R.id.img);
        txt_name=findViewById(R.id.txt1);
        txt_description=findViewById(R.id.txt2);
        Add_btn=findViewById(R.id.btn);

        imageView.setOnClickListener(this::pickImage);
        Add_btn.setOnClickListener(this::clickAddItems);

    }

    private void pickImage(View view) {
        ImageUtility.picImage(view, Add_Items.this, IMAGE_PICKER_REQUEST);


    }

    private void clickAddItems(View view) {
      String name=txt_name.getText().toString().trim();
      if(name.isEmpty()){
          txt_name.setError("Name is Mandatory");
          txt_name.requestFocus();
          return;
      }
      String description=txt_description.getText().toString().trim();
      if(description.isEmpty()){
          txt_description.setError("Description is mandatory");
          txt_description.requestFocus();
          return;
      }
      if (imageUri == null) {
          Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
          return;

      }

      DatabaseHelper db = new DatabaseHelper(this);
      boolean insertData = db.insertData(name, description, imageUri.toString());
      if (insertData) {
          Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
          Intent intent = new Intent(getApplicationContext(), MainActivity.class);
          startActivity(intent);
      }
      else {
          Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
      }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == IMAGE_PICKER_REQUEST) {
            imageUri = data.getData();

            if (imageUri != null) {
                imageView.setImageURI(imageUri);
            }
        } else {
            Toast.makeText(this, "Image selection failed", Toast.LENGTH_SHORT).show();
        }
    }



}