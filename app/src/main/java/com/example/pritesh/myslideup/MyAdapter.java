package com.example.pritesh.myslideup;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = MyAdapter.class.getSimpleName();
    private List<DataHolder> your_array_list;
    private Context context;
    private LayoutInflater inflater;


    MyAdapter(List<DataHolder> your_array_list, Context context)
    {
        this.your_array_list = your_array_list;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        if(viewHolder instanceof  MyViewHolder)
        {
            MyViewHolder holder = (MyViewHolder) viewHolder;

            //Log.d(TAG,""+your_array_list.get(i));

            holder.text.setText(your_array_list.get(i).getName());
        }

    }

    @Override
    public int getItemCount() {
        Log.d(TAG,"your_array_list.size() = "+your_array_list.size());
        return your_array_list.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView text;

        public MyViewHolder(View view) {
            super(view);

            text = view.findViewById(R.id.text);


        }
    }
}
