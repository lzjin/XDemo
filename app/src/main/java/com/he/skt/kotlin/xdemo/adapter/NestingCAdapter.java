package com.he.skt.kotlin.xdemo.adapter;

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
public class NestingCAdapter extends BaseQuickAdapter<NestingBean, BaseViewHolder> {

    public NestingCAdapter(@Nullable List<NestingBean> data) {
        super(R.layout.item_nesting_c_recy_item, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, NestingBean item) {
//        RoundedImageView imgGoods = helper.getView(R.id.iv_cover);
//        Glide.with(mContext).load(item.getImgUrl()).into(imgGoods);

        helper.setImageResource(R.id.iv_cover,R.mipmap.icon_1_3);
        helper.setText(R.id.tv_name,item.getName());
        //单独事件
        helper.addOnClickListener(R.id.iv_cover);
    }
}

