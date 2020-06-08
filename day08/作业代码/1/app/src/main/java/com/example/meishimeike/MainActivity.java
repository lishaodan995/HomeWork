package com.example.meishimeike;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 跳过
     */
    private Button mButton;
    private ImageView mImage;
    /**
     * 5
     */
    private TextView mDaojishi;
    private int a=5;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1&&a>0){
                mDaojishi.setText(a+"");
                handler.sendEmptyMessageDelayed(1,1000);
                a--;
            }else{
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(this);
        mImage = (ImageView) findViewById(R.id.image);
        mDaojishi = (TextView) findViewById(R.id.daojishi);
        handler.sendEmptyMessageDelayed(1,1000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.button:
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
                break;
        }
    }
}

