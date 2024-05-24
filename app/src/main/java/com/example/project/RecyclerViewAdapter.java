package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<RecyclerViewItem> items;
    private LayoutInflater layoutInflater;
    private OnClickListener onClickListener;

    RecyclerViewAdapter(Context context, List<RecyclerViewItem> items, OnClickListener onClickListener) {
        this.layoutInflater = LayoutInflater.from(context);
        this.items = items;
        this.onClickListener = onClickListener;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.recycler_view, parent, false));
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RecyclerViewItem item = items.get(position);

        // Set the text of each TextView to display all the data
        holder.nameTextView.setText("Country: " + item.getName());
        holder.locationTextView.setText("Continent: " + item.getLocation());
        holder.sizeTextView.setText("Population: " + item.getSize()+ " Million");
        Picasso.get().load(item.getImageView()).into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameTextView;
        TextView locationTextView;
        TextView sizeTextView;
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            nameTextView = itemView.findViewById(R.id.name_text_view);
            locationTextView = itemView.findViewById(R.id.location_text_view);
            sizeTextView = itemView.findViewById(R.id.size_text_view);
            imageView = itemView.findViewById(R.id.image_view);
        }

        @Override
        public void onClick(View view) {
            onClickListener.onClick(items.get(getAdapterPosition()));
        }
    }

    public interface OnClickListener {
        void onClick(RecyclerViewItem item);
    }
}