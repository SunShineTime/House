package com.qianfeng.housefinish.model;

import java.util.List;

/**
 * Created by 徐余璟 on 2016/9/22.
 */
public class BigDetails {

    private String productName;
    private String attributeName1;
    private String attributeName2;
    private DetailsList expandedResponse;
    private List<Name>productSKUArray;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getAttributeName1() {
        return attributeName1;
    }

    public void setAttributeName1(String attributeName1) {
        this.attributeName1 = attributeName1;
    }

    public String getAttributeName2() {
        return attributeName2;
    }

    public void setAttributeName2(String attributeName2) {
        this.attributeName2 = attributeName2;
    }

    public DetailsList getExpandedResponse() {
        return expandedResponse;
    }

    public void setExpandedResponse(DetailsList expandedResponse) {
        this.expandedResponse = expandedResponse;
    }

    public List<Name> getProductSKUArray() {
        return productSKUArray;
    }

    public void setProductSKUArray(List<Name> productSKUArray) {
        this.productSKUArray = productSKUArray;
    }
}
