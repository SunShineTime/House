package com.qianfeng.housefinish.model;

import java.util.List;

/**
 * Created by 徐余璟 on 2016/9/21.
 */
public class ProudList {
    private String pageNo;

    private List<Proud>items;

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public List<Proud> getItems() {
        return items;
    }

    public void setItems(List<Proud> items) {
        this.items = items;
    }
}
