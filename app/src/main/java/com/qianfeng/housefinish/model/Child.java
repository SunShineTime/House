package com.qianfeng.housefinish.model;

/**
 * Created by 徐余璟 on 2016/9/21.
 */
public class Child {


    /**
     * categoryId : 0802
     * categoryName : 沙发
     * categoryPicUrl : http://imgs.zhaidou.com/saleCate/02/0802/sclog1_20160001.jpg
     * children : null
     * categoryProductCount : null
     * parentId : 08
     */

    private String categoryId;
    private String categoryName;
    private String categoryPicUrl;
    private String parentId;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryPicUrl() {
        return categoryPicUrl;
    }

    public void setCategoryPicUrl(String categoryPicUrl) {
        this.categoryPicUrl = categoryPicUrl;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
