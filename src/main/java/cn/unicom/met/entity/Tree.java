package cn.unicom.met.entity;

import java.util.List;

public class Tree {
    /**
     * 属性菜单数据实体
     * id：节点ID，对加载远程数据很重要。
     * text：显示节点文本。
     * state：节点状态，'open' 或 'closed'，默认：'open'。如果为'closed'的时候，将不自动展开该节点。
     * checked：表示该节点是否被选中。
     * attributes: 被添加到节点的自定义属性。
     * children: 一个节点数组声明了若干节点。
     */
    private String id; //菜单id
    private String text;//菜单名称
    private boolean checked;//是否选中
    private List<Tree> children;//子菜单

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public List<Tree> getChildren() {
        return children;
    }

    public void setChildren(List<Tree> children) {
        this.children = children;
    }
}
