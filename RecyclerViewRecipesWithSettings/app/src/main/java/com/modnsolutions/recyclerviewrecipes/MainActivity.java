package com.modnsolutions.recyclerviewrecipes;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements RecipeListFragment.OnRecipeClickedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            // Inflate RecipeListFragment in MainActivity
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.recipe_fragment, new RecipeListFragment())
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void onRecipeClickedInteraction(Recipe recipe) {
        // If second fragment layout exist, replace fragment with recipe details
        // else start a new activity with recipe details.
        if (findViewById(R.id.recipe_details_fragment) != null) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(RecipeDetailsFragment.BUNDLE_MESSAGE, recipe);

            RecipeDetailsFragment fragment = new RecipeDetailsFragment();
            fragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.recipe_details_fragment, fragment)
                    .addToBackStack(null)
                    .commit();
        } else {
            Intent intent = new Intent(this, RecipeDetailsActivity.class);
            intent.putExtra(RecipeDetailsFragment.BUNDLE_MESSAGE, recipe);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;

            default: break;
        }

        return super.onOptionsItemSelected(item);
    }
}
