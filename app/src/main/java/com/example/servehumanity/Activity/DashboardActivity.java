package com.example.servehumanity.Activity;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.servehumanity.R;
import com.example.servehumanity.adapter.FragmentAdapter;
import com.example.servehumanity.fragments.SystemToolsFragment;
import com.example.servehumanity.fragments.HomeFragment;
import com.example.servehumanity.fragments.ProfileFragment;
import com.google.android.material.tabs.TabLayout;

public class DashboardActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#ffffff"));

        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), FragmentAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        fragmentAdapter.addFragment(new ProfileFragment(), "Profile");
        fragmentAdapter.addFragment(new HomeFragment(), "Home");
        fragmentAdapter.addFragment(new SystemToolsFragment(), "System Tools");

        viewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
        setUpIcons();
    }

    private void setUpIcons() {
        tabLayout.getTabAt(0).setIcon(R.drawable.person_icon);
        tabLayout.getTabAt(1).setIcon(R.drawable.home);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_wrench);
    }
}