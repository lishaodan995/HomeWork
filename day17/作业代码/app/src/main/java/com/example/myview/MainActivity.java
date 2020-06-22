package com.example.myview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private MyButton mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mButton = (MyButton) findViewById(R.id.button);
        mButton.setTranslationX( SharedPrefHelper.getInstance().getTranslationX());
        mButton.setTranslationY( SharedPrefHelper.getInstance().getTranslationY());
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPrefHelper.getInstance().setTranslationX(mButton.getLastX());
        SharedPrefHelper.getInstance().setTranslationY(mButton.getLastY());
    }
}
