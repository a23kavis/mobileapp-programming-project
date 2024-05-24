package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener{
    private RecyclerViewAdapter adapter;
    private List<RecyclerViewItem> items; // Declare but don't initialize yet
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button home_button = findViewById(R.id.about_button);
        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intenthome = new Intent(MainActivity.this, AboutPage.class);
                startActivity(intenthome);
            }});
        String url = "https://mobprog.webug.se/json-api?login=a23kavis";
        new JsonTask(this).execute(url);

        // Initialize items here
        items = new ArrayList<>();

        adapter = new RecyclerViewAdapter(this, items, new RecyclerViewAdapter.OnClickListener() {
            @Override
            public void onClick(RecyclerViewItem item) {
                Toast.makeText(MainActivity.this, item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView view = findViewById(R.id.recycler_view);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(adapter);
    }


    @Override
    public void onPostExecute(String json) {
        if (json != null) {


            // Process the JSON data
            List<RecyclerViewItem> processedItems = processJson(json);
            // Update the RecyclerView with the processed data
            items.clear(); // Clear existing items
            items.addAll(processedItems); // Add new items
            adapter.notifyDataSetChanged(); // Notify adapter of data change
        } else {
            Toast.makeText(this, "Failed to fetch JSON data", Toast.LENGTH_SHORT).show();
        }
    }
    private List<RecyclerViewItem> processJson(String json) {
        List<RecyclerViewItem> country = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                String location = jsonObject.getString("location");
                int size = jsonObject.getInt("size");
                String auxdata = jsonObject.getString("auxdata");

                // Create a RecyclerViewItem object and add it to the list
                country.add(new RecyclerViewItem(name, location, size, auxdata));
            }
            // Sort the list by population (size) in descending order


            // Update the items list and notify the adapter
            items.clear();
            items.addAll(country);
            adapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return country;
    }
}
