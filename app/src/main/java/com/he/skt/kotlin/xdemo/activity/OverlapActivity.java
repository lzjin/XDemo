package com.he.skt.kotlin.xdemo.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.he.skt.kotlin.xdemo.R;
import com.he.skt.kotlin.xdemo.adapter.OverlapAdapter;
import com.he.skt.kotlin.xdemo.adapter.SingleAdapter;
import com.he.skt.kotlin.xdemo.bean.SingleBean;
import com.he.skt.kotlin.xdemo.utils.DimenUtil;
import com.he.skt.kotlin.xdemo.widgit.RecyclerViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * description ： 头像重叠
 * author : asus
 * date : 2020/11/2
 */
public class OverlapActivity extends AppCompatActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recylerView)
    RecyclerView recylerView;
    private OverlapAdapter mAdapter;
    private List<SingleBean> mData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overlap);
        ButterKnife.bind(this);
        tvTitle.setText("重叠");

        initRV();
        getDataList();
    }

    private void initRV() {
        mData = new ArrayList<>();
        mAdapter = new OverlapAdapter(mData);
        recylerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recylerView.setNestedScrollingEnabled(false);//解决滑动不流畅
        recylerView.setAdapter(mAdapter);

        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN); //开启动画 效果 SCALEIN
        mAdapter.isFirstOnly(false); //仅第一次刷新动画

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(OverlapActivity.this, "Child点击" + position, Toast.LENGTH_SHORT).show();
            }
        });
        recylerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(OverlapActivity.this, "item==" + Integer.toString(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDataList() {
        mData.add(new SingleBean( "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3117353952,3891314081&fm=26&gp=0.jpg"));
        mData.add(new SingleBean( "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1494164303,2636162565&fm=11&gp=0.jpg"));
        mData.add(new SingleBean( "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2719253220,2279421368&fm=26&gp=0.jpg"));
        mData.add(new SingleBean( "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604309350114&di=d1e92e238dfcd4e5a365863670b40ef7&imgtype=0&src=http%3A%2F%2Fp8.itc.cn%2Fq_70%2Fimages03%2F20201027%2F6323aee7874e4feda2b4b8445bfa0432.jpeg"));
        //mAdapter.setNewData(mData);
        mAdapter.notifyDataSetChanged();

    }
}
