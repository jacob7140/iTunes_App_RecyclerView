package com.example.itunes_app_cardview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements LoginFragment.LoginListener, RegisterFragment.SignupListener, CategoriesFragment.CategoriesListener, AppList.AppListListener, AppDetailsFragment.AppDetailsListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.rootView, new LoginFragment())
                .commit();
    }

    String mAccount;

    @Override
    public void loginIsSuccessful(String token) {
        this.mAccount= token;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, CategoriesFragment.newInstance(this.mAccount)).commit();
    }

    @Override
    public void gotoLogin() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new LoginFragment())
                .commit();
    }

    @Override
    public void gotoRegistration() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new RegisterFragment())
                .commit();
    }

    @Override
    public void goToAppList(String token, String category) {
        this.mAccount= token;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, AppList.newInstance(this.mAccount, category)).commit();
    }

    @Override
    public void logout() {
        this.mAccount = null;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new LoginFragment())
                .commit();
    }



    @Override
    public void goToDetails() {
        getSupportFragmentManager().beginTransaction().replace(R.id.rootView, AppDetailsFragment.newInstance(this.mAccount)).commit();

    }



}