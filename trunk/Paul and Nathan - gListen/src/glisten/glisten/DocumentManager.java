/*
 * DocumentManager.java
 *
 * Created on Mar 30, 2009, 4:52:18 PM
 */
package glisten;

import java.util.Hashtable;
import java.util.List;
import java.util.Vector;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Nathan Gilbert
 */
public class DocumentManager {

    static Vector<Hashtable<String, String>> getSubTable(Document document,
        Vector<String> path, String type) {
        return getSubTable(document.getChildNodes(), path, type);
    }

    static Vector<Hashtable<String, String>> getSubTable(NodeList nodes,
        List<String> path, String type) {
        int count = nodes.getLength();
        Vector<Hashtable<String, String>> table = new Vector<Hashtable<String, String>>();
        for(int i = 0; i < count; i++) {
            Node node = nodes.item(i);
            if(path.isEmpty()) {
                if (type.equals(node.getNodeName())) {
                    table.add(getEntry(node));
                }
            }
            else if(path.get(0).equals(node.getNodeName())){
                table.addAll(getSubTable(node.getChildNodes(), path.subList(1, path.size()), type));
            }
        }
        return table;
    }

    static Hashtable<String, String> getEntry(Node node) {
        if (node.hasChildNodes()) {
            Hashtable<String, String> table = new Hashtable<String, String>();
            NodeList children = node.getChildNodes();
            int length = children.getLength();
            for (int i = 0; i < length; i++) {
                Node item = children.item(i);
                if (item.getNodeType() == Node.ELEMENT_NODE) {
                    table.put(item.getNodeName(), item.getTextContent());
                }
            }
            return table;
        }
        return null;
    }

    private DocumentManager() {
    }
}
