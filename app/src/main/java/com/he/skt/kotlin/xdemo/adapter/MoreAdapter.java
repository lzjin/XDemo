package com.he.skt.kotlin.xdemo.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.he.skt.kotlin.xdemo.R;
import com.he.skt.kotlin.xdemo.bean.MoreBean;
import com.he.skt.kotlin.xdemo.bean.SingleBean;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

/**
 * description ： 多布局
 * author : asus
 * date : 2020/10/30
 */
public class MoreAdapter extends BaseMultiItemQuickAdapter<MoreBean, BaseViewHolder> {
    public final static int TYPE_ONE=0;
    public final static int TYPE_TWO=1;
    public MoreAdapter(@Nullable List<MoreBean> data) {
        super(data);
        addItemType(TYPE_ONE, R.layout.item_more_layout_one);
        addItemType(TYPE_TWO, R.layout.item_more_layout_two);
    }



    @Override
    protected void convert(@NonNull BaseViewHolder helper, MoreBean item) {
        switch (helper.getItemViewType()) {
            case TYPE_ONE:
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
                break;
            case TYPE_TWO:
                helper.setText(R.id.tx_goods_name, item.getName() + helper.getLayoutPosition());
                if (helper.getLayoutPosition() % 2 == 0) {
                    helper.setText(R.id.tx_goods_amount, item.getAutPrice());
                } else {
                    helper.setText(R.id.tx_goods_amount, item.getOrgPrice());
                }

                RoundedImageView imgGoods2 = helper.getView(R.id.img_goods);
                Glide.with(mContext).load(item.getImgUrl()).into(imgGoods2);

                //单独事件
                helper.addOnClickListener(R.id.img_goods);
                break;
            default:
                break;
        }

    }
}
