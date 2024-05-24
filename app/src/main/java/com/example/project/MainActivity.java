package com.example.project;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String url = "https://mobprog.webug.se/json-api?login=a23kavis";
        new JsonTask(this).execute(url);

        ArrayList<RecyclerViewItem> items = new ArrayList<>(Arrays.asList(

                new RecyclerViewItem("Mont Blanc"),
                new RecyclerViewItem("Denali"),
                new RecyclerViewItem("Matterhorn")
        ));

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, items, new RecyclerViewAdapter.OnClickListener() {
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
            Toast.makeText(this, "JSON Data fetched", Toast.LENGTH_SHORT).show();
       
        } else {
            Toast.makeText(this, "Failed to fetch JSON data", Toast.LENGTH_SHORT).show();
        }
    }

}
