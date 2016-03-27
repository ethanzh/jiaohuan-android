package com.jiaohuan.jiaohuan;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Collections;
import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.myViewHolder> {

    private LayoutInflater mLayoutInflater;

    List<OneRow> data = Collections.emptyList();

    public RecycleAdapter(Context context, List<OneRow> data){
        mLayoutInflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mLayoutInflater.inflate(R.layout.single_row, parent, false);

        myViewHolder holder = new myViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {

        OneRow current = data.get(position);

        holder.name.setText(current.names);
        holder.email.setText(current.emails);
        holder.phone.setText(current.phone_nums);
        holder.location.setText(current.locations);
        holder.company.setText(current.company);
        holder.icon.setImageResource(current.pictures);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView company;
        TextView email;
        TextView phone;
        TextView location;
        ImageView icon;

        public myViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            company = (TextView) itemView.findViewById(R.id.company);
            email = (TextView) itemView.findViewById(R.id.email);
            phone = (TextView) itemView.findViewById(R.id.phone);
            location = (TextView) itemView.findViewById(R.id.location);

            icon = (ImageView) itemView.findViewById(R.id.image_view);
        }
    }
}
