package com.qianfeng.housefinish.model;

import java.util.List;

/**
 * Created by 徐余璟 on 2016/9/21.
 */
public class BigProudList {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        private ProudList pagePO;

        public ProudList getPagePO() {
            return pagePO;
        }
        public void setPagePO(ProudList pagePO) {
            this.pagePO = pagePO;
        }

    }
}
