package cn.unicom.met.entity;

public class ClaimMeetItem {
    private Integer id;

    private Integer claimMeetId;

    private String item;

    private Double amount;

    private String comment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClaimMeetId() {
        return claimMeetId;
    }

    public void setClaimMeetId(Integer claimMeetId) {
        this.claimMeetId = claimMeetId;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}