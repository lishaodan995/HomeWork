package com.example.clas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.clas.fragment.AppointCourseFragment;
import com.example.clas.fragment.CourseFragment;
import com.example.clas.fragment.HomeFragment;
import com.example.clas.fragment.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fl)
    FrameLayout mFl;
    //    @BindView(R.id.rb_home)
//    RadioButton mRbHome;
//    @BindView(R.id.rb_course)
//    RadioButton mRbCourse;
//    @BindView(R.id.rb_appoint_course)
//    RadioButton mRbAppointCourse;
//    @BindView(R.id.rb_user)
//    RadioButton mRbUser;
//    @BindView(R.id.rg_main)
//    RadioGroup mRgMain;
    @BindView(R.id.main_navigation_bar)
    BottomNavigationView mMainNavigationBar;
    private FragmentTransaction mTransaction;
    private HomeFragment mHomeFragment;
    private CourseFragment mCourseFragment;
    private AppointCourseFragment mAppointCourseFragment;
    private UserFragment mUserFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mHomeFragment = new HomeFragment();
        mCourseFragment = new CourseFragment();
        mAppointCourseFragment = new AppointCourseFragment();
        mUserFragment = new UserFragment();
        mTransaction = getSupportFragmentManager().beginTransaction();
        mTransaction.replace(R.id.fl, mHomeFragment);
        mTransaction.commit();

        mMainNavigationBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.tab_home:
                        fragment = mHomeFragment;
                        break;
                    case R.id.tab_course:
                        fragment = mCourseFragment;
                        break;
                    case R.id.tab_appoint_course:
                        fragment = mAppointCourseFragment;
                        break;
                    case R.id.tab_user:
                        fragment = mUserFragment;
                        break;
                }
                if (fragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl, fragment).commit();
                }
                return true;
            }
        });
    }
}
