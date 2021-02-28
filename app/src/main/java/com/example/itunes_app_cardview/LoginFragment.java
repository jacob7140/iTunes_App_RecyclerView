package com.example.itunes_app_cardview;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;


public class LoginFragment extends Fragment {


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    EditText editTextEmail, editTextPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //to set the title of the fragment
        getActivity().setTitle("Login");


        View view = inflater.inflate(R.layout.fragment_login, container, false);

        // grab the values
        editTextEmail = view.findViewById(R.id.editTextTextEmailAddress);
        editTextPassword = view.findViewById(R.id.editTextTextPassword);

        Log.d("login", "Email = " + editTextEmail + " password =" + editTextPassword);


        view.findViewById(R.id.buttonLoginFragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getActivity(), "Password/Email cannot be empty", Toast.LENGTH_SHORT).show();

                } else {
                    DataServices.login(email, password, new DataServices.AuthResponse() {
                        @Override
                        public void onSuccess(String token) {
                            mListener.loginIsSuccessful(token);




                        }

                        @Override
                        public void onFailure(DataServices.RequestException exception) {
                            Toast.makeText(getActivity(), "Invalid Login", Toast.LENGTH_SHORT);

                        }
                    });
                }


            }
        });
        view.findViewById(R.id.buttonCreateNewAccount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("test", "Login createbutton pushed");
                mListener.gotoRegistration();

            }
        });
        return view;
    }

    LoginListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof LoginListener) {
            mListener = (LoginListener) context;
        } else {
            throw new RuntimeException((context.toString() + "must implement loginlistener"));
        }
    }

    interface LoginListener {
        void loginIsSuccessful(String token);

        void gotoRegistration();
    }
}