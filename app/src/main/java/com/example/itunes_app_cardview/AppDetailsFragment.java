package com.example.itunes_app_cardview;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AppDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AppDetailsFragment extends Fragment {

    private static final String DATA = "details";
    private String mData;

    public AppDetailsFragment() {
        // Required empty public constructor
    }


    public static AppDetailsFragment newInstance(String details) {
        AppDetailsFragment fragment = new AppDetailsFragment();
        Bundle args = new Bundle();
        args.putString(DATA, details);
        Log.d("data","Account new Instance");
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            mData = getArguments().getString(DATA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_app_details, container, false);
        //DataServices.App app = new DataServices.App(mData);

        Log.d("data", "onCreateView: " + mData);

        TextView appName = view.findViewById(R.id.detailsAppName);
        //appName.setText(app.name);

        return view;
    }

    AppDetailsListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (AppDetailsFragment.AppDetailsListener) context;
    }

    interface AppDetailsListener {

    }
}