package com.hcr.turtle.entiey;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单树实体类
 */
/*@Getter
@Setter
@ToString*/
public class Tree implements Serializable {
    private Integer id;
    private String label;
    private List<Tree> children;
    private String url;
    private String icon;

    @Override
    public String toString() {
        return "Tree{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", children=" + children +
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Tree> getChildren() {
        return children;
    }

    public void setChildren(List<Tree> children) {
        this.children = children;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
