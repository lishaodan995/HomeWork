package com.example.recycleradapterhelper.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycleradapterhelper.R;
import com.example.recycleradapterhelper.adapter.MyBaseNetImgAdapter;
import com.example.recycleradapterhelper.adapter.MyBaseNetQuiclAdapyer;
import com.example.recycleradapterhelper.adapter.MyBaseQuiclAdapyer;
import com.example.recycleradapterhelper.api.Constants;
import com.example.recycleradapterhelper.entity.ArticalInfo1;
import com.example.recycleradapterhelper.entity.GongZhongHaoInfo;
import com.example.recycleradapterhelper.utl.GsonUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BaseQuickActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private int currentCount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basequickadapter);

        initNetImgData();
    }

    private void initNetImgData() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(BaseQuickActivity.this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(BaseQuickActivity.this, LinearLayoutManager.VERTICAL));

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(10, TimeUnit.SECONDS).
                readTimeout(10, TimeUnit.SECONDS);
        Request.Builder request = new Request.Builder().url(Constants.ARTICAL_URL);
        Call call = builder.build().newCall(request.build());
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG", "获取数据失败" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonStr = response.body().string();
                ArticalInfo1 articalInfo = GsonUtil.str2Bean(jsonStr, ArticalInfo1.class);
                final ArticalInfo1.DataBean data = articalInfo.getData();
                final List<ArticalInfo1.DataBean.DatasBean> datas = data.getDatas();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        final MyBaseNetImgAdapter adapter = new MyBaseNetImgAdapter(BaseQuickActivity.this, datas);
                        mRecyclerView.setAdapter(adapter);

                        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                            @Override
                            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                Log.e("TAG", "图片" + position + "被点击了");
                                ArticalInfo1.DataBean.DatasBean datasBean = datas.get(position);
                                if (view.getId() == R.id.item_tv) {

                                    String desc = datasBean.getDesc();
                                    Log.e("TAG", "点击的文本字符串是：" + desc);
                                } else {
                                    String envelopePic = datasBean.getEnvelopePic();
                                    Log.e("TAG", "点击的图片的地址是：" + envelopePic);
                                }

                            }
                        });

                        adapter.setOnItemChildLongClickListener(new BaseQuickAdapter.OnItemChildLongClickListener() {
                            @Override
                            public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
                                ArticalInfo1.DataBean.DatasBean datasBean = datas.get(position);
                                if (view.getId() == R.id.item_tv) {
                                    //TODO 如何获取点击的文本字符串
                                    String desc = datasBean.getDesc();
                                    Log.e("TAG", "长按的文本字符串是：" + desc);
                                } else {
                                    //TODO 如何获取点击的图片的地址
                                    String envelopePic = datasBean.getEnvelopePic();
                                    Log.e("TAG", "长按的图片的地址是：" + envelopePic);
                                }
                                return false;
                            }
                        });

                        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);

                        adapter.isFirstOnly(false);


                        View header_view = View.inflate(BaseQuickActivity.this, R.layout.item_header, null);
                        adapter.addHeaderView(header_view);

                        View footer_view = View.inflate(BaseQuickActivity.this, R.layout.item_footer, null);
                        adapter.addFooterView(footer_view);


                    }

                });

            }
        });

    }

    private void initNetData() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(BaseQuickActivity.this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(BaseQuickActivity.this, LinearLayoutManager.VERTICAL));

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(10, TimeUnit.SECONDS).
                readTimeout(10, TimeUnit.SECONDS);
        Request.Builder request = new Request.Builder().url(Constants.GONGZHONGHAO_URL);
        Call call = builder.build().newCall(request.build());
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG", "获取数据失败" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonStr = response.body().string();
                GongZhongHaoInfo gongZhongHaoInfo = GsonUtil.str2Bean(jsonStr, GongZhongHaoInfo.class);
                final List<GongZhongHaoInfo.DataBean> list = gongZhongHaoInfo.getData();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MyBaseNetQuiclAdapyer adapter = new MyBaseNetQuiclAdapyer(list);
                        mRecyclerView.setAdapter(adapter);
                    }
                });

            }
        });


    }

    private void initStringData() {
        List<String> list = new ArrayList<String>();
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(BaseQuickActivity.this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(BaseQuickActivity.this, LinearLayoutManager.VERTICAL));
        for (int i = 0; i < 100; i++) {
            list.add("数据" + i);
        }
        MyBaseQuiclAdapyer adapter = new MyBaseQuiclAdapyer(list);
        mRecyclerView.setAdapter(adapter);
    }
}
