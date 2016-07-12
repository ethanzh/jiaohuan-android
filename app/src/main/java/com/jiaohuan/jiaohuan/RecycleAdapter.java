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

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_CELL = 1;
    private LayoutInflater mLayoutInflater;

    List<Contact> data = Collections.emptyList();

    public RecycleAdapter(Context context, List<Contact> data){
        mLayoutInflater = LayoutInflater.from(context);
        this.data = data;
    }

    public Contact getRow(int position) {
        return this.data.get(position);
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mLayoutInflater.inflate(R.layout.single_row, parent, false);

        myViewHolder holder = new myViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {

        Contact current = data.get(position);

        holder.name.setText(current.getName());
        holder.company.setText(current.getCompany());

        holder.icon.setImageBitmap(current.getPic());

        holder.date.setText(current.getSimple_date());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView company;
        ImageView icon;
        TextView date;

        public myViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name_tv);
            company = (TextView) itemView.findViewById(R.id.company);
            icon = (ImageView) itemView.findViewById(R.id.profile_pic);
            date = (TextView) itemView.findViewById(R.id.date);
        }
    }


    public void swap(List<Contact> datas){
        data.clear();
        data.addAll(datas);
        notifyDataSetChanged();
    }
}
