package cn.unicom.met.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 员工实体类
 * @author Administrator *
 */
public class EmpMenu implements Serializable {
	private String menuid;//部门或员工id
	private String menuname;//部门或员工名称

	private List<EmpMenu> menus;

    public List<EmpMenu> getMenus() {
        return menus;
    }

    public void setMenus(List<EmpMenu> menus) {
        this.menus = menus;
    }

    public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

}
