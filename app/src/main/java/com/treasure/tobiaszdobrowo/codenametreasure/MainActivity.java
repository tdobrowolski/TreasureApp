package com.treasure.tobiaszdobrowo.codenametreasure;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

import static java.security.AccessController.getContext;

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

        // Swipe to delete
        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();
                adapter.removeItem(position);

            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                new RecyclerViewSwipeDecorator.Builder(MainActivity.getAppContext(), c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addBackgroundColor(ContextCompat.getColor(MainActivity.getAppContext(), R.color.colorDanger))
                        .addActionIcon(R.drawable.ic_delete_sweep_white_24dp)
                        .create()
                        .decorate();

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

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
