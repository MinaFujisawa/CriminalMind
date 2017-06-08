package com.derrick.park.criminalmind;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.List;
import java.util.UUID;

/**
 * Created by park on 2017-06-01.
 */

public class CrimeListFragment extends Fragment {
    // declare mCrimeRecyclerView and mAdater pointed CrimeAdapter (inner class)
    RecyclerView mCrimeRecyclerView;
    CrimeAdapter mAdapter;
    CrimeLab crimeLab = CrimeLab.get(getActivity());
    List<Crime> crimes = crimeLab.getmCrimes();
    private static final String EXTRA_ID = "CrimeListFragment_extra_title";
    private int mCurrentPosition;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
        mCrimeRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }


    //set adapter
    private void updateUI() {
        mAdapter = new CrimeAdapter(crimes, new OnItemClickListener() {
            @Override
            public void onItemClick(Crime item) {
                Intent intent = new Intent(getActivity(), CrimeActivity.class);
                String crimeID = String.valueOf(item.getId());
                intent.putExtra(EXTRA_ID, crimeID);
                startActivity(intent);
            }
        });
        mCrimeRecyclerView.setAdapter(mAdapter);
    }

    //各リストの内容
    private class CrimeHolder extends RecyclerView.ViewHolder {
        TextView mTitleTextView;
        TextView mDateTextView;
        ImageView mSolvedImageView;

        public CrimeHolder(View itemView) {
            super(itemView);
            //set id using itemView
            mTitleTextView = (TextView) itemView.findViewById(R.id.crime_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.crime_date);
            mSolvedImageView = (ImageView) itemView.findViewById(R.id.img_solved);
        }

        private void bind(final Crime crime, final OnItemClickListener listener, int position) {
            //set texts
            mTitleTextView.setText(crime.getTitle());
            mDateTextView.setText(String.valueOf(DateFormat.getDateInstance().format(crime.getDate())));
            if(crimes.get(position).isSolved()){
                mSolvedImageView.setVisibility(View.VISIBLE);
            } else {
                mSolvedImageView.setVisibility(View.INVISIBLE);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(crime);
                }
            });
        }
    }

    // adapter = help communicate between recycler actual data and viewholder
    //Adapter - 実データ（text, img...) と ViewHolderの受け渡し
    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {

        private List<Crime> mCrimes;
        private final OnItemClickListener listener;

        public CrimeAdapter(List<Crime> crimes, OnItemClickListener listener) {
            this.mCrimes = crimes;
            this.listener = listener;
        }

        @Override
        // layout managerが新しいitem(view holder)を表示させるときに呼ばれる
        // 各Listのレイアウトをinflateする
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_crime, parent, false);
            return new CrimeHolder(view);

        }


        //特定のPosition(row)にデータをsetする
        //Display the data at the specified position
        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Crime crime = mCrimes.get(position);
            holder.bind(crime, listener, position);
        }

        //データコレクションのsizeをreturn
        @Override
        public int getItemCount() {
            return mCrimes.size();
        }

        //itemによって処理を変えるのに必要
        @Override
        public int getItemViewType(int position) {
            return mCrimes.get(position).getmRequiresPolice();
        }

    }


}
