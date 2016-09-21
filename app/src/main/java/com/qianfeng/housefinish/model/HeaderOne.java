package com.qianfeng.housefinish.model;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.util.List;

/**
 * Created by 徐余璟 on 2016/9/20.
 */
public class HeaderOne {


    /**
     * data : [{"boardCode":"06","programShowNum":3,"programTotalNum":3,"programPOList":[{"name":"111","type":"3","url":"productId=191105005213","pictureUrl":"http://imgs.zhaidou.com/cms/201608/1472551397539.jpg","price":"599.0","code":"191105005213"},{"name":"222","type":"3","url":"productId=191105005212","pictureUrl":"http://imgs.zhaidou.com/cms/201608/1472551345312.jpg","price":"998.0","code":"191105005212"},{"name":"333","type":"3","url":"productId=191105002873","pictureUrl":"http://imgs.zhaidou.com/cms/201606/1467008635920.jpg","price":"25000.0","code":"191105002873"}]}]
     * status : 200
     * message : null
     * code : null
     * suggestMsg : null
     * timestamp : null
     */

    private int status;
    /**
     * boardCode : 06
     * programShowNum : 3
     * programTotalNum : 3
     * programPOList : [{"name":"111","type":"3","url":"productId=191105005213","pictureUrl":"http://imgs.zhaidou.com/cms/201608/1472551397539.jpg","price":"599.0","code":"191105005213"},{"name":"222","type":"3","url":"productId=191105005212","pictureUrl":"http://imgs.zhaidou.com/cms/201608/1472551345312.jpg","price":"998.0","code":"191105005212"},{"name":"333","type":"3","url":"productId=191105002873","pictureUrl":"http://imgs.zhaidou.com/cms/201606/1467008635920.jpg","price":"25000.0","code":"191105002873"}]
     */

    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String boardCode;
        private int programShowNum;
        private int programTotalNum;
        /**
         * name : 111
         * type : 3
         * url : productId=191105005213
         * pictureUrl : http://imgs.zhaidou.com/cms/201608/1472551397539.jpg
         * price : 599.0
         * code : 191105005213
         */

        private List<ProgramPOListBean> programPOList;

        public String getBoardCode() {
            return boardCode;
        }

        public void setBoardCode(String boardCode) {
            this.boardCode = boardCode;
        }

        public int getProgramShowNum() {
            return programShowNum;
        }

        public void setProgramShowNum(int programShowNum) {
            this.programShowNum = programShowNum;
        }

        public int getProgramTotalNum() {
            return programTotalNum;
        }

        public void setProgramTotalNum(int programTotalNum) {
            this.programTotalNum = programTotalNum;
        }

        public List<ProgramPOListBean> getProgramPOList() {
            return programPOList;
        }

        public void setProgramPOList(List<ProgramPOListBean> programPOList) {
            this.programPOList = programPOList;
        }
        @Table(name = "header")
        public static class ProgramPOListBean {
            private String name;
            private String type;
            private String url;
            @Column(name = "pictureUrl")
            private String pictureUrl;
            private String price;
            @Column(name = "code",isId = true,autoGen = true)
            private String code;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getPictureUrl() {
                return pictureUrl;
            }

            public void setPictureUrl(String pictureUrl) {
                this.pictureUrl = pictureUrl;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }
        }
    }
}
