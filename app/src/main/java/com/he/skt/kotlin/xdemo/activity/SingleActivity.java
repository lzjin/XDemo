package com.he.skt.kotlin.xdemo.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
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
import com.he.skt.kotlin.xdemo.adapter.SingleAdapter;
import com.he.skt.kotlin.xdemo.bean.SingleBean;
import com.he.skt.kotlin.xdemo.utils.DimenUtil;
import com.he.skt.kotlin.xdemo.widgit.RecyclerViewDivider;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * description ： TODO:类的作用
 * author : asus
 * date : 2020/10/30
 */
public class SingleActivity extends AppCompatActivity {
    @BindView(R.id.recylerView)
    RecyclerView recylerView;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private SingleAdapter mAdapter;
    private List<SingleBean> mData;
    private int page = 1, pageSize = 10;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);
        ButterKnife.bind(this);

        initRefresh();
        initView();
        initRV();
        dataDownRefresh();
    }

    private void initView() {
        tvTitle.setText("单布局");
    }

    private void initRefresh() {

        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                dataUpMore();//上拉加载更多
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                dataDownRefresh();//下拉刷新
            }
        });

    }


    private void dataDownRefresh() {
        page = 1;
        getDataList();
    }

    private void dataUpMore() {
        page++;
        getDataList();
    }

    private void initRV() {
        mData = new ArrayList<>();
        mAdapter = new SingleAdapter(mData);
        recylerView.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.HORIZONTAL,
                DimenUtil.dipTopx(this, 10), this.getResources().getColor(R.color.light_gray)));
        recylerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recylerView.setNestedScrollingEnabled(false);//解决滑动不流畅
        recylerView.setAdapter(mAdapter);

        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN); //开启动画 效果 SCALEIN
        mAdapter.isFirstOnly(false); //仅第一次刷新动画

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(SingleActivity.this, "Child点击" + position, Toast.LENGTH_SHORT).show();
            }
        });
        recylerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(SingleActivity.this, "item==" + Integer.toString(position), Toast.LENGTH_SHORT).show();
            }
        });
        //添加头布局
        View headView=LayoutInflater.from(this).inflate(R.layout.view_header_layout,null);
        mAdapter.addHeaderView(headView);
    }

    private void getDataList() {
        Log.e("testz","-----------page="+page);
        if (page == 1) {
            mData.clear();
        }
        for (int i = 0; i < (page == 1 ? 8 : 3); i++) {
            if (page == 1) {
                mData.add(new SingleBean("龙蛋蛋白石费分时段", "https://img.ougo.ltd/FmJ5E9-shKB7Suj3iy0gJkiSeEWK", "¥22" + i, "¥34" + i));
            } else {
                mData.add(new SingleBean("更多数据老胜多负少", "https://img.ougo.ltd/FqDV-hb2yc1SwNAoqAs8U_C-dImg", "¥62" + i, "¥83" + i));
            }
        }
        if (page == 1) {
            //mAdapter.setNewData(mData);
            mAdapter.notifyDataSetChanged();
            refreshLayout.finishRefresh(1500);
        } else {
           // mAdapter.addData(mData);
            mAdapter.notifyDataSetChanged();
            refreshLayout.finishLoadMore(1500);
        }

    }

    @OnClick({R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
