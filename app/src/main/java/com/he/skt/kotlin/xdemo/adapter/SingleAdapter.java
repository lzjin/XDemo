package com.he.skt.kotlin.xdemo.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.he.skt.kotlin.xdemo.R;
import com.he.skt.kotlin.xdemo.bean.SingleBean;

import java.util.List;

/**
 * description ： TODO:类的作用
 * author : asus
 * date : 2020/10/30
 */
public class SingleAdapter extends BaseQuickAdapter<SingleBean, BaseViewHolder> {

    public SingleAdapter(@Nullable List<SingleBean> data) {
        super(R.layout.item_single_list, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, SingleBean item) {
        ImageView imgGoods = helper.getView(R.id.img_goods);
        Glide.with(mContext).load(item.getImgUrl()).into(imgGoods);
        helper.setText(R.id.tx_goods_name, item.getName() + helper.getLayoutPosition());
        if (helper.getLayoutPosition() % 2 == 0) {
            helper.getView(R.id.tx_fin_money).setVisibility(View.GONE);
            helper.getView(R.id.tx_goods_amount).setVisibility(View.VISIBLE);
            helper.setText(R.id.tx_goods_amount, item.getAutPrice());
        } else {
            helper.getView(R.id.tx_goods_amount).setVisibility(View.GONE);
            helper.getView(R.id.tx_fin_money).setVisibility(View.VISIBLE);
            helper.setText(R.id.tx_fin_money, item.getOrgPrice());
        }
           //单独事件
          helper.addOnClickListener(R.id.img_goods);
    }
}
