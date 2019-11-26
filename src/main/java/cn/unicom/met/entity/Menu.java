package cn.unicom.met.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 员工实体类
 * @author Administrator *
 */
public class Menu implements Serializable {
	private String menuid;//菜单id
	private String menuname;//菜单名称
	private String icon;//图标
	private String url;//url
//	private int pid;//上级菜单id
	private List<Menu> menus;

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

//    public int getPid() {
//		return pid;
//	}
//
//	public void setPid(int pid) {
//		this.pid = pid;
//	}
}
