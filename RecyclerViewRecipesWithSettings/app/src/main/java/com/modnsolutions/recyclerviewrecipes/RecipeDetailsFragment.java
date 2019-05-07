package com.modnsolutions.recyclerviewrecipes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_recipe_details, container, false);

        // Get bundle and populate the view with recipe object
        Bundle bundle = getArguments();
        if (bundle != null) {
            Recipe recipe = bundle.getParcelable(BUNDLE_MESSAGE);

            TextView recipeName = root.findViewById(R.id.recipe_details_name);
            ImageView imageView = root.findViewById(R.id.recipe_imageview);
            TextView textView = root.findViewById(R.id.recipe_details_textview);

            // Get image resource from drawable asset
            String recipeImage = recipe.getImage();
            int imageResource = getResources().getIdentifier(recipeImage, "drawable",
                    getContext().getPackageName());

            // Set recipe details in view
            recipeName.setText(recipe.getName());
            imageView.setImageDrawable(getResources().getDrawable(imageResource));
            imageView.setContentDescription(recipe.getDescription());
            textView.setText(recipe.getDetails());
        }

        return root;
    }
}
