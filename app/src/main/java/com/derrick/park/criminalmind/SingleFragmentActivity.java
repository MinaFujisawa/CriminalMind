package com.derrick.park.criminalmind;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by park on 2017-06-01.
 */

// ordinal process when you connect fragment and activity
public abstract class SingleFragmentActivity extends AppCompatActivity{

    // create abstract method "createFragment"
    protected abstract Fragment createFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.pager);


        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction().add(R.id.pager, fragment).commit();
        }

    }

}
