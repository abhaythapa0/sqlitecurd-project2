package com.example.sqlite_project2;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseHelper databaseHelper;
    ArrayList<ItemsModel> itemsList;
    ItemsAdapter itemsAdapter;
    Button addItemsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);
        Button btn1=findViewById(R.id.add_items);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseHelper = new DatabaseHelper(this);
        itemsList = new ArrayList<>();

        // Load data from SQLite database
        fetchItemsFromDatabase();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, Add_Items.class);
                startActivity(intent);

            }
        });
    }

    private void fetchItemsFromDatabase() {
        Cursor cursor = databaseHelper.getAllItems();

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No items found", Toast.LENGTH_SHORT).show();
            return;
    }
        while (cursor.moveToNext()) {
            String name = cursor.getString(0);
            String description = cursor.getString(1);
            String imageUri = cursor.getString(2);

            ItemsModel item = new ItemsModel(name, description, Uri.parse(imageUri));
            itemsList.add(item);
        }
        cursor.close();

        itemsAdapter = new ItemsAdapter(itemsList, new RecyclerViewClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "Clicked: " + itemsList.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setAdapter(itemsAdapter);
    }


    @Override
    protected void onResume() {
        super.onResume();
        itemsList.clear();
        fetchItemsFromDatabase();
    }
}