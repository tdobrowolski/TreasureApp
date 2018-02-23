package com.example.tobiaszdobrowo.codenametreasure;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    private List<Object> items;

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

        mRecyclerView = pRecyclerView;

        items = db.getAllObjects();
    }

    public void updateItems() {

        items = db.getAllObjects();
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycle_object_layout, parent, false);

            // usuniecie obiektu
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {

                    AlertDialog.Builder builder1 = new AlertDialog.Builder(parent.getContext());
                    builder1.setMessage(R.string.popup_text);
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            R.string.yes_text,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    int positionToDelete = mRecyclerView.getChildAdapterPosition(v);
                                    db.deleteObject(items.get(positionToDelete));
                                    updateItems();
                                    notifyItemRemoved(positionToDelete);

                                    dialog.cancel();
                                }
                            });

                    builder1.setNegativeButton(
                            R.string.no_text,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
            });

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Object sObj = items.get(position);
        ((MyViewHolder) holder).mName.setText(sObj.getName());
        ((MyViewHolder) holder).mDate.setText(sObj.getDate());
        ((MyViewHolder) holder).mObject.setText(sObj.getObject());
    }

    @Override
    public int getItemCount() {
        List<Object> objects = db.getAllObjects();
        return objects.size();
        //return 0;
    }

}
