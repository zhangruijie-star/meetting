package cn.unicom.met.entity;

/**
 * 部门
 */
public class Dep {

    /** 部门编号 **/
    private String uuid;

    /**
     * 部门名称
     */
    private String name;
    /**
     * 联系电话
     */
    private String tele;

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

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }
    private Dep dep;

    public Dep getDep() {
        return dep;
    }

    public void setDep(Dep dep) {
        this.dep = dep;
    }
}
