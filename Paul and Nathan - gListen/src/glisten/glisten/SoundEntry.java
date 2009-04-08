/*
 * SoundEntry.java
 *
 * Created on Mar 29, 2009, 5:54:56 PM
 */

package glisten;

import java.net.URL;
import java.util.Hashtable;
import java.util.Vector;

/**
 *
 * @author Nathan Gilbert
 */
public class SoundEntry {
    Hashtable<String, String> fields = new Hashtable<String, String>();
    public SoundEntry(Hashtable<String, String> fields) {
        this.fields = fields;
    }
    public Vector<String> getShownFields(Vector<String> names) {
        Vector<String> shown = new Vector<String>();
        for(String name : names) {
            if(fields.containsKey(name)){
                shown.add(fields.get(name));
            }
            else {
                shown.add("");
            }
        }
        return shown;
    }
}
