package com.derrick.park.criminalmind;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

/**
 * Created by MinaFujisawa on 2017/06/13.
 */

public class DatePickerFragment extends DialogFragment {
    private Date mItemsDate;
    public final static String CURRENT_DATE = "criminalMind_current_date";
    public final static String SET_DATE = "criminalMind_set_date";
    private DatePicker mDatePicker;

    public static DatePickerFragment newInstance(Fragment target, int requestCode, Date date) {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setTargetFragment(target,requestCode);
        Bundle args = new Bundle();
        args.putSerializable(CURRENT_DATE, date);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //get current date
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mItemsDate = (Date) bundle.getSerializable(CURRENT_DATE);
        }

        // Use the item's date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        c.setTime(mItemsDate);
//        final int year = c.get(Calendar.YEAR);
        final int year = 2020;
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);


        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_date, null);
        mDatePicker = (DatePicker) view.findViewById(R.id.date_picker_view);
        mDatePicker.updateDate(year, month, day);

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle("Title")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        int selectedDay = mDatePicker.getDayOfMonth();
                        int selectedMonth = mDatePicker.getMonth() + 1;
                        int selectedYear = mDatePicker.getYear();
                        Date date = new GregorianCalendar(selectedYear, selectedMonth, selectedDay).getTime();
                        intent.putExtra(SET_DATE, date);
                        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
    }
}
