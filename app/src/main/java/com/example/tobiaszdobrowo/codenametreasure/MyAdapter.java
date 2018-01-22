package com.example.tobiaszdobrowo.codenametreasure;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

/**
 * Created by tobiaszdobrowo on 03.12.2017.
 */

public class MyAdapter extends RecyclerView.Adapter {

    Context mContext = MainActivity.getAppContext();
    database db = new database(mContext);

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

    public MyAdapter(RecyclerView pRecyclerView) {

        //mContext = context;
        //mCursor = cursor;
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
                    // kod usuwania obiektu
                    notifyItemRemoved(positionToDelete);
                }
            });

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        //mCursor.moveToPosition(position);

        /*Object sObj = db.getObject(position);
        ((MyViewHolder) holder).mName.setText(sObj.getName());
        ((MyViewHolder) holder).mDate.setText(sObj.getDate());
        ((MyViewHolder) holder).mObject.setText(sObj.getObject());*/
    }

    @Override
    public int getItemCount() {
        List<Object> objects = db.getAllObjects();
        return objects.size();
        //return 0;
    }

}
