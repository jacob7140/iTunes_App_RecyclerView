package com.example.itunes_app_cardview;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AppList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AppList extends Fragment {

    private static final String ARG_PARAM_TOKEN = "ARG_PARAM_TOKEN";
    private static final String DATA = "category";
    private String mData;
    private String mToken;

    private DataServices.Account mAccount;
    public void setAccountDetails(DataServices.Account accountDetails){
        this.mAccount=accountDetails;
    }

    public AppList() {
        // Required empty public constructor
    }


    public static AppList newInstance(String token, String category) {
        AppList fragment = new AppList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_TOKEN, token);
        args.putString(DATA, category);
        Log.d("data","Account new Instance");
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            mToken = getArguments().getString(ARG_PARAM_TOKEN);
            mData = getArguments().getString(DATA);
        }

    }

    ListView appByCategoryListView;
    AppListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_app_list, container, false);
        Log.d("data", "onCreateView: " + mToken);

        appByCategoryListView = view.findViewById(R.id.appListView);

        DataServices.getAppsByCategory(mToken, mData, new DataServices.DataResponse<DataServices.App>() {
            @Override
            public void onSuccess(ArrayList<DataServices.App> data) {
                Log.d("data", "onSuccess: Successfully retrieved category" + mData);
                getActivity().setTitle(mData);

                adapter = new AppListAdapter(getContext(), R.layout.user_row_item, data);
                appByCategoryListView.setAdapter(adapter);

            }

            @Override
            public void onFailure(DataServices.RequestException exception) {
                Log.d("data", "onFailure: Failed to get category");

            }
        });

        appByCategoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("data", "onItemClick: " + position);
                adapter.notifyDataSetChanged();
                mListener.goToDetails();
            }
        });

        return view;
    }



    AppListListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (AppList.AppListListener) context;
    }

    interface AppListListener {
        void goToDetails();

    }
}