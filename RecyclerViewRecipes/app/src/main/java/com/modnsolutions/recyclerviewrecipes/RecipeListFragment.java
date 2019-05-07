package com.modnsolutions.recyclerviewrecipes;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RecipeListFragment.OnRecipeClickedListener} interface
 * to handle interaction events.
 */
public class RecipeListFragment extends Fragment {

    private OnRecipeClickedListener mListener;
    private final LinkedList<Recipe> mRecipeList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private RecipeAdapter mAdapter;

    public RecipeListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_recipe_list, container, false);

        // Generate recipe objects
        String[] recipeNames = getResources().getStringArray(R.array.recipe_names);
        String[] recipeDescs = getResources().getStringArray(R.array.recipe_descriptions);
        String[] recipeDetails = getResources().getStringArray(R.array.recipe_details);
        String[] recipeImages = getResources().getStringArray(R.array.recipe_images);

        for (int i = 0; i < recipeNames.length; i++) {
            Recipe recipe = new Recipe(recipeNames[i], recipeDescs[i], recipeDetails[i],
                    recipeImages[i]);
            Log.d("RecipeListFragment", "Recipe added is: " + recipe.toString());
            mRecipeList.add(recipe);
        }

        mRecyclerView = root.findViewById(R.id.recyclerview);
        mAdapter = new RecipeAdapter(getContext(), mRecipeList, mListener);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRecipeClickedListener) {
            mListener = (OnRecipeClickedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnRecipeClickedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnRecipeClickedListener {
        void onRecipeClickedInteraction(Recipe recipe);
    }
}
