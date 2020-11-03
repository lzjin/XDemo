package com.he.skt.kotlin.xdemo.activity;

import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.guanaj.easyswipemenulibrary.EasySwipeMenuLayout;
import com.he.skt.kotlin.xdemo.R;
import com.he.skt.kotlin.xdemo.adapter.DraggableAdapter;
import com.he.skt.kotlin.xdemo.adapter.SingleAdapter;
import com.he.skt.kotlin.xdemo.bean.SingleBean;
import com.he.skt.kotlin.xdemo.utils.DimenUtil;
import com.he.skt.kotlin.xdemo.widgit.RecyclerViewDivider;
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
 * date : 2020/11/2
 */
public class DraggableActivity extends AppCompatActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recylerView)
    RecyclerView recylerView;
    private DraggableAdapter mAdapter;
    private List<SingleBean> mData;
    private int page = 1, pageSize = 10;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draggable);
        ButterKnife.bind(this);
        tvTitle.setText("侧滑删除");

        initRV();
        dataDownRefresh();
    }

    @OnClick({R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
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
        mAdapter = new DraggableAdapter(mData);
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
                switch (view.getId()){
                    case R.id.img_goods:
                        Toast.makeText(DraggableActivity.this, "Child点击图片" + position, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.tvLeftShare:
                        Toast.makeText(DraggableActivity.this, "Child点击分享" + position, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.tvRightDel:
                        Toast.makeText(DraggableActivity.this, "Child点击删除" + position, Toast.LENGTH_SHORT).show();
                       // mAdapter.remove(position);
                       // mAdapter.notifyDataSetChanged();
                        break;
                    case R.id.tvRightMenu:
                        Toast.makeText(DraggableActivity.this, "Child点击收藏" + position, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        mAdapter.setMyClickListener(new DraggableAdapter.OnMyClickListener() {
            @Override
            public void onMyClick(int position, EasySwipeMenuLayout swipeMenuLayout) {
                swipeMenuLayout.resetStatus();//关闭侧滑
                // mAdapter.remove(position);
                // mAdapter.notifyDataSetChanged();
            }
        });
        recylerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(DraggableActivity.this, "item==" + Integer.toString(position), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void getDataList() {
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

        } else {
            // mAdapter.addData(mData);
            mAdapter.notifyDataSetChanged();

        }

    }
}
