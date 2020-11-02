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
                Toast.makeText(DraggableActivity.this, "Child点击" + position, Toast.LENGTH_SHORT).show();
            }
        });
        recylerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(DraggableActivity.this, "item==" + Integer.toString(position), Toast.LENGTH_SHORT).show();
            }
        });

        // 拖拽监听
        ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(mAdapter);
        //滑动配置  方向
        itemDragAndSwipeCallback.setDragMoveFlags(ItemTouchHelper.START );
        //长按拖动
        //  itemDragAndSwipeCallback.setDragMoveFlags(ItemTouchHelper.UP | ItemTouchHelper.DOWN);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
        //拖动事件附加到recylerView  必加
        itemTouchHelper.attachToRecyclerView(recylerView);


        mAdapter.enableDragItem(itemTouchHelper);
        mAdapter.setOnItemDragListener(new OnItemDragListener() {
            @Override
            public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {

            }

            @Override
            public void onItemDragMoving(RecyclerView.ViewHolder viewHolder, int from, RecyclerView.ViewHolder viewHolder1, int to) {
                Log.e("testz","---------------from="+from+"------------to="+to);
            }

            @Override
            public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {

            }
        });

        // 开启滑动删除
        mAdapter.enableSwipeItem();
        mAdapter.setOnItemSwipeListener(new OnItemSwipeListener() {
            @Override
            public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {
                Log.e("testz", "-------drag start");
                final BaseViewHolder holder = ((BaseViewHolder) viewHolder);

                // 开始时，item背景色变化，demo这里使用了一个动画渐变，使得自然
                int startColor = Color.WHITE;
                int endColor = Color.rgb(245, 245, 245);
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ValueAnimator v = ValueAnimator.ofArgb(startColor, endColor);
                    v.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            holder.itemView.setBackgroundColor((int)animation.getAnimatedValue());
                        }
                    });
                    v.setDuration(300);
                    v.start();
                }
            }

            @Override
            public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {

            }

            @Override
            public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int i) {
                //侧滑删除可以在这里进行数据更新
                Toast.makeText(DraggableActivity.this, "删除成功item=" +  i+"  size="+mData.size(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {
                Log.e("testz","---------------dX="+dX+"------------dY="+dY);
                canvas.drawColor(ContextCompat.getColor(DraggableActivity.this, R.color.colorPrimaryDark));
                if(DimenUtil.pxTodip(DraggableActivity.this,dX)>50){
//                    AlertDialog.Builder   builder=new AlertDialog.Builder (DraggableActivity.this);
//                    builder.setTitle("请回答");
//                    builder.setMessage("你觉得我好看吗？？");
//                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int i) {
//                            dialog.dismiss();
//                        }
//                    });
//                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int i) {
//                            dialog.dismiss();
//                        }
//                    });
//                    builder.show();
                }
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
