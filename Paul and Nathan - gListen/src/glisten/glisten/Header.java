/*
 * Header.java
 *
 * Created on Mar 29, 2009, 7:14:59 PM
 */
package glisten;

import java.io.File;
import java.security.AuthProvider;
import java.util.Hashtable;
import java.util.Vector;
import javax.xml.parsers.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Nathan Gilbert
 */
public class Header {

    Vector<HeaderField> fields = new Vector<HeaderField>();
    String[] order;

    void load() {
        try {
            //XML processing code modified from example at: http://java.sun.com/developer/technicalArticles/xml/JavaTechandXML/
            //Get a new DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document;
            //Parse the headers xml file
            document = builder.parse(new File("headers.xml"));
            Vector<String> path = new Vector<String>();
            path.add("root");
            for(Hashtable<String, String> field : DocumentManager.getSubTable(document, path, "field")) {
                fields.add(new HeaderField(field));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void addDefaultField(String name) {
        Hashtable<String, String> props = new Hashtable<String, String>();
        props.put("name", name);
        fields.add(new HeaderField(props));
    }

    public boolean contains(String name) {
        for (HeaderField field : fields) {
            if (field.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public Vector<String> getAllNames() {
        Vector<String> names = new Vector<String>();
        for (HeaderField field : fields) {
            names.add(field.getName());
        }
        return names;
    }

    public Vector<String> getShownNames() {
        Vector<String> names = new Vector<String>();
        for (HeaderField field : fields) {
            if (field.isShow()) {
                names.add(field.getCaption());
            }
        }
        return names;
    }

    public Vector<String> getSearchNames() {
        Vector<String> names = new Vector<String>();
        for (HeaderField field : fields) {
            if (field.isSearch()) {
                names.add(field.getName());
            }
        }
        return names;
    }
}
