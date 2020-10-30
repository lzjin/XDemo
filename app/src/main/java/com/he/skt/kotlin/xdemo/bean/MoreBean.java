package com.he.skt.kotlin.xdemo.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * description ： 多布局
 * author : asus
 * date : 2020/10/30
 */
public class MoreBean implements MultiItemEntity {
    private int itemType; //布局标识
    private String name;
    private String imgUrl;
    private String autPrice;
    private String orgPrice;

    public MoreBean(int itemType,String name, String imgUrl, String autPrice, String orgPrice) {
        this.itemType=itemType;
        this.name = name;
        this.imgUrl = imgUrl;
        this.autPrice = autPrice;
        this.orgPrice = orgPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getAutPrice() {
        return autPrice;
    }

    public void setAutPrice(String autPrice) {
        this.autPrice = autPrice;
    }

    public String getOrgPrice() {
        return orgPrice;
    }

    public void setOrgPrice(String orgPrice) {
        this.orgPrice = orgPrice;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
