/*
 * Category model.
 */

package ast20201.project.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import org.springframework.stereotype.Component;

@Component
public class Category {
    private long id;
    private String name;
    private long order;
    @JsonProperty(access = Access.WRITE_ONLY)
    private long level;
    private List<Category> children;

    public Category() {
        children = new ArrayList<Category>();
    }

    public Category(long id, String name, long order, long level) {
        this.id = id;
        this.name = name;
        this.order = order;
        this.level = level;
        this.children = new ArrayList<Category>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getOrder() {
        return order;
    }

    public void setOrder(long order) {
        this.order = order;
    }

    public long getLevel() {
        return level;
    }

    public void setLevel(long level) {
        this.level = level;
    }

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }

    public void addChildren(Category category) {
        if (null == children)
            children = new ArrayList<Category>();
        children.add(category);
    }
}
