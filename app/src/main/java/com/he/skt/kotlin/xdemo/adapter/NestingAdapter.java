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
        if(data!=null&&data.size()>0){
            switch (data.get(0).getFunctionType()){
                case 0:
                    addItemType(0, R.layout.item_nesting_a);
                    break;
                case 1:
                    addItemType(1, R.layout.item_nesting_b);
                    break;
                case 2:
                    addItemType(2, R.layout.item_nesting_c);
                    break;
                case 3:
                    addItemType(3, R.layout.item_nesting_d);
                    break;
                case 4:
                    addItemType(4, R.layout.item_nesting_e);
                    break;
                case 5:
                    addItemType(5, R.layout.item_nesting_f);
                    break;
            }

        }
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, NestingMainBean item) {
        // 第三步：设置不同布局下的组件数据
        switch (helper.getItemViewType()) {
            case 0:
                helper.setText(R.id.tv_date, "润四月十二");
                break;
            case 1:
                helper.setText(R.id.tvSdb1, "每日推荐");
                helper.setText(R.id.tvSdb2, "最佳销量");
                break;
            case 2:// c
                RecyclerView ry_c = helper.getView(R.id.recylerView);
                NestingCAdapter c_Adapter=new NestingCAdapter(item.getList());
                ry_c.addItemDecoration(new RecyclerViewDivider(mContext, LinearLayoutManager.VERTICAL, DimenUtil.dipTopx(mContext, 10), mContext.getResources().getColor(R.color.whiles)));
                ry_c.setLayoutManager(new GridLayoutManager(mContext, 4, RecyclerView.VERTICAL, false));
                ry_c.setAdapter(c_Adapter);
                break;
            case 3: // d
                RecyclerView ry_d = helper.getView(R.id.recylerView);
                NestingDAdapter d_Adapter=new NestingDAdapter(item.getList());
                ry_d.addItemDecoration(new RecyclerViewDivider(mContext, LinearLayoutManager.HORIZONTAL, DimenUtil.dipTopx(mContext, 10), mContext.getResources().getColor(R.color.whiles)));
                ry_d.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false));
                ry_d.setAdapter(d_Adapter);
                break;
            case 4://e
                RecyclerView ry_e = helper.getView(R.id.recylerView);
                NestingEAdapter e_Adapter=new NestingEAdapter(item.getList());
                ry_e.addItemDecoration(new RecyclerViewDivider(mContext, LinearLayoutManager.VERTICAL, DimenUtil.dipTopx(mContext, 10), mContext.getResources().getColor(R.color.whiles)));
                ry_e.setLayoutManager(new GridLayoutManager(mContext, 2, RecyclerView.VERTICAL, false));
                ry_e.setAdapter(e_Adapter);
                break;
            case 5:
                RecyclerView ry_f= helper.getView(R.id.recylerView);
                NestingFAdapter f_Adapter=new NestingFAdapter(item.getList());
                ry_f.addItemDecoration(new RecyclerViewDivider(mContext, LinearLayoutManager.HORIZONTAL, DimenUtil.dipTopx(mContext, 10), mContext.getResources().getColor(R.color.whiles)));
                ry_f.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false));
                ry_f.setAdapter(f_Adapter);
                break;
        }
    }
}
