package com.qianfeng.housefinish.model;

import java.util.List;

/**
 * Created by 徐余璟 on 2016/9/22.
 */
public class DetailsList {

    private String productDescription;
    private List<String> productInfoImages;
    private List<Attribute>attributeList;

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public List<String> getProductInfoImages() {
        return productInfoImages;
    }

    public void setProductInfoImages(List<String> productInfoImages) {
        this.productInfoImages = productInfoImages;
    }

    public List<Attribute> getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(List<Attribute> attributeList) {
        this.attributeList = attributeList;
    }
}
