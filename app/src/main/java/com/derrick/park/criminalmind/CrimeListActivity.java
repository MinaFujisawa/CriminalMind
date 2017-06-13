package com.derrick.park.criminalmind;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by park on 2017-06-01.
 */

public class CrimeListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crime_list_activity);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.list_fragment_container);


        if (fragment == null) {
            fragment = new CrimeListFragment();
            fm.beginTransaction().add(R.id.list_fragment_container, fragment).commit();
        }

    }
}
