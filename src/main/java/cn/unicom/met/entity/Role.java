package cn.unicom.met.entity;

import java.util.List;

public class Role {
    /**
     * 角色实体
     */
    private String uuid; //角色id
    private String name;//角色名称

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
