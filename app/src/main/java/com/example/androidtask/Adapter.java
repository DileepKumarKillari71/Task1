package com.example.androidtask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

public class Adapter  extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<Model> list = new ArrayList<Model>();
    Context context;

    public Adapter(Context context)
    {
        this.context = context;
    }
    public Adapter(List<Model> list,Context context)
    {
        this.list = list;
        this.context = context;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.charName.setText(list.get(position).getCharName());
        holder.name.setText(list.get(position).getName());
        Glide.with(context).load(list.get(position).getPhoto())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.photo);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context  = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View photoView = inflater.inflate(R.layout.layout_adapter, parent, false);

        ViewHolder viewHolder = new ViewHolder(photoView);
        return viewHolder;

    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

   public void setData(List<Model> list){
        this.list = list;
        notifyDataSetChanged();
   }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView photo ;
        public TextView charName , name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            photo = (ImageView) itemView.findViewById(R.id.photo);
            name = (TextView) itemView.findViewById(R.id.name);
            charName = (TextView) itemView.findViewById(R.id.charName);

        }
    }
}
