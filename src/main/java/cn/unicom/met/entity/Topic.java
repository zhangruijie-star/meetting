package cn.unicom.met.entity;

/**
 * 议题实体类
 * @author Administrator *
 */
public class Topic {
	private String topic_uuid;//议题编号
	private String met_uuid;//会议编号
    private String topic_title;//议题名称
	private String report_time;//汇报时长
	private String talk_time;//讨论时长
    private String comment;//议题说明

    public String getTopic_title() {
        return topic_title;
    }

    public void setTopic_title(String topic_title) {
        this.topic_title = topic_title;
    }

    public Topic() {
    }

    public String getTopic_uuid() {
        return topic_uuid;
    }

    public void setTopic_uuid(String topic_uuid) {
        this.topic_uuid = topic_uuid;
    }

    public String getMet_uuid() {
        return met_uuid;
    }

    public void setMet_uuid(String met_uuid) {
        this.met_uuid = met_uuid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getReport_time() {
        return report_time;
    }

    public void setReport_time(String report_time) {
        this.report_time = report_time;
    }

    public String getTalk_time() {
        return talk_time;
    }

    public void setTalk_time(String talk_time) {
        this.talk_time = talk_time;
    }
}
