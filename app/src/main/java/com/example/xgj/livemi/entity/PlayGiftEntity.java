package com.example.xgj.livemi.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by chen on 2017/4/26.
 */

public class PlayGiftEntity implements MultiItemEntity {

    public static final int TYPE_1 = 100;
    public static final int TYPE_2 = 200;


    private String imageUrl;
    private String shopName;
    private String price;
    private int itemType;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
