package com.example.zuoye1;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements View.OnClickListener {


    private ViewPager mViewpageMain;
    private TabLayout mTabRecommend;
    private NavigationView mNavigation;
    private DrawerLayout mDraw;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> tabs;
    private ImageView mBarImgMain;
    private ArrayList<Integer> images;
    private Toolbar mTool;
    private TextView main_bar_text;


    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        mTool = (Toolbar) findViewById(R.id.tool);

        mViewpageMain = (ViewPager) findViewById(R.id.main_viewpage);
        mTabRecommend = (TabLayout) findViewById(R.id.main_tab);
        mNavigation = (NavigationView) findViewById(R.id.main_navigation);
        mBarImgMain = (ImageView) findViewById(R.id.main_bar_img);
        mBarImgMain.setOnClickListener(this);
        mDraw = (DrawerLayout) findViewById(R.id.draw);

        mDraw.setOnClickListener(this);
        mTabRecommend.setSelectedTabIndicator(0);
        initFragment();
        initTabs();
        initImages();
        setSupportActionBar(mTool);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDraw, mTool, R.string.app_name, R.string.app_name);
        mDraw.addDrawerListener(drawerToggle);
        MainPageAdapter adapter = new MainPageAdapter(getSupportFragmentManager(), fragments, tabs);
        mViewpageMain.setAdapter(adapter);
        mTabRecommend.setupWithViewPager(mViewpageMain);
        for (int i = 0; i < tabs.size(); i++) {
            TabLayout.Tab tab = mTabRecommend.getTabAt(i);

            tab.setCustomView(getTabView(i));
        }

    }

    private void initImages() {
        images = new ArrayList<>();
        images.add(R.drawable.messageselect);
        images.add(R.drawable.peopleselect);
        images.add(R.drawable.dongtaiselect);
    }

    private void initTabs() {
        tabs = new ArrayList<>();
        tabs.add("消息");
        tabs.add("联系人");
        tabs.add("动态");

        // mTool.setTitle(tabs.getClass());

    }


    private View getTabView(int position) {
        View inflate = LayoutInflater.from(this).inflate(R.layout.tab, null);
        TextView tv = inflate.findViewById(R.id.tv);
        ImageView iv = inflate.findViewById(R.id.iv);
        tv.setText(tabs.get(position));
        iv.setImageResource(images.get(position));
        return inflate;
    }


    private void initFragment() {

        fragments = new ArrayList<>();
        fragments.add(new FragmentA());
        fragments.add(new FragmentB());
        fragments.add(new FragmentC());





    }

    @Override
    protected BasePre initPre() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.draw:


                break;
            case R.id.main_bar_img:
                mDraw.openDrawer(mNavigation);
                break;
            default:
                break;
        }
    }

    @Override
    public void showToast(String msg) {

    }

}
