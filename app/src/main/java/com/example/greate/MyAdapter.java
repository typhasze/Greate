package com.example.greate;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends  RecyclerView.Adapter<RecipeViewHolder>{

    private Context mContext;
    private List<RecipeData> myRecipeList;

    public MyAdapter(Context mContext, List<RecipeData> myRecipeList) {
        this.mContext = mContext;
        this.myRecipeList = myRecipeList;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_row_item, parent, false);

        return new RecipeViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecipeViewHolder holder, int position) {

        // holder.imageView.setImageResource(myRecipeList.get(position).getItemImage());
        holder.mTitle.setText(myRecipeList.get(position).getItemName());
        holder.mDescription.setText(myRecipeList.get(position).getItemDescription());
        holder.mCalorie.setText(myRecipeList.get(position).getCalorieCount());

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, RecipeDetail.class);
                intent.putExtra("Image", myRecipeList.get(holder.getAdapterPosition()).getItemImage());
                intent.putExtra("Description", myRecipeList.get(holder.getAdapterPosition()).getItemDescription());
                intent.putExtra("Title", myRecipeList.get(holder.getAdapterPosition()).getItemName());
                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return myRecipeList.size();
    }
}

class RecipeViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView mTitle, mDescription, mCalorie;
    CardView mCardView;

    public RecipeViewHolder(View itemView) {

        super(itemView);

        imageView = itemView.findViewById(R.id.ivImage);
        mTitle = itemView.findViewById(R.id.tvTitle);
        mDescription = itemView.findViewById(R.id.tvDescription);
        mCalorie = itemView.findViewById(R.id.tvCalorie);
        mCardView = itemView.findViewById(R.id.myCardView);
    }
}
