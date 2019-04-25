package com.modnsolutions.recyclerviewrecipes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RecipeDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        if (savedInstanceState == null) {
            // Get intent
            Intent intent = getIntent();
            Recipe recipe = intent.getParcelableExtra(RecipeDetailsFragment.BUNDLE_MESSAGE);

            // Put recipe in bundle
            Bundle bundle = new Bundle();
            bundle.putParcelable(RecipeDetailsFragment.BUNDLE_MESSAGE, recipe);

            // Pass bundle over to fragment
            RecipeDetailsFragment fragment = new RecipeDetailsFragment();
            fragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.recipe_details_fragment, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}
