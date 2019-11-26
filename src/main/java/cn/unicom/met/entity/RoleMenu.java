package cn.unicom.met.entity;

public class RoleMenu {
    /**
     * 角色菜单实体
     */
    private String roleuuid; //角色id
    private String menuuuid;//权限菜单id

    public String getRoleuuid() {
        return roleuuid;
    }

    public void setRoleuuid(String roleuuid) {
        this.roleuuid = roleuuid;
    }

    public String getMenuuuid() {
        return menuuuid;
    }

    public void setMenuuuid(String menuuuid) {
        this.menuuuid = menuuuid;
    }
}
