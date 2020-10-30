package com.he.skt.kotlin.xdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.he.skt.kotlin.xdemo.activity.MoreActivity;
import com.he.skt.kotlin.xdemo.activity.NestingActivity;
import com.he.skt.kotlin.xdemo.activity.SingleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btOne, R.id.btTwo,R.id.btThree})
    public void onViewClicked(View view) {
        Intent intent=null;
        switch (view.getId()) {
            case R.id.btOne:
                intent=new Intent(this,SingleActivity.class);
                startActivity(intent);
                break;
            case R.id.btTwo:
                intent=new Intent(this, MoreActivity.class);
                startActivity(intent);
                break;
            case R.id.btThree:
                intent=new Intent(this, NestingActivity.class);
                startActivity(intent);
                break;
        }
    }
}
