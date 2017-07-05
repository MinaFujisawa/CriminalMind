package com.derrick.park.criminalmind;

import android.content.Context;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by park on 2017-06-01.
 */

public class CrimeLab {

    private static CrimeLab sCrimeLab; //why static?
    private List<Crime> mCrimes;
    private LinkedHashMap<UUID, Crime> mCrimes2;


    //if sCrimeLab hasn't made, make new one and return it
    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    // constructor : create arrayList and create crime instance and set title and solved then add it (as a dummy)
    public CrimeLab(Context context) {
        mCrimes = new ArrayList<>();
    }

    public void addCrime(Crime c){
        mCrimes.add(c);
    }

    public void deleteCrime(Crime c){
        mCrimes.remove(c);
    }


    //getter for mCrimes
    public List<Crime> getmCrimes() {
        return mCrimes;
    }

    public Crime getCirme(UUID id){
        return mCrimes2.get(id);
    }

    //getter for getCrime using UUID for getting particular crime, if there aren't, return null
    public Crime getCrime(UUID id) {
        for (Crime crime : mCrimes) {
            if (crime.getId().equals(id)) {
                return crime;
            }
        }
        return null;
    }

}












