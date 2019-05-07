package com.modnsolutions.recyclerviewrecipes;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
    private final LinkedList<Recipe> mRecipeList;
    private LayoutInflater mInflater;
    private final RecipeListFragment.OnRecipeClickedListener mListener;

    RecipeAdapter(Context context, LinkedList<Recipe> recipeList,
                  RecipeListFragment.OnRecipeClickedListener listener) {
        mInflater = LayoutInflater.from(context);
        mRecipeList = recipeList;
        mListener = listener;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View mItemView = mInflater.inflate(R.layout.recipe_list_item, viewGroup, false);
        return new RecipeViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder recipeViewHolder, int i) {
        Recipe mCurrentRecipe = mRecipeList.get(i);
        recipeViewHolder.recipeNameTextView.setText(mCurrentRecipe.getName());
        recipeViewHolder.recipeDescriptionTextView.setText(mCurrentRecipe.getDescription());
    }

    @Override
    public int getItemCount() {
        return mRecipeList.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView recipeNameTextView;
        final TextView recipeDescriptionTextView;
        final RecipeAdapter mAdapter;

        RecipeViewHolder(@NonNull View itemView, RecipeAdapter adapter) {
            super(itemView);
            recipeNameTextView = itemView.findViewById(R.id.recipe_name_textview);
            recipeDescriptionTextView = itemView.findViewById(R.id.recipe_description_textview);
            mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // Get the recipe and pass to the fragment listener to be implemented by hosting
            // activity.
            int position = getAdapterPosition();
            Recipe recipe = mRecipeList.get(position);
            mListener.onRecipeClickedInteraction(recipe);
        }
    }
}
