package com.derrick.park.criminalmind;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by park on 2017-06-01.
 */

public class CrimeListFragment extends Fragment {
    // declare mCrimeRecyclerView and mAdater pointed CrimeAdapter (inner class)
    RecyclerView mCrimeRecyclerView;
    CrimeAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //create view
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
        //set Id and setLayoutManager(LinearKayout) for mCrimeRecyclerView
        mCrimeRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getmCrimes();
        mAdapter = new CrimeAdapter(crimes);
        mCrimeRecyclerView.setAdapter(mAdapter);
    }

    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        TextView mTitleTextView;
        TextView mDateTextView;
        Crime mCrime = new Crime();
        public CrimeHolder(View itemView) {
            super(itemView);
            //set id using itemView
            mTitleTextView = (TextView) itemView.findViewById(R.id.crime_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.crime_date);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getLayoutPosition();
            Toast.makeText(getActivity(), "test", Toast.LENGTH_SHORT).show();
        }

        private void bind(Crime crime){
            //set texts
            mTitleTextView.setText(crime.getTitle());
            mDateTextView.setText(String.valueOf(crime.getDate()));
        }
    }

    // adapter = help communicate between recycler actual data and viewholder
    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {
        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }

        @Override
        // set the layout you want to display
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_crime, parent, false);
            return new CrimeHolder(view);
        }

        @Override
        //Display the data at the specified position
        //position = row num like index
        public void onBindViewHolder(CrimeHolder holder, int position) {
            // make crime using .get(position)
            Crime crime = mCrimes.get(position);
            holder.bind(crime);
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }

}
