package com.example.sqlite_project2;

import android.content.ClipData;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {

    private final RecyclerViewClickListener recyclerViewClickListener;
    private final ArrayList<ItemsModel>itemsModels;
    public ItemsAdapter(ArrayList<ItemsModel> itemsModels, RecyclerViewClickListener recyclerViewClickListener){
        this.itemsModels=itemsModels;
        this.recyclerViewClickListener=recyclerViewClickListener;
    };

    @NonNull
    @Override
    public ItemsAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsAdapter.ItemViewHolder holder, int position) {
        ItemsModel itemsModel=itemsModels.get(position);
        holder.textName.setText(itemsModel.getName());
        holder.textDescription.setText(itemsModel.getDescription());

        Uri image = itemsModel.getImage();
        if (image != null) {
            holder.imageView.setImageURI(image);
        }

    }

    @Override
    public int getItemCount() {
        return itemsModels.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imageView;
        TextView textName, textDescription;

        public ItemViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.profile_image);
            textName = itemView.findViewById(R.id.txt1);
            textDescription = itemView.findViewById(R.id.txt2);
            itemView.setOnClickListener(this::itemViewClick);


        }

        public void itemViewClick(View view) {
            recyclerViewClickListener.onItemClick(view, getAdapterPosition());
        }
    }

}
