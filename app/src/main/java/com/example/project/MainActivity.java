package com.example.project;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener{
    private RecyclerViewAdapter adapter;
    private List<RecyclerViewItem> items = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String url = "https://mobprog.webug.se/json-api?login=a23kavis";
        new JsonTask(this).execute(url);



         List<RecyclerViewItem> items = new ArrayList<>();
         adapter = new RecyclerViewAdapter(this, items, new RecyclerViewAdapter.OnClickListener() {

            @Override
            public void onClick(RecyclerViewItem item) {
                Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        RecyclerView view = findViewById(R.id.recycler_view);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(adapter);
    }



    @Override
    public void onPostExecute(String json) {
        if (json != null) {
            Log.d("JsonTask", "JSON Data: " + json);
            Toast.makeText(this, "JSON Data fetched", Toast.LENGTH_SHORT).show();
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
        List<RecyclerViewItem> tempItems = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                String location = jsonObject.getString("location");
                int size = jsonObject.getInt("size");
                String auxdata = jsonObject.getString("auxdata");

                // Create a RecyclerViewItem object and add it to the list
                tempItems.add(new RecyclerViewItem(name, location, size, auxdata));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return tempItems;
    }
}
