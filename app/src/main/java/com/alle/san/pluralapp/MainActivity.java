package com.alle.san.pluralapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alle.san.pluralapp.adapters.TabPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import static com.alle.san.pluralapp.Utils.Globals.LISTING_FRAGMENT_TAG;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "LeaderListActivity";

    Button submit;
    Toolbar myToolbar;
    int container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myToolbar = findViewById(R.id.my_tool_bar);
        container = R.id.fragment_container;
        submit = findViewById(R.id.submit);

        setSupportActionBar(myToolbar);

        initListingFragment();
        submitButton();





    }

    private void submitButton() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Submitted",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initListingFragment() {
        ListingFragment listingFragment= new ListingFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(container, listingFragment, LISTING_FRAGMENT_TAG);
        transaction.addToBackStack(LISTING_FRAGMENT_TAG);
        transaction.commit();
    }


}