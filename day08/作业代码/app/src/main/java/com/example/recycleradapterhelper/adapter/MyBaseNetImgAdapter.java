package com.example.recycleradapterhelper.adapter;
import android.content.Context;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.bumptech.glide.Glide;
import com.example.recycleradapterhelper.R;
import com.example.recycleradapterhelper.entity.ArticalInfo1;
import java.util.List;
public class MyBaseNetImgAdapter extends BaseQuickAdapter<ArticalInfo1.DataBean.DatasBean, BaseViewHolder> {
    private Context mContext;

    public MyBaseNetImgAdapter(Context context, @Nullable List<ArticalInfo1.DataBean.DatasBean> data) {
        super(R.layout.list_item_img, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticalInfo1.DataBean.DatasBean item) {
        helper.setText(R.id.item_tv, item.getDesc());
        String envelopePic = item.getEnvelopePic();
        helper.addOnClickListener(R.id.item_iv);
        helper.addOnClickListener(R.id.item_tv);

        helper.addOnLongClickListener(R.id.item_tv);

        helper.addOnLongClickListener(R.id.item_iv);

        Glide.with(mContext).load(envelopePic).into((ImageView) helper.getView(R.id.item_iv));




    }
}
