package com.qianfeng.housefinish.model;

import java.util.List;

/**
 * Created by 徐余璟 on 2016/9/21.
 */
public class ChildList {

    private String categoryId;
    private String  categoryName;
    private List<Child>children;

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

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }
}
