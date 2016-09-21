package com.qianfeng.housefinish.model;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by 徐余璟 on 2016/9/20.
 */
@Table(name = "home")
public class Home {
    @Column(name = "id",isId = true ,autoGen = false)
    private String id;
    @Column(name = "caseName")
    private String caseName;
    @Column(name = "mainDesc")
    private String mainDesc;
    @Column(name = "mainPic")
    private String mainPic;
    @Column(name = "commentCount")
    private String commentCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getMainDesc() {
        return mainDesc;
    }

    public void setMainDesc(String mainDesc) {
        this.mainDesc = mainDesc;
    }

    public String getMainPic() {
        return mainPic;
    }

    public void setMainPic(String mainPic) {
        this.mainPic = mainPic;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }
}
