package com.example.tobiaszdobrowo.codenametreasure;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static Context context;
    RecyclerView recyclerView;
    private TextView emptyView;
    private ImageView emptyView2;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivity.context = getApplicationContext();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewEntryActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        emptyView = (TextView) findViewById(R.id.empty_view);
        emptyView2 = (ImageView) findViewById(R.id.empty_view2);
        recyclerView.setHasFixedSize(true); // optymalizacja

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setItemAnimator(new DefaultItemAnimator()); // dodaj / usuń element

        // tworzymy adapter oraz łączymy go z RecyclerView
        adapter = new MyAdapter(recyclerView);
        recyclerView.setAdapter(adapter);

        visibilityCheck(adapter.getItemCount());

    }

    @Override
    protected void onResume() {

        super.onResume();
        if(adapter != null) {
            adapter.updateItems();
            visibilityCheck(adapter.getItemCount());
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        visibilityCheck(adapter.getItemCount());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public static Context getAppContext() {
        return MainActivity.context;
    }

    public void visibilityCheck(int amount) {

        int orientation = getResources().getConfiguration().orientation;

        if (amount == 0) {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
            if (orientation == 1) {
                emptyView2.setVisibility(View.VISIBLE);
            } else {
                emptyView2.setVisibility(View.INVISIBLE);
            }
        }
        else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
            emptyView2.setVisibility(View.GONE);
        }
    }

}
