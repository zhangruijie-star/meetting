package cn.unicom.met.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class HandleRecord {
    private String metuuid;

    private String dealuuid;

    private String dealname;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date dealtime;

    private String dealway;

    private String dealresult;

    private String comment;

    public String getDealname() {
        return dealname;
    }

    public void setDealname(String dealname) {
        this.dealname = dealname;
    }

    public String getMetuuid() {
        return metuuid;
    }

    public void setMetuuid(String metuuid) {
        this.metuuid = metuuid;
    }

    public String getDealuuid() {
        return dealuuid;
    }

    public void setDealuuid(String dealuuid) {
        this.dealuuid = dealuuid;
    }

    public Date getDealtime() {
        return dealtime;
    }

    public void setDealtime(Date dealtime) {
        this.dealtime = dealtime;
    }

    public String getDealway() {
        return dealway;
    }

    public void setDealway(String dealway) {
        this.dealway = dealway;
    }

    public String getDealresult() {
        return dealresult;
    }

    public void setDealresult(String dealresult) {
        this.dealresult = dealresult;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
