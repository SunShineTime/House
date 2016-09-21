package com.qianfeng.housefinish.model;

/**
 * Created by 徐余璟 on 2016/9/21.
 */
public class Proud {

    private String productId;
    private String productName;
    private String productPicUrl;
    private double price;
    private double marketingPrice;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPicUrl() {
        return productPicUrl;
    }

    public void setProductPicUrl(String productPicUrl) {
        this.productPicUrl = productPicUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getMarketingPrice() {
        return marketingPrice;
    }

    public void setMarketingPrice(double marketingPrice) {
        this.marketingPrice = marketingPrice;
    }
}
