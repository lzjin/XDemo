package com.he.skt.kotlin.xdemo.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.he.skt.kotlin.xdemo.R;
import com.he.skt.kotlin.xdemo.bean.NestingBean;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

/**
 * description ： TODO:类的作用
 * author : asus
 * date : 2020/10/30
 */
public class NestingDAdapter extends BaseQuickAdapter<NestingBean, BaseViewHolder> {

    public NestingDAdapter(@Nullable List<NestingBean> data) {
        super(R.layout.item_nesting_d_recy_item, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, NestingBean item) {
        RoundedImageView imgGoods = helper.getView(R.id.iv_cover);
        Glide.with(mContext).load(item.getImgUrl()).into(imgGoods);

        helper.setText(R.id.tv_name,item.getName());
        helper.getView(R.id.tv_name).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mClickListener!=null){
                    mClickListener.onMyClick(helper.getLayoutPosition());
                }
            }
        });

    }

    /**
     * 按钮监听对外接口
     */
    public static interface OnMyClickListener {
        void onMyClick(int position);
    }
    /**
     * 监听
     */
    private OnMyClickListener mClickListener;
    /**
     * 监听
     */
    public void setMyClickListener(OnMyClickListener listener) {
        mClickListener = listener;
    }

}

