package com.lmj.bms.account.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lmj.bms.R;
import com.lmj.bms.util.ActivityUtil;

public class AdminActivity extends AppCompatActivity {
    private BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        getSupportActionBar().hide();
        ActivityUtil.setTransparentStatusBar(this);
        navigationView=findViewById(R.id.activity_admin_nav_view);
        NavController navController = Navigation.findNavController(this, R.id.activity_admin_fragment);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

}