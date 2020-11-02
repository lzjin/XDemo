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
import com.he.skt.kotlin.xdemo.adapter.NestingAdapter;
import com.he.skt.kotlin.xdemo.adapter.SingleAdapter;
import com.he.skt.kotlin.xdemo.bean.NestingBean;
import com.he.skt.kotlin.xdemo.bean.NestingMainBean;
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
 * description ： 嵌套布局
 * author : asus
 * date : 2020/10/30
 */
public class NestingActivity extends AppCompatActivity {
    @BindView(R.id.recylerView)
    RecyclerView recylerView;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private NestingAdapter mAdapter;
    private List<NestingMainBean> mData;
    private int page = 1, pageSize = 10;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nesting);
        ButterKnife.bind(this);

        initRefresh();
        initView();
        initRV();
        dataDownRefresh();
    }

    private void initView() {
        tvTitle.setText("嵌套布局");
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
        new Thread(new Runnable() {
            @Override
            public void run() {
                getDataList();
            }
        }).start();
    }

    private void dataUpMore() {
        page++;
        new Thread(new Runnable() {
            @Override
            public void run() {
                getDataList();
            }
        }).start();
    }

    private void initRV() {
        mData = new ArrayList<>();
        mAdapter = new NestingAdapter(mData);
        recylerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recylerView.setNestedScrollingEnabled(false);//解决滑动不流畅
        recylerView.setAdapter(mAdapter);

        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN); //开启动画 效果 SCALEIN
        mAdapter.isFirstOnly(false); //仅第一次刷新动画
        mAdapter.setMyClickListener(new NestingAdapter.OnMyClickListener() {
            @Override
            public void onMyClick(int group, int position) {
                switch (group){
                    case 3:
                         Toast.makeText(NestingActivity.this, "Child点击3,== " + position, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position){
                    case 0:
                        if(view.getId()==R.id.tvTest){
                            Toast.makeText(NestingActivity.this, "Child点击测试" , Toast.LENGTH_SHORT).show();
                        }else if(view.getId()==R.id.tv_day){
                            Toast.makeText(NestingActivity.this, "Child点击30" , Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 1:
                        if(view.getId()==R.id.tvSdb1){
                            Toast.makeText(NestingActivity.this, "Child点击 每日推荐" , Toast.LENGTH_SHORT).show();
                        }else if(view.getId()==R.id.tvSdb2){
                            Toast.makeText(NestingActivity.this, "Child点击 最佳销售" , Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 4:
                        if(view.getId()==R.id.tvSport){
                            Toast.makeText(NestingActivity.this, "Child点击 健康运动" , Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }
        });
        recylerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(NestingActivity.this, "item==" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDataList() {
        if (page == 1) {
            mData.clear();
            // type = 0
            mData.add(new NestingMainBean(0));

            // type = 1
            mData.add(new NestingMainBean(1));

            // type = 2
            List<NestingBean> list =new ArrayList<>();
            list.add(new NestingBean("膳食时段",""));
            list.add(new NestingBean("膳食游客",""));
            list.add(new NestingBean("豆腐干大",""));
            list.add(new NestingBean("刚点燃的",""));
            mData.add(new NestingMainBean(2,list));

            // type = 3
            List<NestingBean> list3 =new ArrayList<>();
            list3.add(new NestingBean("阴虚体质","https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1604052752&di=d82bf175377928d5dbbbdbb71df84de9&src=http://a3.att.hudong.com/64/52/01300000407527124482522224765.jpg"));
            list3.add(new NestingBean("消化系统","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604062837440&di=c3de5c80c84974d751a4673a4f346f97&imgtype=0&src=http%3A%2F%2Fa4.att.hudong.com%2F52%2F52%2F01200000169026136208529565374.jpg"));
            list3.add(new NestingBean("阳虚体质","https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1604052752&di=d82bf175377928d5dbbbdbb71df84de9&src=http://a3.att.hudong.com/64/52/01300000407527124482522224765.jpg"));
            list3.add(new NestingBean("重复的发","https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1604052752&di=d82bf175377928d5dbbbdbb71df84de9&src=http://a3.att.hudong.com/64/52/01300000407527124482522224765.jpg"));
            list3.add(new NestingBean("也认同感","https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1604052752&di=d82bf175377928d5dbbbdbb71df84de9&src=http://a3.att.hudong.com/64/52/01300000407527124482522224765.jpg"));
            list3.add(new NestingBean("东方故事","https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1604052752&di=d82bf175377928d5dbbbdbb71df84de9&src=http://a3.att.hudong.com/64/52/01300000407527124482522224765.jpg"));
            mData.add(new NestingMainBean(3,list3));
            // type = 4
            List<NestingBean> list4 =new ArrayList<>();
            list4.add(new NestingBean("8分钟简单高效瘦胳膊运动","https://img.ougo.ltd/Fkvz-2NjDMZ4wqthPqP-ngCtdpob"));
            list4.add(new NestingBean("太极拳24式，第一式，起势","https://img.ougo.ltd/Fvtnz46ul_apUiBNPL0N17Y50Kp4"));
            list4.add(new NestingBean("早晨瑜伽，精神一整天","https://img.ougo.ltd/Fvhzeoox2lGu120QBb376TaFpfYH"));
            list4.add(new NestingBean("手部穴位认识","https://img.ougo.ltd/Fvtnz46ul_apUiBNPL0N17Y50Kp4"));
            mData.add(new NestingMainBean(4,list4));

            // type = 5
            List<NestingBean> list5 =new ArrayList<>();
            list5.add(new NestingBean("8分钟简单高效瘦胳膊运动","https://img.ougo.ltd/Fkvz-2NjDMZ4wqthPqP-ngCtdpob","55","99"));
            list5.add(new NestingBean("太极拳24式，第一式，起势","https://img.ougo.ltd/Fvtnz46ul_apUiBNPL0N17Y50Kp4","55","99"));
            list5.add(new NestingBean("早晨瑜伽，精神一整天","https://img.ougo.ltd/Fvhzeoox2lGu120QBb376TaFpfYH","55","99"));
            list5.add(new NestingBean("手部穴位认识","https://img.ougo.ltd/Fvtnz46ul_apUiBNPL0N17Y50Kp4","55","99"));
            mData.add(new NestingMainBean(5,list5));
        }else {
            // type = 5
            List<NestingBean> list5 =new ArrayList<>();
            list5.add(new NestingBean("更新效瘦运动xxx","https://img.ougo.ltd/Fkvz-2NjDMZ4wqthPqP-ngCtdpob","29","89"));
            list5.add(new NestingBean("更新效瘦运动xxx","https://img.ougo.ltd/Fvtnz46ul_apUiBNPL0N17Y50Kp4","29","89"));
            list5.add(new NestingBean("更新效瘦运动xxx","https://img.ougo.ltd/Fvhzeoox2lGu120QBb376TaFpfYH","29","89"));
            list5.add(new NestingBean("更新效瘦运动xxx","https://img.ougo.ltd/Fvtnz46ul_apUiBNPL0N17Y50Kp4","29","89"));
            if(mData.get(mData.size()-1).getItemType()==5){
                mData.get(mData.size()-1).getList().addAll(list5);
            }
        }

        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (page == 1) {
                    mAdapter.setNewData(mData);
                    mAdapter.notifyDataSetChanged();
                    refreshLayout.finishRefresh(1500);
                } else {
                    // mAdapter.addData(mData);
                    mAdapter.notifyDataSetChanged();
                    refreshLayout.finishLoadMore(1500);
                }
            }
        });

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
