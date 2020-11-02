package com.he.skt.kotlin.xdemo.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.he.skt.kotlin.xdemo.R;
import com.he.skt.kotlin.xdemo.bean.SingleBean;
import com.he.skt.kotlin.xdemo.utils.DimenUtil;

import java.util.List;

/**
 * description ： TODO:类的作用
 * author : asus
 * date : 2020/10/30
 */
public class OverlapAdapter extends BaseQuickAdapter<SingleBean, BaseViewHolder> {

    public OverlapAdapter(@Nullable List<SingleBean> data) {
        super(R.layout.item_overlap_list, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, SingleBean item) {
        ImageView imgGoods = helper.getView(R.id.iv_cover);
        Glide.with(mContext).load(item.getImgUrl()).into(imgGoods);
        helper.addOnClickListener(R.id.iv_cover);

        // 左堆叠 1
        if(helper.getLayoutPosition()==0){
            setMargins(helper.getView(R.id.ll_layout),0,0,0,0);
        }else {
            setMargins(helper.getView(R.id.ll_layout),DimenUtil.dipTopx(mContext,-10),0,0,0);
        }
        //右堆叠 2
       // setMargins(helper.getView(R.id.ll_layout),0,0,DimenUtil.dipTopx(mContext,-10),0);
    }

    /**
     *  设置间距
     * @param v view
     * @param l 左
     * @param t 上
     * @param r 右
     * @param b 下
     */
    public void setMargins (View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }
}
