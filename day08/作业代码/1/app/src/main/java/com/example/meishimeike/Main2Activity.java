package com.example.meishimeike;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    private ViewPager mIewPager;
    private TabLayout mTabLayout;
    private FragmentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
    }

    private void initView() {
        mIewPager = findViewById(R.id.iewPager);
        mTabLayout = findViewById(R.id.tabLayout);
        ArrayList<Fragment> list = new ArrayList<>();
        list.add(new Fragment1());
        list.add(new Fragment2());
        list.add(new Fragment3());
        list.add(new Fragment4());
        adapter = new FragmentAdapter(getSupportFragmentManager(), list);
        mIewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mIewPager);
        mTabLayout.getTabAt(0).setText("首页");
        mTabLayout.getTabAt(1).setText("收藏");
        mTabLayout.getTabAt(2).setText("收藏1");
        mTabLayout.getTabAt(3).setText("收藏2");
    }
}
