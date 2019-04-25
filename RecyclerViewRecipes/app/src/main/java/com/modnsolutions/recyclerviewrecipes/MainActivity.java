package com.modnsolutions.recyclerviewrecipes;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements RecipeListFragment.OnRecipeClickedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inflate RecipeListFragment in MainActivity
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.recipe_fragment, new RecipeListFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onRecipeClickedInteraction(Recipe recipe) {
        // Do something
        if (findViewById(R.id.recipe_details_fragment) != null) {
            Bundle bundle = new Bundle();
        } else {
            Intent intent = new Intent(this, RecipeDetailsActivity.class);
            intent.putExtra(RecipeDetailsFragment.BUNDLE_MESSAGE, recipe);
            startActivity(intent);
        }
    }
}
