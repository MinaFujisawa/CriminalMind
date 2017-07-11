package com.derrick.park.criminalmind.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.derrick.park.criminalmind.Crime;

import java.util.Date;
import java.util.UUID;

import static com.derrick.park.criminalmind.database.CrimeDbSchema.*;

/**
 * Created by MinaFujisawa on 2017/07/07.
 */

public class CrimeCursorWrapper extends CursorWrapper{
    public CrimeCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public Crime getCrime(){
        String uuidString = getString(getColumnIndex(CrimeTable.Cols.UUID));
        String title = getString(getColumnIndex(CrimeTable.Cols.TITLE));
        long date = getLong(getColumnIndex(CrimeTable.Cols.DATE));
        int isSolved = getInt(getColumnIndex(CrimeTable.Cols.SOLVED));
        String suspect = getString(getColumnIndex(CrimeTable.Cols.SUSPECT));
        String phone = getString(getColumnIndex(CrimeTable.Cols.PHONE));

        Crime crime = new Crime(UUID.fromString(uuidString));
        crime.setTitle(title);
        crime.setDate(new Date(date));
        crime.setSolved(isSolved != 0);
        crime.setmSuspect(suspect);
        crime.setmPhone(phone);
        return crime;
    }
}
