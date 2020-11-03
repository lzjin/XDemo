package com.he.skt.kotlin.xdemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.guanaj.easyswipemenulibrary.EasySwipeMenuLayout;
import com.guanaj.easyswipemenulibrary.State;
import com.he.skt.kotlin.xdemo.R;
import com.he.skt.kotlin.xdemo.bean.SingleBean;

import java.util.List;

/**
 * description ： 可拖动
 * author : asus
 * date : 2020/11/2
 */
public class DraggableAdapter extends BaseQuickAdapter<SingleBean, BaseViewHolder> {

    public DraggableAdapter(@Nullable List<SingleBean> data) {
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
        helper.addOnClickListener(R.id.tvLeftShare);
       // helper.addOnClickListener(R.id.tvRightDel);
       // helper.addOnClickListener(R.id.tvRightMenu);

        helper.getView(R.id.tvRightDel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mClickListener!=null){
                    mClickListener.onMyClick(helper.getLayoutPosition(), helper.getView(R.id.swipeMenuLayout));
                }
            }
        });
        helper.getView(R.id.tvRightMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mClickListener!=null){
                    mClickListener.onMyClick(helper.getLayoutPosition(), helper.getView(R.id.swipeMenuLayout));
                }
            }
        });

    }

    /**
     * 按钮监听对外接口
     */
    public static interface OnMyClickListener {
        void onMyClick(int position, EasySwipeMenuLayout  swipeMenuLayout);
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
