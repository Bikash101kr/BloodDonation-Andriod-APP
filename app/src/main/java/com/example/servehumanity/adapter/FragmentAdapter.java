package com.example.servehumanity.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentAdapter extends FragmentPagerAdapter {

  private List<Fragment> fr = new ArrayList<>();
  private  List<String> fragTitle = new ArrayList<>();
  public FragmentAdapter(@NonNull FragmentManager fm, int behavior) {
    super(fm, behavior);
  }

  @NonNull
  @Override
  public Fragment getItem(int position) {
    return fr.get(position);
  }

  @Override
  public int getCount() {
    return fr.size();
  }
  @Nullable
  @Override
  public CharSequence getPageTitle(int position) {
    return fragTitle.get(position);
  }

  public void addFragment(Fragment fragment, String title) {
    fr.add(fragment);
    fragTitle.add(title);
  }
}