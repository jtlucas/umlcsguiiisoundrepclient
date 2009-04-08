/*
 * HeaderField.java
 *
 * Created on Mar 29, 2009, 6:21:57 PM
 */
package glisten;

import java.util.Hashtable;
import java.util.Vector;
//import java.util.

/**
 *
 * @author Nathan Gilbert
 */
public class HeaderField {

    private String name;
    private String caption;
    private boolean show;
    private boolean search;
    private int width;

    public HeaderField(Hashtable<String, String> props) {
        if (props.containsKey("name")) {
            name = props.get("name");
        } else {
            name = "";
        }
        if (props.containsKey("caption")) {
            caption = props.get("caption");
        } else {
            caption = "";
        }
        if (props.containsKey("show")) {
            show = Boolean.parseBoolean(props.get("show"));
        } else {
            show = true;
        }
        if (props.containsKey("search")) {
            search = Boolean.parseBoolean(props.get("search"));
        } else {
            search = false;
        }
        if (props.containsKey("width")) {
            width = Integer.getInteger(props.get("width"), 75);
        } else {
            width = 75;
        }
        if (caption.isEmpty()) {
            caption = name;
        }
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass().equals(this.getClass())) {
            return toString().equals(obj.toString());
        } else {
            return false;
        }
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the caption
     */
    public String getCaption() {
        return caption;
    }

    /**
     * @param caption the caption to set
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }

    /**
     * @return the show
     */
    public boolean isShow() {
        return show;
    }

    /**
     * @param show the show to set
     */
    public void setShow(boolean show) {
        this.show = show;
    }

    /**
     * @return the search
     */
    public boolean isSearch() {
        return search;
    }

    /**
     * @param search the search to set
     */
    public void setSearch(boolean search) {
        this.search = search;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }
}
