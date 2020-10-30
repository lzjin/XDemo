package com.he.skt.kotlin.xdemo.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.he.skt.kotlin.xdemo.inner.HomeFunction;

import java.util.List;

/**
 * description ： TODO:类的作用
 * author : asus
 * date : 2020/10/30
 */
public class NestingMainBean implements HomeFunction, MultiItemEntity {
    private List<NestingBean> list;
    private int type = -1;

    public NestingMainBean() {
    }

    public NestingMainBean(int type) {
        this.type = type;
    }

    public NestingMainBean(int type , List<NestingBean> list) {
        this.list = list;
        this.type = type;
    }

    public List<NestingBean> getList() {
        return list;
    }

    public void setList(List<NestingBean> list) {
        this.list = list;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int getFunctionType() {
        return type;
    }

    @Override
    public int getItemType() {
        return type;
    }
}
