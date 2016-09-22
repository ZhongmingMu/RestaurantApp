package com.laioffer.laiofferproject;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class RestaurantListActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);
        //Log.e("Life cycle test", "We are at onCreate()");
        // Show different fragments based on screen size.
        if (savedInstanceState != null) {
            return;
        }
        if (findViewById(R.id.fragment_container) != null) {
            Fragment fragment = isTablet() ?
                    new RestaurantGridFragment() : new RestaurantListFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment).commit();
        }

        Button button = (Button) findViewById(R.id.show_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListView restuarantList = (ListView) findViewById(R.id.restaurant_list);
                Intent intent = new Intent(v.getContext(), RestaurantMapActivity.class);
                ArrayList<LatLng> list = new ArrayList<>();
                for (int i = 0; i < 20; i++) {
                    Restaurant r = (Restaurant) restuarantList.getItemAtPosition(i);
                    list.add(new LatLng(r.getLat(), r.getLng()));

                }
                intent.putParcelableArrayListExtra(RestaurantMapActivity.EXTRA_LATLNG, list);
                startActivity(intent);
            }
        });
    }



    private boolean isTablet() {
        return (getApplicationContext().getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) >=
                Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    /**
     * A dummy function to get fake restaurant names.
     *
     * @return an array of fake restaurant names.
     */

    /*
    private String[] getRestaurantNames() {
        String[] names= {
                "Restaurant1", "Restaurant2", "Restaurant3",
                "Restaurant4", "Restaurant5", "Restaurant6",
                "Restaurant7", "Restaurant8", "Restaurant9",
                "Restaurant11", "Restaurant11", "Restaurant12"};
        return names;
    }
    */

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("Life cycle test", "We are at onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("Life cycle test", "We are at onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("Life cycle test", "We are at onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("Life cycle test", "We are at onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("Life cycle test", "We are at onDestroy()");
    }
}
