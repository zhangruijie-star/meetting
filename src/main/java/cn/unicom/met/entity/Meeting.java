package cn.unicom.met.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 会议实体类
 * @author Administrator *
 */
public class Meeting {
	private String uuid;//编号
	private String mtitle;//会议名称
	private String mtype;//会议类型--mtype_uuid
	private String address;//会议地点--address_uuid
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date start_time;//会议开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date end_time;//会议结束时间
    private String status;//会议状态：申请/审核
    private String create_uuid;//会议创建及申请者：员工id
    private String next_deal_uuid;//会议审核人：员工id
    private String next_deal_name;//会议审核人：员工name
    private String attend_met_empuuid; //参会员工id
    private String attend_met_empname; //参会员工name
    private String attend_met_depuuid;//参会部门id
    private String attend_met_depname;//参会部门name
    private String mtext;//会议审核人：员工id
    private String creater;
//    private String dealer;

    public String getNext_deal_name() {
        return next_deal_name;
    }

    public void setNext_deal_name(String next_deal_name) {
        this.next_deal_name = next_deal_name;
    }

    public String getAttend_met_empname() {
        return attend_met_empname;
    }

    public void setAttend_met_empname(String attend_met_empname) {
        this.attend_met_empname = attend_met_empname;
    }

    public String getAttend_met_depname() {
        return attend_met_depname;
    }

    public void setAttend_met_depname(String attend_met_depname) {
        this.attend_met_depname = attend_met_depname;
    }

    public String getAttend_met_empuuid() {
        return attend_met_empuuid;
    }

    public void setAttend_met_empuuid(String attend_met_empuuid) {
        this.attend_met_empuuid = attend_met_empuuid;
    }

    public String getAttend_met_depuuid() {
        return attend_met_depuuid;
    }

    public void setAttend_met_depuuid(String attend_met_depuuid) {
        this.attend_met_depuuid = attend_met_depuuid;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

//    public String getDealer() {
//        return dealer;
//    }
//
//    public void setDealer(String dealer) {
//        this.dealer = dealer;
//    }
    //    private Emp creater;
//    private Emp dealer;
//
//    public Emp getCreater() {
//        return creater;
//    }
//
//    public void setCreater(Emp creater) {
//        this.creater = creater;
//    }
//
//    public Emp getDealer() {
//        return dealer;
//    }
//
//    public void setDealer(Emp dealer) {
//        this.dealer = dealer;
//    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMtitle() {
        return mtitle;
    }

    public void setMtitle(String mtitle) {
        this.mtitle = mtitle;
    }

    public String getMtype() {
        return mtype;
    }

    public void setMtype(String mtype) {
        this.mtype = mtype;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreate_uuid() {
        return create_uuid;
    }

    public void setCreate_uuid(String create_uuid) {
        this.create_uuid = create_uuid;
    }

    public String getNext_deal_uuid() {
        return next_deal_uuid;
    }

    public void setNext_deal_uuid(String next_deal_uuid) {
        this.next_deal_uuid = next_deal_uuid;
    }

    public String getMtext() {
        return mtext;
    }

    public void setMtext(String mtext) {
        this.mtext = mtext;
    }
}
