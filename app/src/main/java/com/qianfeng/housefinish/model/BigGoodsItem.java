package com.qianfeng.housefinish.model;

/**
 * Created by chengchao on 2016/9/22.
 */
public class BigGoodsItem {
    private GoodsItemOne activityPO;
    private GoodsItemTwoList pagePO;

    public GoodsItemOne getActivityPO() {
        return activityPO;
    }

    public void setActivityPO(GoodsItemOne activityPO) {
        this.activityPO = activityPO;
    }

    public GoodsItemTwoList getPagePO() {
        return pagePO;
    }

    public void setPagePO(GoodsItemTwoList pagePO) {
        this.pagePO = pagePO;
    }
}
