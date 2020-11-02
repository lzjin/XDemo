package com.he.skt.kotlin.xdemo.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.he.skt.kotlin.xdemo.R;
import com.he.skt.kotlin.xdemo.bean.NestingMainBean;
import com.he.skt.kotlin.xdemo.utils.DimenUtil;
import com.he.skt.kotlin.xdemo.widgit.RecyclerViewDivider;
import java.util.List;

/**
 * description ：  嵌套布局
 * author : asus
 * date : 2020/10/30
 */
public class NestingAdapter extends BaseMultiItemQuickAdapter<NestingMainBean, BaseViewHolder> {

    public NestingAdapter(@Nullable List<NestingMainBean> data) {
        super(data);
        addItemType(0, R.layout.item_nesting_a);
        addItemType(1, R.layout.item_nesting_b);
        addItemType(2, R.layout.item_nesting_c);
        addItemType(3, R.layout.item_nesting_d);
        addItemType(4, R.layout.item_nesting_e);
        addItemType(5, R.layout.item_nesting_f);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, NestingMainBean item) {
        // 第三步：设置不同布局下的组件数据
        switch (helper.getItemViewType()) {
            case 0:
                helper.setText(R.id.tv_date, "润六月十九");
                helper.setText(R.id.tv_day, "30");
                helper.addOnClickListener(R.id.tvTest);
                helper.addOnClickListener(R.id.tv_day);
                break;
            case 1:
                helper.setText(R.id.tvSdb1, "每日推荐");
                helper.setText(R.id.tvSdb2, "最佳销量");
                helper.addOnClickListener(R.id.tvSdb1);
                helper.addOnClickListener(R.id.tvSdb2);
                break;
            case 2:// c 健康自评
                RecyclerView ry_c = helper.getView(R.id.recylerView);
                ry_c.setNestedScrollingEnabled(false);
                NestingCAdapter c_Adapter=new NestingCAdapter(item.getList());
                //避免间距逐渐增加
                if (ry_c.getItemDecorationCount() == 0 ) {
                  //  ry_c.addItemDecoration(new RecyclerViewDivider(mContext, LinearLayoutManager.VERTICAL, DimenUtil.dipTopx(mContext, 10), mContext.getResources().getColor(R.color.whiles)));
                }
                ry_c.setLayoutManager(new GridLayoutManager(mContext, 4, RecyclerView.VERTICAL, false));
                ry_c.setAdapter(c_Adapter);
                break;
            case 3: // d  调养妙方
                RecyclerView ry_d = helper.getView(R.id.recylerView);
                ry_d.setNestedScrollingEnabled(false);
                NestingDAdapter d_Adapter=new NestingDAdapter(item.getList());
                d_Adapter.setMyClickListener(new NestingDAdapter.OnMyClickListener() {
                    @Override
                    public void onMyClick(int position) {
                        if(mClickListener!=null){
                            mClickListener.onMyClick(3,position);
                        }
                    }
                });
                //避免间距逐渐增加
                if (ry_d.getItemDecorationCount() == 0 ) {
                    ry_d.addItemDecoration(new RecyclerViewDivider(mContext, LinearLayoutManager.VERTICAL, DimenUtil.dipTopx(mContext, 10), mContext.getResources().getColor(R.color.whiles)));
                }
                ry_d.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false));
                ry_d.setAdapter(d_Adapter);
                break;
            case 4://e  健康运动
                helper.setText(R.id.tvSport, "健康运动2");
                helper.addOnClickListener(R.id.tvSport);
                RecyclerView ry_e = helper.getView(R.id.recylerView);
                ry_e.setNestedScrollingEnabled(false);
                NestingEAdapter e_Adapter=new NestingEAdapter(item.getList());
                //避免间距逐渐增加
                if (ry_e.getItemDecorationCount() == 0 ) {
                  //  ry_e.addItemDecoration(new RecyclerViewDivider(mContext, LinearLayoutManager.VERTICAL, DimenUtil.dipTopx(mContext, 10), mContext.getResources().getColor(R.color.whiles)));
                }
                ry_e.setLayoutManager(new GridLayoutManager(mContext, 2, RecyclerView.VERTICAL, false));
                ry_e.setAdapter(e_Adapter);
                break;
            case 5://f 形态时段
                RecyclerView ry_f= helper.getView(R.id.recylerView);
                NestingFAdapter f_Adapter=new NestingFAdapter(item.getList());
                //避免间距逐渐增加
                if (ry_f.getItemDecorationCount() == 0 ) {
                    ry_f.addItemDecoration(new RecyclerViewDivider(mContext, LinearLayoutManager.HORIZONTAL, DimenUtil.dipTopx(mContext, 10), mContext.getResources().getColor(R.color.whiles)));
                }
                ry_f.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false));
                ry_f.setAdapter(f_Adapter);
                break;
        }
    }
    /**
     * 按钮监听对外接口
     */
    public static interface OnMyClickListener {
        void onMyClick(int group,int position);
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
