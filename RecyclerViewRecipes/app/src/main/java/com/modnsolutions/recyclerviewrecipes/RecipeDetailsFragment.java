package com.modnsolutions.recyclerviewrecipes;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeDetailsFragment extends Fragment {
    public static final String BUNDLE_MESSAGE = RecipeListFragment.class.getCanonicalName() +
            ".MESSAGE";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_recipe_details, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            Recipe recipe = bundle.getParcelable(BUNDLE_MESSAGE);

            ImageView imageView = root.findViewById(R.id.recipe_imageview);
            TextView textView = root.findViewById(R.id.recipe_details_textview);

            // Get image resource from drawable asset
            String recipeImage = recipe.getImage();
            int imageResource = getResources().getIdentifier(recipeImage, "drawable",
                    getContext().getPackageName());

            // Set recipe details in view
            imageView.setImageDrawable(getResources().getDrawable(imageResource));
            imageView.setContentDescription(recipe.getDescription());
            textView.setText(recipe.getDetails());
        }

        return root;
    }
}
