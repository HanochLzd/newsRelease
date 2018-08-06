package cn.soft.news.po;

import java.io.Serializable;

/**
 * 未登录点赞
 *
 * @author Hanoch
 */
public class Praise implements Serializable {
    /**
     * 点赞id
     */
    private String praiseId;
    private int praiseType;
    private String praiseNewsId;
    private String praiseIp;


    public Praise() {
    }

    public Praise(String praiseId, int praiseType, String praiseNewsId, String praiseIp) {
        this.praiseId = praiseId;
        this.praiseType = praiseType;
        this.praiseNewsId = praiseNewsId;
        this.praiseIp = praiseIp;
    }

    public String getPraiseId() {
        return praiseId;
    }

    public void setPraiseId(String praiseId) {
        this.praiseId = praiseId;
    }

    public int getPraiseType() {
        return praiseType;
    }

    public void setPraiseType(int praiseType) {
        this.praiseType = praiseType;
    }

    public String getPraiseNewsId() {
        return praiseNewsId;
    }

    public void setPraiseNewsId(String praiseNewsId) {
        this.praiseNewsId = praiseNewsId;
    }

    public String getPraiseIp() {
        return praiseIp;
    }

    public void setPraiseIp(String praiseIp) {
        this.praiseIp = praiseIp;
    }
}
