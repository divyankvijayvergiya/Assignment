package com.teamvariance.teamvariance1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.teamvariance.teamvariance1.Model.Items;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by divyankvijayvergiya on 12/03/18.
 */

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ListViewHolder> {
    private ArrayList<Items> itemsArrayList;

    public ListViewAdapter(ArrayList<Items> itemsArrayList){
        this.itemsArrayList = itemsArrayList;
    }
    @Override
    public ListViewAdapter.ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutForListItem = R.layout.list_items;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layoutForListItem, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListViewAdapter.ListViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if(itemsArrayList!=null){
            return itemsArrayList.size();
        }
        else {
            return 0;
        }
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        public final ImageView imageView;
        public final TextView category;
        public final TextView created;
        public final  TextView updated;

        public ListViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            category = itemView.findViewById(R.id.category);
            created = itemView.findViewById(R.id.created);
            updated = itemView.findViewById(R.id.updated);
        }

        void onBind(int position){
            if(!itemsArrayList.isEmpty()){
                Picasso.with(itemView.getContext()).load(itemsArrayList.get(position).getImage()).into(imageView);
                category.setText(itemsArrayList.get(position).getCategory());
                created.setText(parseDateToddMMyyyy(itemsArrayList.get(position).getCreatedAt()));
                updated.setText(parseDateToddMMyyyy(itemsArrayList.get(position).getUpdatedAt()));
            }
        }
        public String parseDateToddMMyyyy(String time) {
            String inputPattern = "yyyy-MM-dd HH:mm:ss";
            String outputPattern = "dd-MMM-yyyy h:mm a";
            SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
            SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

            Date date = null;
            String str = null;

            try {
                date = inputFormat.parse(time);
                str = outputFormat.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return str;
        }

    }
}
