package com.example.itunes_app_cardview;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoriesFragment extends Fragment {
    ListView listView;
    TextView textViewWelcome;

    private static final String ARG_PARAM_TOKEN = "ARG_PARAM_TOKEN";
    private static final String DATA = "category";
    private String mToken;
    private String mData;

    private DataServices.Account mAccount;
    public void setAccountDetails(DataServices.Account accountDetails){
        this.mAccount=accountDetails;
    }
    public CategoriesFragment() {
        // Required empty public constructor
    }

    public static CategoriesFragment newInstance(String token) {
        CategoriesFragment fragment = new CategoriesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_TOKEN, token);
        Log.d("data","Account new Instance");
        fragment.setArguments(args);
        return fragment;

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            mToken = getArguments().getString(ARG_PARAM_TOKEN);
        }

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("App Categories");


        View view = inflater.inflate(R.layout.fragment_catagories, container, false);
        textViewWelcome = view.findViewById(R.id.textViewWelcome);


        Log.d("data", "onCreateView: " + mToken);
        DataServices.getAccount(mToken, new DataServices.AccountResponse() {
            @Override
            public void onSuccess(DataServices.Account account) {
                Log.d("data", "onSuccess: Account Created");
                textViewWelcome.setText("Welcome " + account.getName());

            }

            @Override
            public void onFailure(DataServices.RequestException exception) {
                Log.d("data", "onFailure: Failed to create account");

            }
        });

        listView = view.findViewById(R.id.listView);


        DataServices.getAppCategories(mToken, new DataServices.DataResponse<String>() {
            @Override
            public void onSuccess(ArrayList<String> data) {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, data);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Log.d("data", "onItemClick: " + position);
                        mListener.goToAppList(mToken, data.get(position));
                    }
                });

            }

            @Override
            public void onFailure(DataServices.RequestException exception) {

            }
        });


        view.findViewById(R.id.categoriesLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.logout();
            }
        });



        return view;
    }

    CategoriesListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (CategoriesListener) context;
    }

    interface CategoriesListener {
        void logout();
        void goToAppList(String token, String category);
    }
}
