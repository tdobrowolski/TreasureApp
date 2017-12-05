package com.example.tobiaszdobrowo.codenametreasure;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tobiaszdobrowo on 03.12.2017.
 */

public class MyAdapter extends RecyclerView.Adapter {

    private ArrayList<Object> mNames = new ArrayList<>();

    private ArrayList<Object> mDates = new ArrayList<>();

    private ArrayList<Object> mObjects = new ArrayList<>();

    private RecyclerView mRecyclerView;

    private class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView mName, mDate, mObject;

        public MyViewHolder(View pItem) {

            super(pItem);
            mName = (TextView) pItem.findViewById(R.id.text_name);
            mDate = (TextView) pItem.findViewById(R.id.text_date);
            mObject = (TextView) pItem.findViewById(R.id.text_object);

        }

    }

    public MyAdapter(ArrayList<Object> pNames, ArrayList<Object> pDates, ArrayList<Object> pObjects, RecyclerView pRecyclerView){
        mNames = pNames;
        mDates = pDates;
        mObjects = pObjects;
        mRecyclerView = pRecyclerView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycle_object_layout, parent, false);

            // usuniecie obiektu
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    int positionToDelete = mRecyclerView.getChildAdapterPosition(v);
                    mNames.remove(positionToDelete);
                    mDates.remove(positionToDelete);
                    mObjects.remove(positionToDelete);
                    notifyItemRemoved(positionToDelete);
                }

        });

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Object object = mNames.get(position);
        ((MyViewHolder) holder).mName.setText(object.getName());

        Object object1 = mDates.get(position);
        ((MyViewHolder) holder).mDate.setText(object1.getDate());

        Object object2 = mObjects.get(position);
        ((MyViewHolder) holder).mObject.setText(object2.getObject());

    }

    @Override
    public int getItemCount() {
        return mNames.size();
    }

}
